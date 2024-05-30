import Main.{Mower, Orientation, Position}
import munit.FunSuite


class TondeuseTest extends FunSuite:

  test("handle empty instruction") {
    val input = List("5 5", "1 2 N", "  ")
    val expectedMowers = Right((5, 5, List((Mower(Position(1, 2), Orientation.North), List.empty[Char]))))

    val parsedInput = Main.parseInput(input)

    parsedInput match {
      case Right((maxX, maxY, mowers)) =>
        assert(maxX == 5)
        assert(maxY == 5)
        assert(mowers.size == 1)
        val (mower, instructions) = mowers.head
        assert(mower == Mower(Position(1, 2), Orientation.North))
        assert(instructions.isEmpty)
      case Left(error) => fail(s"Unexpected error: $error")
    }
  }

  test("handle unknown instruction"):
    // Ignore invalid instructions
    val initialMower = {
      Mower(Position(1, 2), Orientation.North)
    }
    val (resultMower, _) = Main.executeInstructions(initialMower, List('X'), 5, 5, Set.empty)
    assert(resultMower == initialMower)

  test("handle invalid max x"):
    assert(Main.parseInput( List("X 5")) == Left("Invalid lawn dimensions"))

  test("handle invalid max y"):
    assert(Main.parseInput(List("5 X")) == Left("Invalid lawn dimensions"))

  test("handle invalid mower position with non-integer x") {
    val result = Main.parseInput(List("5 5", "X 2 N", "GAGAGAGAA"))
    println(result)
    assert(result == Left("Invalid mower positions or orientations"))
  }

  test("handle invalid mower position with non-integer y") {
    assert(Main.parseInput(List("5 5", "1 Y N", "GAGAGAGAA")) == Left("Invalid mower positions or orientations"))
  }

  test("handle invalid mower orientation") {
    assert(Main.parseInput(List("5 5", "1 2 X", "GAGAGAGAA")) == Left("Invalid mower positions or orientations"))
  }

  test("handle invalid input format") {
    assert(Main.parseInput(Nil) == Left("Invalid input format"))
  }

  test("executeInstructions should correctly execute a sequence of instructions") {
    val maxX = 5
    val maxY = 5
    val initialMower = {
      Mower(Position(1, 2), Orientation.North)
    }
    val instructions = "GAGAGAGAA".toList
    val expectedMower = Mower(Position(1, 3), Orientation.North)
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

    val expectedParsedInput = Right((
      5, 5, List(
      (Mower(Position(1, 2), Orientation.North), "GAGAGAGAA".toList),
      (Mower(Position(3, 3), Orientation.East), "AADAADADDA".toList)
    )
    ))

    val parsedInput = Main.parseInput(input.split("\r\n").toList)
    assert(parsedInput == expectedParsedInput)
  }

  test("simulateMowers should correctly execute the simulation for all mowers") {
    val maxX = 5
    val maxY = 5
    val parsedInput = List(
      (Mower(Position(1, 2), Orientation.North), "GAGAGAGAA".toList),
      (Mower(Position(3, 3), Orientation.East), "AADAADADDA".toList)
    )

    val expectedMowers = List(
      Mower(Position(1, 3), Orientation.North),
      Mower(Position(5, 1), Orientation.East)
    )

    val simulatedMowers = Main.simulateMowers(maxX, maxY, parsedInput)
    assert(simulatedMowers == expectedMowers)
  }
