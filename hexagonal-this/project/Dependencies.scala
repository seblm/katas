import sbt._

object Dependencies {
  private lazy val catsVersion = "2.0.0"
  lazy val `cats-core` = "org.typelevel" %% "cats-core" % catsVersion
  lazy val `cats-effect` = "org.typelevel" %% "cats-effect" % catsVersion
  private lazy val circeVersion = "0.12.3"
  lazy val `circe-core` = "io.circe" %% "circe-core" % circeVersion
  lazy val `circe-parser` = "io.circe" %% "circe-parser" % circeVersion
  lazy val `circe-generic` = "io.circe" %% "circe-generic" % circeVersion
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.8"
}
