package name.lemerdy.sebastian.katas.adventofcode._2024.day01

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.TryValues.given
import org.scalatest.matchers.should.Matchers.*

import scala.io.Source
import scala.util.Using

class HistorianHysteriaSpec extends AnyFlatSpec:

  "HistorianHysteria" should "compute distance" in:
    val result = HistorianHysteria.computeDistance("""3   4
                                                     |4   3
                                                     |2   5
                                                     |1   3
                                                     |3   9
                                                     |3   3""".stripMargin)

    result should be(11)

  private lazy val input =
    Using(Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2024/day01/input.txt"))(
      _.mkString
    ).success.value

  it should "compute distance with real example" in:
    val result = HistorianHysteria.computeDistance(input)

    result should be(2_113_135)

  it should "compute similarity score" in:
    val result = HistorianHysteria.computeSimilarityScore("""3   4
                                                            |4   3
                                                            |2   5
                                                            |1   3
                                                            |3   9
                                                            |3   3""".stripMargin)

    result should be(31)

  it should "compute similarity score with real example" in:
    val result = HistorianHysteria.computeSimilarityScore(input)

    result should be(19_097_157)
