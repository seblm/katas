lazy val mowitnow = project
  .in(file("."))
  .settings(
    version := "0.1.0-SNAPSHOT",

    scalaVersion := "3.6.3",

    libraryDependencies += "org.typelevel" %% "cats-core" % "2.13.0",

    libraryDependencies += "org.scalameta" %% "munit" % "1.1.0" % Test
  )
