lazy val root = project
  .in(file("."))
  .settings(
    organization := "name.lemerdy.sebastian",
    scalaVersion := "3.4.2",
    version := "0.1.0-SNAPSHOT",
    name := "ascii-art",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % Test
  )
