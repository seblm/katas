val scala3Version = "3.4.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "cats-effect-experiments",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.apache.cassandra" % "java-driver-core" % "4.18.1",
    libraryDependencies += "org.typelevel" %% "cats-effect" % "3.5.4",

    libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.5.6" % Runtime,

    libraryDependencies += "org.scalameta" %% "munit" % "1.0.0" % Test
  )
