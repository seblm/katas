lazy val root = (project in file(".")).settings(
  name := "adventofcode",
  version := "1.0",
  scalacOptions += "-deprecation",
  scalaVersion := "3.1.0",
  libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.9" % Test
)
