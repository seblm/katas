lazy val root = (project in file(".")).settings(
  name := "adventofcode",
  version := "1.0",
  scalaVersion := "3.3.1",
  libraryDependencies += "org.scalatest" %% "scalatest-flatspec" % "3.2.17" % Test,
  libraryDependencies += "org.scalatest" %% "scalatest-shouldmatchers" % "3.2.17" % Test
)
