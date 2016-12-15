lazy val root = (project in file(".")).
  settings(
    name := "adventofcode",
    version := "1.0",
    scalaVersion := "2.11.8",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"
  )

