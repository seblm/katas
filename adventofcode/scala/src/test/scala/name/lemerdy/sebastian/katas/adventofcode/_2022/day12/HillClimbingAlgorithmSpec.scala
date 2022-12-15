package name.lemerdy.sebastian.katas.adventofcode._2022.day12

import name.lemerdy.sebastian.katas.adventofcode._2022.day12.HillClimbingAlgorithm.*
import org.scalatest.TryValues.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

import scala.io.Source
import scala.util.Using

class HillClimbingAlgorithmSpec extends AnyFlatSpec:

  "HillClimbingAlgorithm" should "compute fewest steps" in {
    HillClimbingAlgorithm.computeFewestSteps("""Sabqponm
                                               |abcryxxl
                                               |accszExk
                                               |acctuvwj
                                               |abdefghi""".stripMargin) should be(31)
  }

  private lazy val input =
    Using(Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2022/day12/input.txt"))(
      _.mkString
    ).success.value

  it should "compute fewest steps with real example" in {
    HillClimbingAlgorithm.computeFewestSteps(input) should be(339)
  }

  it should "compute fewest steps starting at any 'a'" in {
    HillClimbingAlgorithm.computeFewestSteps(
      """Sabqponm
                                               |abcryxxl
                                               |accszExk
                                               |acctuvwj
                                               |abdefghi""".stripMargin,
      'a'
    ) should be(29)
  }

  it should "compute fewest steps starting at any 'a' with real example" in {
    HillClimbingAlgorithm.computeFewestSteps(input, 'a') should be(332)
  }

end HillClimbingAlgorithmSpec
