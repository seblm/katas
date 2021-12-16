package name.lemerdy.sebastian.katas.adventofcode._2021.day14

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

import scala.io.Source

class ExtendedPolymerizationSpec extends AnyFlatSpec:

  private val example = """NNCB
                          |
                          |CH -> B
                          |HH -> N
                          |CB -> H
                          |NH -> C
                          |HB -> C
                          |HC -> B
                          |HN -> C
                          |NN -> C
                          |BH -> H
                          |NC -> B
                          |NB -> B
                          |BN -> B
                          |BB -> N
                          |BC -> B
                          |CC -> N
                          |CN -> C""".stripMargin.split("\n").toList

  "ExtendedPolymerization" should "compute most minus least common" in {
    val result = ExtendedPolymerization.mostMinusLeastCommon(example, 10)

    result should be(1588)
  }

  private lazy val input: List[String] =
    Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2021/day14/input.txt").getLines().toList

  it should "compute most minus least common with real example" in {
    val result = ExtendedPolymerization.mostMinusLeastCommon(input, 10)

    result should be(3406)
  }

  it should "compute most minus least common 40 times" in {
    val result = ExtendedPolymerization.mostMinusLeastCommon(example, 40)

    result should be(2188189693529L)
  }

  it should "compute most minus least common 40 times with real example" in {
    val result = ExtendedPolymerization.mostMinusLeastCommon(input, 40)

    result should be(3941782230241L)
  }
