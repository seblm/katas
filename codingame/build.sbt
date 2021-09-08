import Dependencies._

lazy val root = project
  .in(file("."))
  .settings(
    name := "codingame",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := "3.0.2",

    libraryDependencies += scalaTest % Test,

    scalacOptions ++= Seq("-deprecation", "-feature", "-Xfatal-warnings")
  )
