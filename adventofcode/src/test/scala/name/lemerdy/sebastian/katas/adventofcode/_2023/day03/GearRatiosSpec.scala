package name.lemerdy.sebastian.katas.adventofcode._2023.day03

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

import scala.io.Source
import scala.util.Using
import org.scalatest.TryValues.given

class GearRatiosSpec extends AnyFlatSpec:
  "Gear ratios" should "compute sum" in:
    val sum = GearRatios.sum("""467..114..
                               |...*......
                               |..35..633.
                               |......#...
                               |617*......
                               |.....+.58.
                               |..592.....
                               |......755.
                               |...$.*....
                               |.664.598..""".stripMargin)

    sum should be(4_361)

  private lazy val input =
    Using(Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2023/day03/input.txt"))(
      _.mkString
    ).success.value

  it should "compute sum with real example" in:
    val sum = GearRatios.sum(input)

    sum should be(519_444)

  it should "compute sum gear ratios" in:
    val sum = GearRatios.sumGearRatios("""467..114..
                                         |...*......
                                         |..35..633.
                                         |......#...
                                         |617*......
                                         |.....+.58.
                                         |..592.....
                                         |......755.
                                         |...$.*....
                                         |.664.598..""".stripMargin)

    sum should be(467_835)

  it should "compute sum gear ratios with real example" in:
    val sum = GearRatios.sumGearRatios(input)

    sum should be(74_528_807)
