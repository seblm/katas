package name.lemerdy.sebastian.katas.adventofcode._2022.day03

import org.scalatest.TryValues.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

import scala.io.Source
import scala.util.Using

class RucksackReorganizationSpec extends AnyFlatSpec:

  "RucksackReorganization" should "sum priorities of wrong item" in {
    RucksackReorganization.sumPriorities("""vJrwpWtwJgWrhcsFMMfFFhFp
                                           |jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
                                           |PmmdzqPrVvPwwTWBwg
                                           |wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
                                           |ttgJtRGJQctTZtZT
                                           |CrZsJsPPZsGzwwsLwLmpwMDw""".stripMargin) should be(157)
  }

  private lazy val input =
    Using(Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2022/day03/input.txt"))(
      _.mkString
    ).success.value

  it should "sum priorities of wrong item in real example" in {
    RucksackReorganization.sumPriorities(input) should be(8_233)
  }

  it should "find badges priorities" in {
    RucksackReorganization.sumBadges("""vJrwpWtwJgWrhcsFMMfFFhFp
                                       |jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
                                       |PmmdzqPrVvPwwTWBwg
                                       |wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
                                       |ttgJtRGJQctTZtZT
                                       |CrZsJsPPZsGzwwsLwLmpwMDw""".stripMargin) should be(70)
  }

  it should "find badges priorities in real example" in {
    RucksackReorganization.sumBadges(input) should be(2_821)
  }
