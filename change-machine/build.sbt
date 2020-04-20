import Dependencies._

ThisBuild / scalaVersion     := "2.13.1"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "name.lemerdy.sebastian"

lazy val root = (project in file("."))
  .settings(
    name := "change-machine",
    libraryDependencies += scalaTest % Test
  )
