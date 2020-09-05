import sbt._

object Dependencies {
  lazy val ec2 = "software.amazon.awssdk" % "ec2" % "2.14.12"
  lazy val `logback-classic` = "ch.qos.logback" % "logback-classic" % "1.2.3"
}
