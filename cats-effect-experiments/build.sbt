val scala3Version = "3.2.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "cats-effect-experiments",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "com.datastax.oss" % "java-driver-core" % "4.15.0",
    libraryDependencies += "org.typelevel" %% "cats-effect" % "3.5-4ba2590",

    libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.4.4" % Runtime,

    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
  )
