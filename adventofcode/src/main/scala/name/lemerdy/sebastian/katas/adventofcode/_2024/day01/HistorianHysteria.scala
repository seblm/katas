package name.lemerdy.sebastian.katas.adventofcode._2024.day01

import scala.jdk.StreamConverters.*
import scala.util.matching.Regex

object HistorianHysteria:

  private val pair: Regex = """([0-9]+) {3}([0-9]+)""".r

  private def linesToTwoLongs(input: String) = input
    .lines()
    .toScala(Vector)
    .flatMap:
      case pair(left, right) => Some((left.toLong, right.toLong))
      case _                 => None

  def computeDistance(input: String): Long =
    val lines = linesToTwoLongs(input)
    lines
      .map(_._1)
      .sorted
      .zip(lines.map(_._2).sorted)
      .map: (left, right) =>
        Math.abs(left - right)
      .sum

  def computeSimilarityScore(input: String): Long =
    val lines = linesToTwoLongs(input)
    val numberOfOccurrences = lines
      .map(_._2)
      .groupBy(identity)
      .map: (number, numbers) =>
        number -> numbers.length
    lines.map(_._1).map(number => number * numberOfOccurrences.getOrElse(number, 0)).sum
