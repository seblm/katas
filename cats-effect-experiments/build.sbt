import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "name.lemerdy.sebastian",
      scalaVersion := "2.12.4",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "cats-effect-experiments",
    libraryDependencies += `cassandra-driver-core`,
    libraryDependencies += `cats-effect`,
    libraryDependencies += `logback-classic`,
    libraryDependencies += scalaTest % Test
  )
