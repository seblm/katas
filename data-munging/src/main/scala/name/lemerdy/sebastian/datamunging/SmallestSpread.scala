package name.lemerdy.sebastian.datamunging

import java.io.File
import java.nio.file.Paths

import scala.io.Source

object SmallestSpread extends App {

  /*
  Part One: Weather Data
  In weather.dat you’ll find daily weather data for Morristown, NJ for June 2002. Download this text file, then write a
  program to output the day number (column one) with the smallest temperature spread (the maximum temperature is the
  second column, the minimum the third column).
   */
  def computeSmallestSpread(file: File): String = {
    Source.fromFile(file).getLines().drop(2)
      .map(line => line.split("\\s+").filter(_.trim.nonEmpty))
      .filter(columns => columns.head != "mo")
      .map(columns => WeatherLine(columns(0).trim(), columns(1).replaceAll("\\*", "").toLong, columns(2).replaceAll("\\*", "").toLong))
      .minBy(_.spread).day
  }

  println(computeSmallestSpread(Paths.get("src", "main", "resources", "weather.dat").toFile))

}
