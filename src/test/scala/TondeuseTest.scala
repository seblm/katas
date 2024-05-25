import Main.{East, Mower, North, Position}
import munit.FunSuite


class TondeuseTest extends FunSuite:

  test("executeInstructions should correctly execute a sequence of instructions") {
    val maxX = 5
    val maxY = 5
    val initialMower = {
      Mower(Position(1, 2), North)
    }
    val instructions = "GAGAGAGAA".toList
    val expectedMower = Mower(Position(1, 3), North)
    val occupiedPositions = Set[Position](Position(3, 3))

    val (resultMower, _) = Main.executeInstructions(initialMower, instructions, maxX, maxY, occupiedPositions)
    assert(resultMower == expectedMower)
  }

  test("parseInput should correctly parse input data") {
    val input: String =
      """5 5
        |1 2 N
        |GAGAGAGAA
        |3 3 E
        |AADAADADDA""".stripMargin

    val expectedMowers = List(
      Mower(Position(1, 3), North),
      Mower(Position(5, 1), East)
    )

    val parsedMowers = Main.parseInput(input.split("\r\n").toList)
    assert(parsedMowers == expectedMowers)
  }
