package name.lemerdy.sebastian.katas.adventofcode._2020.day11

import name.lemerdy.sebastian.katas.adventofcode._2020.day11.SeatingSystem.countOccupiedSeats
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

import scala.io.Source

class SeatingSystemSpec extends AnyFlatSpec {

  lazy val input: String =
    Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2020/day11/input.txt").getLines().mkString("\n")

  "SeatingSystem" should "count occupied adjacents seats" in {
    SeatingSystem.occupiedAdjacentSeats("""L.LL.LL.LL
                                          |LLLLLLL.LL
                                          |L.L.L..L..
                                          |LLLL.LL.LL
                                          |L.LL.LL.LL
                                          |L.LLLLL.LL
                                          |..L.L.....
                                          |LLLLLLLLLL
                                          |L.LLLLLL.L
                                          |L.LLLLL.LL""".stripMargin) shouldBe 37
  }

  it should "count occupied adjacents seats with input" in {
    SeatingSystem.occupiedAdjacentSeats(input) shouldBe 2265
  }

  it should "count occupied seats" ignore {
    SeatingSystem.occupiedSeats("""L.LL.LL.LL
                                          |LLLLLLL.LL
                                          |L.L.L..L..
                                          |LLLL.LL.LL
                                          |L.LL.LL.LL
                                          |L.LLLLL.LL
                                          |..L.L.....
                                          |LLLLLLLLLL
                                          |L.LLLLLL.L
                                          |L.LLLLL.LL""".stripMargin) shouldBe 37
  }

  it should "count occupied seats iteration 1" ignore {
    val map = """L.LL.LL.LL
                |LLLLLLL.LL
                |L.L.L..L..
                |LLLL.LL.LL
                |L.LL.LL.LL
                |L.LLLLL.LL
                |..L.L.....
                |LLLLLLLLLL
                |L.LLLLLL.L
                |L.LLLLL.LL""".stripMargin
    val withoutNewLines = map.replaceAll("\n", "")
    val height = map.count(_ == '\n') + 1
    val width = withoutNewLines.length / height
    val newMap = countOccupiedSeats(withoutNewLines, width, height)._2
    newMap.grouped(width).mkString("\n") shouldBe """#.##.##.##
                                                    |#######.##
                                                    |#.#.#..#..
                                                    |####.##.##
                                                    |#.##.##.##
                                                    |#.#####.##
                                                    |..#.#.....
                                                    |##########
                                                    |#.######.#
                                                    |#.#####.##""".stripMargin

  }

  it should "count occupied seats with input" ignore {
    SeatingSystem.occupiedSeats(input) shouldBe 2265
  }

}
