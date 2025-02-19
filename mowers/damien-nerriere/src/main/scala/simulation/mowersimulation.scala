package simulation

import modele._
import io.circe.syntax._

object mowersimulation {

  def moveMower(mower: Mower, width: Int, height: Int): Mower = {
    val Position(x, y, direction) = mower.position
    val newPosition = direction match {
      case "N" if y < height => Position(x, y + 1, "N")
      case "S" if y > 0 => Position(x, y - 1, "S")
      case "E" if x < width => Position(x + 1, y, "E")
      case "W" if x > 0 => Position(x - 1, y, "W")
      case _ => mower.position
    }
    mower.copy(position = newPosition)
  }

  def rotateMower(mower: Mower, rotation: Char): Mower = {
    val newDirection = (mower.position.direction, rotation) match {
      case ("N", 'G') => "W"
      case ("N", 'D') => "E"
      case ("S", 'G') => "E"
      case ("S", 'D') => "W"
      case ("E", 'G') => "N"
      case ("E", 'D') => "S"
      case ("W", 'G') => "S"
      case ("W", 'D') => "N"
      case _ => mower.position.direction
    }
    mower.copy(position = mower.position.copy(direction = newDirection))
  }

  def executeInstructions(mower: Mower, width: Int, height: Int): Mower = {
    mower.instructions.foldLeft(mower) {
      case (currentMower, 'A') => moveMower(currentMower, width, height)
      case (currentMower, instruction @ ('G' | 'D')) => rotateMower(currentMower, instruction)
      case (currentMower, _) => currentMower
    }
  }

  def runSimulation(simulation: Simulation): Map[String, List[String]] = {
    val finalPositions = simulation.mowers.foldLeft(List.empty[String]) { (positions, mower) =>
      val finalMower = executeInstructions(mower, simulation.width, simulation.height)
      positions :+ s"${finalMower.position.x} ${finalMower.position.y} ${finalMower.position.direction}"
    }
    Map("finalPositions" -> finalPositions)
  }
}
