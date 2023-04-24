package name.lemerdy.sebastian.katas.adventofcode._2021.day03

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

import scala.io.Source

class BinaryDiagnosticSpec extends AnyFlatSpec:

  "BinaryDiagnostic" should "compute power consumption" in {
    val powerConsumption = BinaryDiagnostic.computePowerConsumption("""00100
                                                                      |11110
                                                                      |10110
                                                                      |10111
                                                                      |10101
                                                                      |01111
                                                                      |00111
                                                                      |11100
                                                                      |10000
                                                                      |11001
                                                                      |00010
                                                                      |01010""".stripMargin.split("\n").toList)

    powerConsumption should be(198)
  }

  private lazy val input: List[String] =
    Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2021/day03/input.txt").getLines().toList

  it should "compute power consumption with real example" in {
    val powerConsumption = BinaryDiagnostic.computePowerConsumption(input)

    powerConsumption should be(1071734)
  }

  it should "compute life support rating" in {
    val lifeSupportRating = BinaryDiagnostic.computeLifeSupportRating("""00100
                                                                        |11110
                                                                        |10110
                                                                        |10111
                                                                        |10101
                                                                        |01111
                                                                        |00111
                                                                        |11100
                                                                        |10000
                                                                        |11001
                                                                        |00010
                                                                        |01010""".stripMargin.split("\n").toList)

    lifeSupportRating should be(230)
  }

  it should "compute life support rating with real example" in {
    val lifeSupportRating = BinaryDiagnostic.computeLifeSupportRating(input)

    lifeSupportRating should be(6124992)
  }
