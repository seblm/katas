package mowitnow

import munit.FunSuite
import mowitnow.Orientation.{North, East}

class MowerControlSuite extends FunSuite:

  test("should split instructions"):
    val input = """5 5
                  |1 2 N
                  |GAGAGAGAA
                  |3 3 E
                  |AADAADADDA""".stripMargin
    val recordedMowerContract = new RecordedMowerContract(Seq("""1 2 E
                                                                |3 4 E""".stripMargin))
    val mowerControl = MowerControl(input, Map("participant" -> recordedMowerContract))

    val state = mowerControl.next()

    assertEquals(state("participant"), Right(Seq(Position(1, 2, East), Position(3, 4, East))))
    assertEquals(
      recordedMowerContract.getInputs,
      List("""5 5
             |1 2 N
             |G
             |3 3 E
             |A""".stripMargin)
    )

  test("should handle participant failure"):
    val input = """5 5
                  |1 2 N
                  |GAGAGAGAA
                  |3 3 E
                  |AADAADADDA""".stripMargin
    val recordedMowerContract = new RecordedMowerContract(
      Seq(
        """1 2 E
          |3 4 X""".stripMargin,
        """0 2 N
          |3 4 E""".stripMargin
      )
    )
    val mowerControl = MowerControl(input, Map("participant" -> recordedMowerContract))

    val state = mowerControl.next()

    assertEquals(state("participant"), Left("Invalid orientation: X"))

    val state2 = mowerControl.next()

    assertEquals(state2("participant"), Right(Seq(Position(0, 2, North), Position(3, 4, East))))
    assertEquals(recordedMowerContract.getInputs(1), "5 5\n1 2 N\nA\n3 3 E\nA")
