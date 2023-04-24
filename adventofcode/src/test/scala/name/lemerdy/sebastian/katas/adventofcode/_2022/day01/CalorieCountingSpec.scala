package name.lemerdy.sebastian.katas.adventofcode._2022.day01

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.TryValues.*

import scala.io.Source
import scala.util.Using

class CalorieCountingSpec extends AnyFlatSpec:

  "CalorieCounting" should "compute total Calories carried by the Elf that have most" in {
    CalorieCounting.totalCaloriesOfMost(1)("""1000
                                             |2000
                                             |3000
                                             |
                                             |4000
                                             |
                                             |5000
                                             |6000
                                             |
                                             |7000
                                             |8000
                                             |9000
                                             |
                                             |10000""".stripMargin) should be(24_000)
  }

  private lazy val input =
    Using(Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2022/day01/input.txt"))(
      _.mkString
    ).success.value

  it should "compute total Calories carried by the Elf that have most with real example" in {
    CalorieCounting.totalCaloriesOfMost(1)(input) should be(67_027)
  }

  it should "compute total Calories carried by the 3 Elves that have most" in {
    CalorieCounting.totalCaloriesOfMost(3)("""1000
                                             |2000
                                             |3000
                                             |
                                             |4000
                                             |
                                             |5000
                                             |6000
                                             |
                                             |7000
                                             |8000
                                             |9000
                                             |
                                             |10000""".stripMargin) should be(45_000)
  }

  it should "compute total Calories carried by the 3 Elves that have most with real example" in {
    CalorieCounting.totalCaloriesOfMost(3)(input) should be(197_291)
  }
