package services

import models._

object MowerService {
  // Lecture des instructions de manière séquentielle
  def executeInstructions(mower: Position, instructions: String, lawn: Lawn): Position = {
    instructions.foldLeft(mower) {
      case (pos, 'G') => pos.gauche
      case (pos, 'D') => pos.droite
      case (pos, 'A') => pos.avancer(lawn)
      case (pos, _)   => pos
    }
  }
}