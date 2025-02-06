package services

import org.scalatest.funsuite.AnyFunSuite
import models._

class MowerServiceTest extends AnyFunSuite {

  test("Mower should move correctly") {
    val lawn = Lawn(6, 6)  
    val initialPosition = (1, 2, 'N')
    val instructions = "GAGAGAGAA"

    val finalPosition = MowerService.moveMower(lawn, initialPosition, instructions)

    assert(finalPosition == (1, 3, 'N'))
  }

  test("Mower should not move outside the lawn") {
    val lawn = Lawn(6, 6)  
    val initialPosition = (0, 0, 'S')
    val instructions = "A"

    val finalPosition = MowerService.moveMower(lawn, initialPosition, instructions)

    assert(finalPosition == (0, 0, 'S'))
  }
}
