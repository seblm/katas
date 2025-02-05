package test

import org.scalatest.funsuite.AnyFunSuite
import domain.{Lawn, Mower, Orientation}
import logic.MowerLogic

class LogicTest extends AnyFunSuite {
  test("Tondeuse tourne correctement à gauche") {
    assert(MowerLogic.turnLeft(Orientation.North) == Orientation.West)
  }

  test("Tondeuse tourne correctement à droite") {
    assert(MowerLogic.turnRight(Orientation.West) == Orientation.North)
  }

  test("Tondeuse avance correctement") {
    val mower = Mower(1, 2, Orientation.North)
    val lawn = Lawn(5, 5)
    val movedMower = MowerLogic.moveForward(mower, lawn)

    assert(movedMower.y == 3)
  }
}
