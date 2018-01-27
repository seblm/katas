import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "name.sebastian.lemerdy",
      scalaVersion := "2.12.4",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "boggle",
    libraryDependencies += scalaTest % Test
  )
