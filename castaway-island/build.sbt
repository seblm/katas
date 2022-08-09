lazy val root = project
  .in(file("."))
  .settings(
    name := "castaway-island",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := "3.1.3",
    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
  )
