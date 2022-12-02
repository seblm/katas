package name.lemerdy.sebastian.katas.adventofcode._2022.day02

import org.scalatest.TryValues.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

import scala.io.Source
import scala.util.Using

class RockPaperScissorsSpec extends AnyFlatSpec:

  "RockPaperScissors" should "compute total score" in {
    RockPaperScissors.computeTotalScore("""A Y
                                          |B X
                                          |C Z""".stripMargin) should be(15)
  }

  private lazy val input =
    Using(Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2022/day02/input.txt"))(
      _.mkString
    ).success.value

  it should "compute total score with real example" in {
    RockPaperScissors.computeTotalScore(input) should be(11_906)
  }

  it should "compute total score with correct strategy" in {
    RockPaperScissors.computeTotalScoreCorrectStrategy("""A Y
                                                         |B X
                                                         |C Z""".stripMargin) should be(12)
  }

  it should "compute total score with correct strategy with real example" in {
    RockPaperScissors.computeTotalScoreCorrectStrategy(input) should be(11_186)
  }
