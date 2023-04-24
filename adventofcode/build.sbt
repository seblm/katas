lazy val root = (project in file(".")).settings(
  name := "adventofcode",
  version := "1.0",
  scalaVersion := "3.2.2",
  libraryDependencies += "org.scalatest" %% "scalatest-flatspec" % "3.2.15" % Test,
  libraryDependencies += "org.scalatest" %% "scalatest-shouldmatchers" % "3.2.15" % Test
)