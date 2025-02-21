package services

import models._

object MowerService {

  def moveMower(mower: Mower, lawn: Lawn): Position = {
    mower.instructions.foldLeft(mower.position) { (pos, instruction) =>
      executeInstruction(pos, instruction, lawn)
    }
  }

  private def executeInstruction(position: Position, instruction: Char, lawn: Lawn): Position = {
    instruction match {
      case 'G' => position.copy(orientation = turnLeft(position.orientation))
      case 'D' => position.copy(orientation = turnRight(position.orientation))
      case 'A' => moveForward(position, lawn)
      case _   => position
    }
  }

  private def turnLeft(orientation: Char): Char = {
    Map('N' -> 'W', 'W' -> 'S', 'S' -> 'E', 'E' -> 'N')(orientation)
  }

  private def turnRight(orientation: Char): Char = {
    Map('N' -> 'E', 'E' -> 'S', 'S' -> 'W', 'W' -> 'N')(orientation)
  }

  private def moveForward(position: Position, lawn: Lawn): Position = {
    val (newX, newY) = position.orientation match {
      // check en même temps si le mouvement est possible
      case 'N' if position.y < lawn.height => (position.x, position.y + 1)
      case 'S' if position.y > 0           => (position.x, position.y - 1)
      case 'E' if position.x < lawn.width  => (position.x + 1, position.y)
      case 'W' if position.x > 0           => (position.x - 1, position.y)
      case _                               => (position.x, position.y)
    }
    position.copy(x = newX, y = newY)
  }

  def processMowers(request: MowerRequest): MowerResponse = {
    val lawn = Lawn(request.width, request.height)
    val finalPositions = request.mowers.map(mower => {
      val finalPos = moveMower(mower, lawn)
      s"${finalPos.x} ${finalPos.y} ${finalPos.orientation}"  // convertion en string
    })
    MowerResponse(finalPositions)
  }
}