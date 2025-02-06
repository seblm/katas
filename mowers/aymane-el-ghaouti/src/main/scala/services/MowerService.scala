package services

import models.Lawn

object MowerService {
  type Position = (Int, Int, Char)

  def moveMower(lawn: Lawn, position: Position, instructions: String): Position = {
    instructions.foldLeft(position) { case ((x, y, dir), cmd) =>
      cmd match {
        case 'G' => (x, y, left(dir))
        case 'D' => (x, y, right(dir))
        case 'A' => moveForward(lawn, x, y, dir)
        case _   => (x, y, dir)
      }
    }
  }

  def left(dir: Char): Char = dir match {
    case 'N' => 'W'
    case 'W' => 'S'
    case 'S' => 'E'
    case 'E' => 'N'
  }

  def right(dir: Char): Char = dir match {
    case 'N' => 'E'
    case 'E' => 'S'
    case 'S' => 'W'
    case 'W' => 'N'
  }

  def moveForward(lawn: Lawn, x: Int, y: Int, dir: Char): Position = {
    val (nx, ny) = dir match {
      case 'N' if y < lawn.height => (x, y + 1)
      case 'S' if y > 0           => (x, y - 1)
      case 'E' if x < lawn.width  => (x + 1, y)
      case 'W' if x > 0           => (x - 1, y)
      case _                      => (x, y)
    }
    (nx, ny, dir)
  }
}
