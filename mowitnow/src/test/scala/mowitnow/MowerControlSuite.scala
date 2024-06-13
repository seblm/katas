package mowitnow

import mowitnow.Orientation.East
import munit.FunSuite

class MowerControlSuite extends FunSuite:

  test("should keep only first instruction"):
    val input = """5 5
                  |1 2 N
                  |GAGAGAGAA
                  |3 3 E
                  |AADAADADDA""".stripMargin
    val recordedMowerContract = new RecordedMowerContract(Seq("1 2 E\n3 3 E"))
    val mowerControl = MowerControl(input, Map("participant" -> recordedMowerContract))

    val state = mowerControl.next()

    assertEquals(state("participant"), Right(Seq(Position(1, 2, East), Position(3, 3, East))))
    assertEquals(
      recordedMowerContract.getInputs,
      List("""5 5
             |1 2 N
             |G
             |3 3 E
             |""".stripMargin)
    )

  test("should handle participant failure"):
    val input = """5 5
                  |1 2 E
                  |GAGAGAGAA
                  |3 3 E
                  |AADAADADDA""".stripMargin
    val recordedMowerContract = new RecordedMowerContract(Seq("1 2 X\n3 3 E", "2 2 E\n3 3 E"))
    val mowerControl = MowerControl(input, Map("participant" -> recordedMowerContract))

    val state1 = mowerControl.next()

    assertEquals(state1("participant"), Left("Invalid orientation: X"))

    val state2 = mowerControl.next()

    assertEquals(state2("participant"), Right(Seq(Position(2, 2, East), Position(3, 3, East))))
    assertEquals(
      recordedMowerContract.getInputs(1),
      """5 5
        |1 2 E
        |A
        |3 3 E
        |""".stripMargin
    )
