package name.lemerdy.sebastian.katas.adventofcode._2021.day09

import name.lemerdy.sebastian.katas.adventofcode._2021.day09.SmokeBasin.Position
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

import scala.io.Source

class SmokeBasinSpec extends AnyFlatSpec:

  private lazy val heightmap = """2199943210
                                 |3987894921
                                 |9856789892
                                 |8767896789
                                 |9899965678""".stripMargin.split("\n").toSeq

  "SmokeBasin" should "compute sum of risk levels of all low points" in {
    val sumOfRiskLevelsOfAllLowPoints = SmokeBasin.computeSumOfRiskLevelsOfAllLowPoints(heightmap)

    sumOfRiskLevelsOfAllLowPoints should be(15)
  }

  private lazy val input =
    Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2021/day09/input.txt").getLines().toSeq

  it should "compute adjacents" in {
    SmokeBasin.adjacents(input, Position(0, 0, 7)) should contain only (Position(1, 0, 9), Position(0, 1, 6))
  }

  it should "compute sum of risk levels of all low points with real example" in {
    val sumOfRiskLevelsOfAllLowPoints = SmokeBasin.computeSumOfRiskLevelsOfAllLowPoints(input)

    sumOfRiskLevelsOfAllLowPoints should be(496)
  }

  it should "compute basins sizes" in {
    SmokeBasin.basinSizes(heightmap) should be(Seq(3, 9, 14, 9))
  }

  it should "compute product of three largest basins sizes" in {
    SmokeBasin.productOfThreeLargestBasinSizes(heightmap) should be(1134)
  }

  it should "compute product of three largest basins sizes with real example" in {
    SmokeBasin.productOfThreeLargestBasinSizes(input) should be(902880)
  }
