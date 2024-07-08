package mowitnow

import mowitnow.Orientation.{East, North}
import munit.FunSuite

class MowerControlSuite extends FunSuite:
/*
  test("should keep only first instruction"):
    val input = """5 5
                  |1 2 N
                  |GAGAGAGAA
                  |3 3 E
                  |AADAADADDA""".stripMargin
    val recordedMowerContract = new RecordedMowerContract(Seq("1 2 E"))
    val mowerControl = MowerControl(input, Map("participant" -> recordedMowerContract))

    val state = Range(0, "G".length).map(_ => mowerControl.next()).last

    assertEquals(state("participant"), Right(Seq(Position(1, 2, East))))
    assertEquals(
      recordedMowerContract.getInputs,
      List("""5 5
             |1 2 N
             |G""".stripMargin)
    )

  test("should advance to next instruction"):
    val input = """5 5
                  |1 2 N
                  |GAGAGAGAA
                  |3 3 E
                  |AADAADADDA""".stripMargin
    val recordedMowerContract = new RecordedMowerContract(Seq("1 2 E", "0 2 E"))
    val mowerControl = MowerControl(input, Map("participant" -> recordedMowerContract))

    val state = Range(0, "GA".length).map(_ => mowerControl.next()).last

    assertEquals(state("participant"), Right(Seq(Position(0, 2, East))))
    assertEquals(
      recordedMowerContract.getInputs.last,
      """5 5
        |1 2 E
        |A""".stripMargin
    )

  test("should handle participant failure"):
    val input = """5 5
                  |1 2 N
                  |GAGAGAGAA
                  |3 3 E
                  |AADAADADDA""".stripMargin
    val recordedMowerContract = new RecordedMowerContract(Seq("1 2 X", "1 3 N"))
    val mowerControl = MowerControl(input, Map("participant" -> recordedMowerContract))

    val state1 = mowerControl.next()

    assertEquals(state1("participant"), Left("Invalid orientation: X"))

    val state2 = mowerControl.next()

    assertEquals(state2("participant"), Right(Seq(Position(1, 3, North))))
    assertEquals(
      recordedMowerContract.getInputs(1),
      """5 5
        |1 2 E
        |A""".stripMargin
    )
*/
  test("should keep last instruction of first mower"):
    val input = """5 5
                  |1 2 N
                  |GAGAGAGAA
                  |3 3 E
                  |AADAADADDA""".stripMargin
    val recordedMowerContract = new RecordedMowerContract(Seq("1 2 E", "0 2 E", "0 2 S", "0 1 S", "0 1 W", "1 1 W", "1 1 N", "1 2 N", "1 3 N", "1 3 N\n4 3 E"))
    val mowerControl = MowerControl(input, Map("participant" -> recordedMowerContract))

    val state = Range(0, ("GAGAGAGAA" + "A").length).map(_ => mowerControl.next()).last

    assertEquals(state("participant"), Right(Seq(Position(1, 3, North), Position(4, 3, East))))
    assertEquals(
      recordedMowerContract.getInputs.last,
      """5 5
        |1 2 N
        |A
        |3 3 E
        |A""".stripMargin
    )
/*
  test("should go beyond last instruction"):
    val input =
      """5 5
        |1 2 N
        |GAGAGAGAA
        |3 3 E
        |AADAADADDA""".stripMargin
    val recordedMowerContract = new RecordedMowerContract(Seq("1 3 N\n5 1 E"))
    val mowerControl = MowerControl(input, Map("participant" -> recordedMowerContract))

    Range(0, ("GAGAGAGAA" + "AADAADADDA").length + 1).foreach(_ => mowerControl.next())

    assertEquals(
      recordedMowerContract.getInputs.last,
      """5 5
        |1 2 N
        |A
        |4 1 E
        |A""".stripMargin
    )
*/