import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "name.lemerdy.sebastian",
      scalaVersion := "2.12.3",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "ascii-art",
    libraryDependencies += scalaTest % Test
  )
