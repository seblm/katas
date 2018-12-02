lazy val root = (project in file(".")).
  settings(
    name := "adventofcode",
    version := "1.0",
    scalaVersion := "2.12.7",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test
  )
