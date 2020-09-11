package infrastructure.application

import domain.{InstancesRepository, ZeeneaApplicationsManager}
import infrastructure.repository.DescribeAWSInstances

object ShowAWSApplications extends App {

  require(args.nonEmpty, "Please provide an aws-vault profile name")

  private val instancesRepository: InstancesRepository = new DescribeAWSInstances(args(0))
  private val zeeneaApplications = new ZeeneaApplicationsManager(instancesRepository)

  private val header = """Application  Version
                         |------------+-------""".stripMargin
  private val runningApplicationsToPrint = zeeneaApplications.getRunningApplications.flatMap { application =>
    application.versions.map(version => f"${application.name}%-12s $version")
  }

  println((header :: runningApplicationsToPrint).mkString("\n"))

}
