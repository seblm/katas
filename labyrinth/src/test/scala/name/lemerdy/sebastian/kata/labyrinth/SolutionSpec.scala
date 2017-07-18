package name.lemerdy.sebastian.kata.labyrinth

import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

import org.scalatest._

class SolutionSpec extends FlatSpec with Matchers {

  "Solution" should "Unique sortie" in {
    val in = new ByteArrayInputStream(
      """7 7
        |1 1
        |#######
        |#.....#
        |#####.#
        |#.#...#
        |#.#.###
        |#......
        |#######""".stripMargin.getBytes)
    val out = new ByteArrayOutputStream()

    Console.withIn[Unit](in) {
      Console.withOut[Unit](out) {
        Solution.main(Array.empty)
      }
    }

    out.toString should be(
      """1
        |6 5""".stripMargin)
  }

  "Solution" should "Plusieurs sorties" in {
    val in = new ByteArrayInputStream(
      """11 11
        |5 5
        |###########
        |......#...#
        |#.###.###.#
        |#...#.....#
        |#.#.#######
        |#.#...#...#
        |#####.###.#
        |#...#.....#
        |#.#######.#
        |#..........
        |###########""".stripMargin.getBytes)
    val out = new ByteArrayOutputStream()

    Console.withIn[Unit](in) {
      Console.withOut[Unit](out) {
        Solution.main(Array.empty)
      }
    }

    out.toString should be(
      """2
        |0 1
        |10 9""".stripMargin)
  }

  "Solution" should "Pas de sortie" in {
    val in = new ByteArrayInputStream(
      """11 11
        |5 5
        |###########
        |#.....#...#
        |#.###.###.#
        |#...#.....#
        |#.#.#######
        |#.#...#...#
        |#####.###.#
        |....#.....#
        |#.#########
        |#..........
        |###########""".stripMargin.getBytes)
    val out = new ByteArrayOutputStream()

    Console.withIn[Unit](in) {
      Console.withOut[Unit](out) {
        Solution.main(Array.empty)
      }
    }

    out.toString should be("0")
  }

  "Solution" should "Boucles" in {
    val in = new ByteArrayInputStream(
      """11 11
        |5 5
        |###########
        |......#...#
        |#.###.###.#
        |#...#.....#
        |#.#.#.##.##
        |#.#...#...#
        |#.###.###.#
        |#...#.....#
        |#.###.###.#
        |#.........#
        |###########""".stripMargin.getBytes)
    val out = new ByteArrayOutputStream()

    Console.withIn[Unit](in) {
      Console.withOut[Unit](out) {
        Solution.main(Array.empty)
      }
    }

    out.toString should be(
      """1
        |0 1""".stripMargin)
  }

  "Solution" should "Tout ensemble, 21x21" in {
    val in = new ByteArrayInputStream(
      """21 21
        |9 10
        |##########.##########
        |..#...........#.....#
        |#.#.#########.#.###.#
        |#...#.........#.#...#
        |###############.#.###
        |#.....#.......#.#...#
        |#.#######.###.#.#.#.#
        |#...#...#...#...#.#..
        |###.###.###.###.#.#.#
        |#.#.#.#...#.#...#.#.#
        |#.#.#.#.#.#.#.###.#.#
        |#...#.#.#.#.#...#.#.#
        |#####.###.#.#####.###
        |#.#.......#.#...#...#
        |#.#.#######.#.#.###.#
        |#.#.#...#...#.#.#...#
        |#.#.###.#.#####.#####
        |#.#.................#
        |#.#######.#########.#
        |#.........#..........
        |####.######.#########""".stripMargin.getBytes)
    val out = new ByteArrayOutputStream()

    Console.withIn[Unit](in) {
      Console.withOut[Unit](out) {
        Solution.main(Array.empty)
      }
    }

    out.toString should be(
      """4
        |4 20
        |11 20
        |20 7
        |20 19""".stripMargin)
  }

}
