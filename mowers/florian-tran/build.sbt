import Dependencies._

ThisBuild / scalaVersion := "2.13.12"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "example"

lazy val pekkoVersion = "1.0.1"

lazy val root = (project in file("."))
  .settings(
    name := "tondeuse-scala",
    libraryDependencies ++= Seq(
      "org.apache.pekko" %% "pekko-http" % pekkoVersion,
      "org.apache.pekko" %% "pekko-stream" % pekkoVersion,
      "org.json" % "json" % "20230227",
      "org.scalatest" %% "scalatest" % "3.2.14" % Test
    )
  )
