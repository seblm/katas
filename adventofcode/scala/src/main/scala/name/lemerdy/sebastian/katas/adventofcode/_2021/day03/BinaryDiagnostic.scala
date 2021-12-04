package name.lemerdy.sebastian.katas.adventofcode._2021.day03

import java.lang.Long.parseLong
import scala.annotation.tailrec

object BinaryDiagnostic:

  def computePowerConsumption(diagnostic: List[String]): Long =
    val length = diagnostic.headOption.map(_.length).getOrElse(0)
    val gammaRate = Range(0, length)
      .map(i => if (diagnostic.map(_.substring(i, i + 1).toLong).sum > (diagnostic.length / 2)) "1" else "0")
      .mkString
    parseLong(gammaRate, 2) * parseLong(invert(gammaRate), 2)

  private def invert(binaryString: String): String = binaryString.map {
    case '0'   => '1'
    case '1'   => '0'
    case other => other
  }

  def computeLifeSupportRating(diagnostic: List[String]): Long =
    parseLong(computeOxygenGeneratorRating(diagnostic, 0), 2) *
      parseLong(computeCO2ScrubberRating(diagnostic, 0), 2)

  @tailrec
  private def computeOxygenGeneratorRating(diagnostic: List[String], i: Int): String =
    diagnostic match {
      case head :: Nil =>
        head
      case _ =>
        val (ones, zeros) = diagnostic.map(_.substring(i, i + 1)).partition(_ == "1")
        if (zeros.length > ones.length)
          computeOxygenGeneratorRating(filter(diagnostic, i, "0"), i + 1)
        else
          computeOxygenGeneratorRating(filter(diagnostic, i, "1"), i + 1)
    }

  @tailrec
  private def computeCO2ScrubberRating(diagnostic: List[String], i: Int): String =
    diagnostic match {
      case head :: Nil =>
        head
      case _ =>
        val (ones, zeros) = diagnostic.map(_.substring(i, i + 1)).partition(_ == "1")
        if (zeros.length > ones.length)
          computeCO2ScrubberRating(filter(diagnostic, i, "1"), i + 1)
        else
          computeCO2ScrubberRating(filter(diagnostic, i, "0"), i + 1)
    }

  private def filter(diagnostic: List[String], i: Int, value: String): List[String] =
    diagnostic.filter(_.substring(i, i + 1) == value)
