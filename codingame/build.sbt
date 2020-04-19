import Dependencies._

ThisBuild / scalaVersion     := "2.13.2"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "name.lemerdy.sebastian"
ThisBuild / organizationName := "seblm"

lazy val root = (project in file("."))
  .settings(
    name := "codingame",
    libraryDependencies += scalaTest % Test
  )
  .settings(
    scalacOptions ++= Seq("-deprecation", "-feature", "-Xfatal-warnings")
  )
