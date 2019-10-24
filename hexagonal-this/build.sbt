import Dependencies._

ThisBuild / scalaVersion     := "2.13.1"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "hexagonal-this",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += `cats-core`,
    libraryDependencies += `cats-effect`,
    libraryDependencies += `circe-core`,
    libraryDependencies += `circe-parser`,
    libraryDependencies += `circe-generic`,
  )
