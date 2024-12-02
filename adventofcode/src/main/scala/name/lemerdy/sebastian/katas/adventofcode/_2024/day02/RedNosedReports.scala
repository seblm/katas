package name.lemerdy.sebastian.katas.adventofcode._2024.day02

import scala.jdk.StreamConverters.*

object RedNosedReports:

  private def evaluateReport(levels: Array[Long]): Int =
    val withNext = levels
      .zip(levels.drop(1))
      .map: (first, second) =>
        first - second
    val sameSign = withNext.forall(_ > 0) || withNext.forall(_ < 0)
    if sameSign then
      val acceptableDiff = withNext.map(diff => Math.abs(diff)).forall(number => number >= 0 && number <= 3)
      if acceptableDiff then 1 else 0
    else 0

  def evaluateReports(input: String): Long =
    input
      .lines()
      .toScala(Vector)
      .map: line =>
        val levels = line.split(" ").map(_.toLong)
        evaluateReport(levels)
      .sum

  def evaluateReportsTolerant(input: String): Long =
    input
      .lines()
      .toScala(Vector)
      .map: line =>
        val levels = line.split(" ").map(_.toLong)
        val result = evaluateReport(levels)
        val withoutOneReport =
          if result == 0 then for i <- levels.indices yield levels.zipWithIndex.filterNot(_._2 == i).map(_._1)
          else Seq(Array(1L))
        if withoutOneReport.map(evaluateReport).contains(1) then 1 else 0
      .sum
