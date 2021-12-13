package name.lemerdy.sebastian.katas.adventofcode._2021.day13

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

import scala.io.Source

class TransparentOrigamiSpec extends AnyFlatSpec:

  "TransparentOrigami" should "count dots after one fold" in {
    val count = TransparentOrigami.count(
      1,
      """6,10
        |0,14
        |9,10
        |0,3
        |10,4
        |4,11
        |6,0
        |6,12
        |4,1
        |0,13
        |10,12
        |3,4
        |3,0
        |8,4
        |1,10
        |2,14
        |8,10
        |9,0
        |
        |fold along y=7
        |fold along x=5""".stripMargin.split("\n").toList
    )

    count should be(17)
  }

  private lazy val input: List[String] =
    Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2021/day13/input.txt").getLines().toList

  it should "count dots after one fold with real example" in {
    TransparentOrigami.count(1, input) should be(695)
  }

  it should "display capital letters" in {
    TransparentOrigami.foldAll(input) should be(""".##....##.####..##..#....#..#.###....##
                                                  |#..#....#....#.#..#.#....#..#.#..#....#
                                                  |#.......#...#..#....#....#..#.#..#....#
                                                  |#.##....#..#...#.##.#....#..#.###.....#
                                                  |#..#.#..#.#....#..#.#....#..#.#....#..#
                                                  |.###..##..####..###.####..##..#.....##.""".stripMargin)
  }
