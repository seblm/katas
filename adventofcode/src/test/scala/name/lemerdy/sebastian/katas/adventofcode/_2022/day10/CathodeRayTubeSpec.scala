package name.lemerdy.sebastian.katas.adventofcode._2022.day10

import name.lemerdy.sebastian.katas.adventofcode._2022.day10.CathodeRayTube
import org.scalatest.TryValues.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

import scala.io.Source
import scala.util.Using

class CathodeRayTubeSpec extends AnyFlatSpec:

  private val program = """addx 15
        |addx -11
        |addx 6
        |addx -3
        |addx 5
        |addx -1
        |addx -8
        |addx 13
        |addx 4
        |noop
        |addx -1
        |addx 5
        |addx -1
        |addx 5
        |addx -1
        |addx 5
        |addx -1
        |addx 5
        |addx -1
        |addx -35
        |addx 1
        |addx 24
        |addx -19
        |addx 1
        |addx 16
        |addx -11
        |noop
        |noop
        |addx 21
        |addx -15
        |noop
        |noop
        |addx -3
        |addx 9
        |addx 1
        |addx -3
        |addx 8
        |addx 1
        |addx 5
        |noop
        |noop
        |noop
        |noop
        |noop
        |addx -36
        |noop
        |addx 1
        |addx 7
        |noop
        |noop
        |noop
        |addx 2
        |addx 6
        |noop
        |noop
        |noop
        |noop
        |noop
        |addx 1
        |noop
        |noop
        |addx 7
        |addx 1
        |noop
        |addx -13
        |addx 13
        |addx 7
        |noop
        |addx 1
        |addx -33
        |noop
        |noop
        |noop
        |addx 2
        |noop
        |noop
        |noop
        |addx 8
        |noop
        |addx -1
        |addx 2
        |addx 1
        |noop
        |addx 17
        |addx -9
        |addx 1
        |addx 1
        |addx -3
        |addx 11
        |noop
        |noop
        |addx 1
        |noop
        |addx 1
        |noop
        |noop
        |addx -13
        |addx -19
        |addx 1
        |addx 3
        |addx 26
        |addx -30
        |addx 12
        |addx -1
        |addx 3
        |addx 1
        |noop
        |noop
        |noop
        |addx -9
        |addx 18
        |addx 1
        |addx 2
        |noop
        |noop
        |addx 9
        |noop
        |noop
        |noop
        |addx -1
        |addx 2
        |addx -37
        |addx 1
        |addx 3
        |noop
        |addx 15
        |addx -21
        |addx 22
        |addx -6
        |addx 1
        |noop
        |addx 2
        |addx 1
        |noop
        |addx -10
        |noop
        |noop
        |addx 20
        |addx 1
        |addx 2
        |addx 2
        |addx -6
        |addx -11
        |noop
        |noop
        |noop""".stripMargin

  "CathodeRayTube" should "compute signal strengths" in {
    CathodeRayTube.computeSignalStrengths(program) should be(13140)
  }

  private lazy val input =
    Using(Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2022/day10/input.txt"))(
      _.mkString
    ).success.value

  it should "compute with real example" in {
    CathodeRayTube.computeSignalStrengths(input) should be(14620)
  }

  it should "display" in {
    CathodeRayTube.display(program) should be("""##..##..##..##..##..##..##..##..##..##..
                                                |###...###...###...###...###...###...###.
                                                |####....####....####....####....####....
                                                |#####.....#####.....#####.....#####.....
                                                |######......######......######......####
                                                |#######.......#######.......#######.....""".stripMargin)
  }

  it should "display with real example" in {
    CathodeRayTube.display(input) should be("""###....##.####.###..#..#.###..####.#..#.
                                              |#..#....#.#....#..#.#..#.#..#.#....#..#.
                                              |###.....#.###..#..#.####.#..#.###..#..#.
                                              |#..#....#.#....###..#..#.###..#....#..#.
                                              |#..#.#..#.#....#.#..#..#.#.#..#....#..#.
                                              |###...##..#....#..#.#..#.#..#.#.....##..""".stripMargin)
  }
