package mowitnow.seblm

import mowitnow.seblm.Instruction.{Forward, LeftInstruction, RightInstruction}
import mowitnow.seblm.Orientation.{East, North}
import munit.FunSuite

class MowItNowSuite extends FunSuite:

  test("should parse"):
    val input = """5 5
                  |1 2 N
                  |GAGAGAGAA
                  |3 3 E
                  |AADAADADDA""".stripMargin

    val obtained = MowItNow.parseGarden(input)

    assertEquals(
      obtained,
      Right(
        Garden(
          5,
          5,
          List(
            Mower(
              Position(1, 2, North),
              List(
                LeftInstruction,
                Forward,
                LeftInstruction,
                Forward,
                LeftInstruction,
                Forward,
                LeftInstruction,
                Forward,
                Forward
              )
            ),
            Mower(
              Position(3, 3, East),
              List(
                Forward,
                Forward,
                RightInstruction,
                Forward,
                Forward,
                RightInstruction,
                Forward,
                RightInstruction,
                RightInstruction,
                Forward
              )
            )
          )
        )
      )
    )

  test("should handle bad first coordinate"):
    val input = """X 5"""

    val obtained = MowItNow.parseGarden(input)

    assertEquals(obtained, Left("Invalid dimensions"))

  test("should handle bad second coordinate"):
    val input = """5 X"""

    val obtained = MowItNow.parseGarden(input)

    assertEquals(obtained, Left("Invalid dimensions"))

  test("should handle bad coordinates"):
    val input = """5 5 something more"""

    val obtained = MowItNow.parseGarden(input)

    assertEquals(obtained, Left("Invalid dimensions"))

  test("should handle bad mower first coordinates"):
    val input = """5 5
                  |X 2 N
                  |""".stripMargin

    val obtained = MowItNow.parseGarden(input)

    assertEquals(obtained, Left("Invalid mower position"))

  test("should handle bad mower second coordinates"):
    val input = """5 5
                  |1 X N
                  |""".stripMargin

    val obtained = MowItNow.parseGarden(input)

    assertEquals(obtained, Left("Invalid mower position"))

  test("should handle bad mower orientation"):
    val input = """5 5
                  |1 2 X
                  |""".stripMargin

    val obtained = MowItNow.parseGarden(input)

    assertEquals(obtained, Left("Invalid orientation: X"))

  test("should handle bad mower"):
    val input = """5 5
                  |1 2 N something more
                  |""".stripMargin

    val obtained = MowItNow.parseGarden(input)

    assertEquals(obtained, Left("Invalid mower position"))

  test("should compute the final position of the mowers"):
    val input = """5 5
                  |1 2 N
                  |GAGAGAGAA
                  |3 3 E
                  |AADAADADDA""".stripMargin

    val obtained = MowItNow.computeFinalPositions(input)

    assertEquals(
      obtained,
      """1 3 N
        |5 1 E""".stripMargin
    )
