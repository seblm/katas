ThisBuild / scalaVersion := "3.3.0"
ThisBuild / useCoursier := false

lazy val root = (project in file("."))
  .settings(
    name := "mower-project",
    version := "0.1",
    Compile / run / mainClass := Some("tondeuse.Main"),
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-blaze-server" % "0.23.17",
      "org.http4s" %% "http4s-dsl" % "0.23.18",
      "org.http4s" %% "http4s-circe" % "0.23.18",
      "io.circe" %% "circe-generic" % "0.14.1",
      "io.circe" %% "circe-parser" % "0.14.1",
      "org.typelevel" %% "cats-effect" % "3.3.12",
      "org.scalatest" %% "scalatest" % "3.2.15" % Test
    )
  )
