package example

import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.ec2.Ec2Client
import software.amazon.awssdk.services.ec2.model.{DescribeInstancesRequest, Ec2Exception}

import scala.jdk.CollectionConverters.ListHasAsScala
import scala.util.Try

/**
  * port of https://github.com/awsdocs/aws-doc-sdk-examples/blob/master/javav2/example_code/ec2/src/main/java/com/example/ec2/DescribeInstances.java
  */
object DescribeInstances extends App {

  private val region: Region = Region.EU_WEST_3

  private val ec2: Ec2Client = Ec2Client.builder().region(region).build()

  var done: Boolean = false
  var nextToken: String = _

  Try {
    do {
      val request = DescribeInstancesRequest.builder().maxResults(6).nextToken(nextToken).build()
      val response = ec2.describeInstances(request)
      for {
        reservation <- response.reservations().asScala
        instance <- reservation.instances().asScala
      } yield println(f"""Found reservation with id ${instance.instanceId()}%s,
           | AMI ${instance.imageId()}%s,
           | type ${instance.instanceType()}%s,
           | state ${instance.state().name()}%s
           | and monitoring state ${instance.monitoring().state()}%s""".stripMargin.stripLineEnd)
      nextToken = response.nextToken()
    } while (nextToken != null)
    ()
  }.fold(
    {
      case e: Ec2Exception =>
        Console.err.println(e.awsErrorDetails().errorMessage())
        ()
      case _ =>
        ()
    },
    identity
  )

}
