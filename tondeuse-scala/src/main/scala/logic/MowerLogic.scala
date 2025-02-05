package logic

import domain.{Lawn, Mower, Orientation}
import domain.{West, South, East, North}

object MowerLogic {
  def turnLeft(o: Orientation): Orientation = o match {
    case North => West
    case West  => South
    case South => East
    case East  => North
  }

  def turnRight(o: Orientation): Orientation = o match {
    case North => East
    case East  => South
    case South => West
    case West  => North
  }

  def moveForward(m: Mower, lawn: Lawn): Mower = {
    m.orientation match {
      case North if m.y < lawn.height => m.copy(y = m.y + 1)
      case South if m.y > 0           => m.copy(y = m.y - 1)
      case East if m.x < lawn.width   => m.copy(x = m.x + 1)
      case West if m.x > 0            => m.copy(x = m.x - 1)
      case _ => m
    }
  }

  def applyInstruction(m: Mower, instr: Char, lawn: Lawn): Mower = instr match {
    case 'G' => m.copy(orientation = turnLeft(m.orientation))
    case 'D' => m.copy(orientation = turnRight(m.orientation))
    case 'A' => moveForward(m, lawn)
    case _   => m
  }

  def processMowers(lawn: Lawn, mowers: List[(Mower, String)]): List[Mower] = {
    mowers.map { case (mower, instructions) =>
      instructions.foldLeft(mower)((m, instr) => applyInstruction(m, instr, lawn))
    }
  }
}
