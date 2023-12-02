package name.lemerdy.sebastian.katas.adventofcode._2023.day02

import org.scalatest.TryValues.given
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

import scala.io.Source
import scala.util.Using

class CubeConundrumSpec extends AnyFlatSpec:
  "Cube Conundrum" should "sum IDs of possible games" in:
    val sum = CubeConundrum.sum("""Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
                                  |Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
                                  |Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
                                  |Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
                                  |Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green""".stripMargin)

    sum should be(8)

  private lazy val input =
    Using(Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2023/day02/input.txt"))(
      _.mkString
    ).success.value

  it should "sum IDs of possible games with real example" in:
    val sum = CubeConundrum.sum(input)

    sum should be(2_265)

  it should "compute power" in:
    val power = CubeConundrum.power("""Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
                                      |Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
                                      |Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
                                      |Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
                                      |Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green""".stripMargin)

    power should be(2_286)

  it should "compute power with real example" in:
    val power = CubeConundrum.power(input)

    power should be(64_097)
