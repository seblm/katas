package name.lemerdy.sebastian.katas.adventofcode._2022.day09

import name.lemerdy.sebastian.katas.adventofcode._2022.day08.TreetopTreeHouse.Tree
import org.scalatest.TryValues.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

import scala.io.Source
import scala.util.Using

class RopeBridgeSpec extends AnyFlatSpec:

  "RopeBridge" should "compute positions" in {
    RopeBridge.computePositions(
      """R 4
                                  |U 4
                                  |L 3
                                  |D 1
                                  |R 4
                                  |D 1
                                  |L 5
                                  |R 2""".stripMargin,
      2
    ) should be(13)
  }

  private lazy val input =
    Using(Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2022/day09/input.txt"))(
      _.mkString
    ).success.value

  it should "compute positions with real example" in {
    RopeBridge.computePositions(input, 2) should be(6337)
  }

  it should "compute positions with a rope of size 9" in {
    RopeBridge.computePositions(
      """R 4
                                  |U 4
                                  |L 3
                                  |D 1
                                  |R 4
                                  |D 1
                                  |L 5
                                  |R 2""".stripMargin,
      10
    ) should be(1)
  }

  it should "compute positions with a rope of size 9 with larger example" in {
    RopeBridge.computePositions(
      """R 5
                                  |U 8
                                  |L 8
                                  |D 3
                                  |R 17
                                  |D 10
                                  |L 25
                                  |U 20""".stripMargin,
      10
    ) should be(36)
  }

  it should "compute positions with a rope of size 9 with real example" in {
    RopeBridge.computePositions(input, 10) should be(2455)
  }
