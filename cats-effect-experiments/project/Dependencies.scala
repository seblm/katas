import sbt._

object Dependencies {
  lazy val `cassandra-driver-core` = "com.datastax.cassandra" % "cassandra-driver-core" % "3.4.0"
  lazy val `cats-effect` = "org.typelevel" %% "cats-effect" % "0.9"
  lazy val `logback-classic` = "ch.qos.logback" % "logback-classic" % "1.2.3"
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
}
