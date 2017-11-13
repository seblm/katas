import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "name.lemerdy.sebastian.kata.devicedriver",
      scalaVersion := "2.12.4",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "device-driver-kata",
    libraryDependencies += `mockito-core` % Test,
    libraryDependencies += scalatest % Test
  )
