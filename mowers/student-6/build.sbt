name := """scala-tondeuse"""
organization := "michee"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.16"

libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.1" % Test;
libraryDependencies += guice
// Adds additional packages into Twirl
//TwirlKeys.templateImports += "michee.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "michee.binders._"
