lazy val root = (project in file(".")).
  settings(
    name := "adventofcode",
    version := "1.0",
    scalaVersion := "2.12.4",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % Test
  )

