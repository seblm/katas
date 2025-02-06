package test

import org.scalatest.funsuite.AnyFunSuite
import domain.{Lawn, Mower}
import domain.Orientation.{North, West, East, South}
import logic.MowerLogic

class LogicTest extends AnyFunSuite {
  test("Tondeuse tourne correctement à gauche") {
    assert(MowerLogic.turnLeft(North) == West)
  }

  test("Tondeuse tourne correctement à droite") {
    assert(MowerLogic.turnRight(West) == North)
  }

  test("Tondeuse avance correctement") {
    val mower = Mower(1, 2, North)
    val lawn = Lawn(5, 5)
    val movedMower = MowerLogic.moveForward(mower, lawn)

    assert(movedMower.y == 3)
  }
}
