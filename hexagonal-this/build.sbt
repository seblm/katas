import Dependencies._

ThisBuild / scalaVersion     := "2.13.1"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "hexagonal-this",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += "io.circe" %% "circe-core" % "0.12.2",
    libraryDependencies += "io.circe" %% "circe-parser" % "0.12.2",
    libraryDependencies += "io.circe" %% "circe-generic" % "0.12.2",
  )
