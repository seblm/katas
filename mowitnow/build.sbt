val scala3Version = "3.4.2"

lazy val mowitnow = project
  .in(file("."))
  .settings(
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.typelevel" %% "cats-core" % "2.12.0",

    libraryDependencies += "org.scalameta" %% "munit" % "1.0.0" % Test
  )
