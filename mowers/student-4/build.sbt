scalaVersion := "3.3.4"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := "tp-tondeuse-guillaume",
    libraryDependencies ++= Seq(
      guice,
      "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.1" % Test
    ),
    scalacOptions ++= Seq(
      "-feature",
      "-Werror"
    )
  )
