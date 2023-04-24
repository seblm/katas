package name.lemerdy.sebastian.katas.adventofcode._2020.day3

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

import scala.io.Source

class TobogganTrajectorySpec extends AnyFlatSpec {

  lazy val input: String =
    Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2020/day3/input.txt").getLines().mkString("\n")

  "TobogganTrajectory" should "count trees" in {
    val slope = Slope(right = 3, down = 1)
    val map = """..##.......
                |#...#...#..
                |.#....#..#.
                |..#.#...#.#
                |.#...##..#.
                |..#.##.....
                |.#.#.#....#
                |.#........#
                |#.##...#...
                |#...##....#
                |.#..#...#.#""".stripMargin

    val treesCount = TobogganTrajectory.countTrees(slope, map)

    treesCount shouldBe 7
  }

  it should "count trees with real data" in {
    val slope = Slope(right = 3, down = 1)
    val map = input

    val treesCount = TobogganTrajectory.countTrees(slope, map)

    treesCount shouldBe 257
  }

  it should "count trees with many slopes and multiply it" in {
    val slopes = List(
      Slope(right = 1, down = 1),
      Slope(right = 3, down = 1),
      Slope(right = 5, down = 1),
      Slope(right = 7, down = 1),
      Slope(right = 1, down = 2)
    )
    val map = input

    val treesCount = TobogganTrajectory.countTrees(slopes, map)

    treesCount shouldBe 1744787392
  }

}
