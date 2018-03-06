import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "name.lemerdy.sebastian",
      scalaVersion := "2.12.4",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "codingame",
    libraryDependencies += scalaTest % Test
  )
  .settings(
    scalacOptions ++= Seq("-deprecation", "-feature", "-Xfatal-warnings")
  )