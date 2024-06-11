package mowitnow

import munit.FunSuite

class RecordedMowerContractSuite extends FunSuite:

  test("getInputs should be a copy"):
    val recorderMower = new RecordedMowerContract()
    recorderMower.computeFinalPositions("a")
    recorderMower.computeFinalPositions("b")
    val inputs = recorderMower.getInputs
    assertEquals(inputs, List("a", "b"))
    recorderMower.computeFinalPositions("c")
    assertEquals(inputs, List("a", "b"))
    assertEquals(recorderMower.getInputs, List("a", "b", "c"))
