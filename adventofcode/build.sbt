lazy val root = (project in file(".")).settings(
  name := "adventofcode",
  version := "1.0",
  scalaVersion := "3.5.2",
  libraryDependencies += "org.scalatest" %% "scalatest-flatspec" % "3.2.19" % Test,
  libraryDependencies += "org.scalatest" %% "scalatest-shouldmatchers" % "3.2.19" % Test
)
