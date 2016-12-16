lazy val root = (project in file(".")).
  settings(
    name := "diamond",
    version := "1.0",
    scalaVersion := "2.12.1",
    projectDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % Test
  )
