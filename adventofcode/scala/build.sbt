lazy val root = (project in file(".")).
  settings(
    name := "adventofcode",
    version := "1.0",
    scalacOptions += "-deprecation",
    scalaVersion := "2.13.4",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.3" % Test
  )
