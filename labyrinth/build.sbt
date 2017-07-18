import Dependencies._

lazy val labyrinth = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "name.lemerdy.sebastian",
      scalaVersion := "2.12.1",
      version      := "0.1.0-SNAPSHOT"
    )),
    libraryDependencies += scalaTest % Test
  )
