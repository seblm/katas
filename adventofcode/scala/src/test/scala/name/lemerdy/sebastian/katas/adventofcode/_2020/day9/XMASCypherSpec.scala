package name.lemerdy.sebastian.katas.adventofcode._2020.day9

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

import scala.io.Source

class XMASCypherSpec extends AnyFlatSpec {

  lazy val input: String =
    Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2020/day9/input.txt").getLines().mkString("\n")

  "XMASCypher" should "get first number which is not sum of previous 5 numbers" in {
    XMASCypher.wrongNumber(
      5,
      """35
                                               |20
                                               |15
                                               |25
                                               |47
                                               |40
                                               |62
                                               |55
                                               |65
                                               |95
                                               |102
                                               |117
                                               |150
                                               |182
                                               |127
                                               |219
                                               |299
                                               |277
                                               |309
                                               |576""".stripMargin
    ) shouldBe 127
  }

  it should "get first number which is not sum of previous 25 numbers" in {
    XMASCypher.wrongNumber(25, input) shouldBe 15690279
  }

  it should "get min and max of contigous numbers that sum to 127" in {
    XMASCypher.sumOfMinAndMaxContiguousThatSumTo(
      127,
      """35
                                                              |20
                                                              |15
                                                              |25
                                                              |47
                                                              |40
                                                              |62
                                                              |55
                                                              |65
                                                              |95
                                                              |102
                                                              |117
                                                              |150
                                                              |182
                                                              |127
                                                              |219
                                                              |299
                                                              |277
                                                              |309
                                                              |576""".stripMargin
    ) shouldBe 62
  }

  it should "get min and max of contigous numbers that sum to 15690279" in {
    XMASCypher.sumOfMinAndMaxContiguousThatSumTo(15690279, input) shouldBe 2174232
  }

}
