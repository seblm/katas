package infrastructure.repository

import domain.InstancesRepository
import domain.InstancesRepository.{Instance, InstancesError, TechnicalError}
import software.amazon.awssdk.auth.credentials.ProcessCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.ec2.Ec2Client
import software.amazon.awssdk.services.ec2.model.{DescribeInstancesRequest, Ec2Exception, InstanceStateName}

import scala.annotation.tailrec
import scala.jdk.CollectionConverters.ListHasAsScala
import scala.util.Try

/**
  * port of https://github.com/awsdocs/aws-doc-sdk-examples/blob/master/javav2/example_code/ec2/src/main/java/com/example/ec2/DescribeInstances.java
  */
class DescribeAWSInstances(profile: String) extends InstancesRepository {

  private val region: Region = Region.EU_WEST_3

  private val processCredentialsProvider =
    ProcessCredentialsProvider.builder().command(s"aws-vault exec --json $profile").build()
  private val ec2: Ec2Client =
    Ec2Client.builder().region(region).credentialsProvider(processCredentialsProvider).build()

  @tailrec private def describeInstances(
      state: InstanceStateName
  )(acc: List[Instance], nextToken: String): List[Instance] = {
    val request = DescribeInstancesRequest.builder().maxResults(6).nextToken(nextToken).build()
    val response = ec2.describeInstances(request)

    val instances = for {
      reservation <- response.reservations().asScala
      instance <- reservation.instances().asScala if instance.state().name() == state
      name <- instance.tags().asScala.find(_.key() == "Project").map(_.value()).toList
      version <- instance.tags().asScala.find(_.key() == "Environment").map(_.value()).toList
    } yield Instance(name, version)

    val previousAndCurrentInstances = acc ++ instances

    Option(response.nextToken()) match {
      case None        => previousAndCurrentInstances
      case Some(token) => describeInstances(state)(previousAndCurrentInstances, token)
    }
  }

  override def getRunningInstances: Either[InstancesError, List[Instance]] =
    Try(describeInstances(InstanceStateName.RUNNING)(List.empty, null)).fold(
      {
        case e: Ec2Exception =>
          Console.err.println(e.awsErrorDetails().errorMessage())
          Left(TechnicalError(e.awsErrorDetails().errorMessage()))
        case e: Throwable =>
          Left(TechnicalError(s"unknown exception: ${Option(e.getMessage).getOrElse(e.toString)}"))
      },
      Right.apply
    )

  override def getTerminatedInstances: Either[InstancesError, List[Instance]] =
    Try(describeInstances(InstanceStateName.TERMINATED)(List.empty, null)).fold(
      {
        case e: Ec2Exception =>
          Console.err.println(e.awsErrorDetails().errorMessage())
          Left(TechnicalError(e.awsErrorDetails().errorMessage()))
        case _ =>
          Left(TechnicalError(""))
      },
      Right.apply
    )

  // TODO implicit conversions https://docs.scala-lang.org/tour/implicit-conversions.html
//  private implicit def handleErrors(instances: Try[List[Instance]]): Either[InstancesError, List[Instance]] = ???

  // TODO implicit class https://docs.scala-lang.org/overviews/core/implicit-classes.html
//  private implicit class DescribeInstancesOps(instances: Try[List[Instance]]) {
//    def handleErrors: Either[InstancesError, List[Instance]] = ???
//  }

}
