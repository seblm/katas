package name.lemerdy.sebastian.katas.adventofcode._2021.day05

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

import scala.io.Source

class HydrotermalVentureSpec extends AnyFlatSpec:

  private lazy val testInput = """0,9 -> 5,9
                                 |8,0 -> 0,8
                                 |9,4 -> 3,4
                                 |2,2 -> 2,1
                                 |7,0 -> 7,4
                                 |6,4 -> 2,0
                                 |0,9 -> 2,9
                                 |3,4 -> 1,4
                                 |0,0 -> 8,8
                                 |5,5 -> 8,2""".stripMargin.split("\n").toList

  "HydrotermalVenture" should "compute overlap count" in {
    val overlapCount = HydrothermalVenture.computeOverlapCount(testInput)

    overlapCount should be(5)
  }

  private lazy val input: List[String] =
    Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2021/day05/input.txt").getLines().toList

  it should "compute overlap count with real example" in {
    val overlapCount = HydrothermalVenture.computeOverlapCount(input)

    overlapCount should be(6113)
  }

  it should "compute diagonal overlap count" in {
    val overlapCount = HydrothermalVenture.computeDiagonalOverlapCount(testInput)

    overlapCount should be(12)
  }

  it should "compute diagonal overlap count with real example" in {
    val overlapCount = HydrothermalVenture.computeDiagonalOverlapCount(input)

    overlapCount should be(20373)
  }
