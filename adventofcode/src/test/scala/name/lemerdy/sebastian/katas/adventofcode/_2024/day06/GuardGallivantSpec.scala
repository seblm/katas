package name.lemerdy.sebastian.katas.adventofcode._2024.day06

import org.scalatest.TryValues.given
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

import scala.io.Source
import scala.util.Using

class GuardGallivantSpec extends AnyFlatSpec:

  private val smallInput = """....#.....
                             |.........#
                             |..........
                             |..#.......
                             |.......#..
                             |..........
                             |.#..^.....
                             |........#.
                             |#.........
                             |......#...""".stripMargin

  it should "count distinct positions" in:
    val result = GuardGallivant.countDistinctPositions(smallInput)

    result should be(41)

  private lazy val input =
    Using(Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2024/day06/input.txt"))(
      _.mkString
    ).success.value

  it should "count distinct positions with real example" in:
    val result = GuardGallivant.countDistinctPositions(input)

    result should be(5_452)
