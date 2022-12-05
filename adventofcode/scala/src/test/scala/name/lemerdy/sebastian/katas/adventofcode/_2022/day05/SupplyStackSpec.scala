package name.lemerdy.sebastian.katas.adventofcode._2022.day05

import org.scalatest.TryValues.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

import scala.io.Source
import scala.util.Using

class SupplyStackSpec extends AnyFlatSpec:

  "SupplyStack" should "compute to of stacks" in {
    SupplyStack.computeTopOfStacks(
      """    [D]
        |[N] [C]    
        |[Z] [M] [P]
        | 1   2   3
        |
        |move 1 from 2 to 1
        |move 3 from 1 to 3
        |move 2 from 2 to 1
        |move 1 from 1 to 2""".stripMargin,
      byBlock = false
    ) should be("CMZ")
  }

  private lazy val input =
    Using(Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2022/day05/input.txt"))(
      _.mkString
    ).success.value

  it should "compute top of stacks with real example" in {
    SupplyStack.computeTopOfStacks(input, byBlock = false, real = true) should be("VJSFHWGFT")
  }

  it should "compute top of stacks by blocks" in {
    SupplyStack.computeTopOfStacks(
      """    [D]
        |[N] [C]
        |[Z] [M] [P]
        | 1   2   3
        |
        |move 1 from 2 to 1
        |move 3 from 1 to 3
        |move 2 from 2 to 1
        |move 1 from 1 to 2""".stripMargin,
      byBlock = true
    ) should be("MCD")
  }

  it should "compute top of stacks by blocks with real example" in {
    SupplyStack.computeTopOfStacks(input, byBlock = true, real = true) should be("LCTQFBVZV")
  }
