package mowitnow.mitchel.andriatsilavo

import Orientation._
case class Position(x: Int, y: Int) {
  def move(orientation: Orientation): Position = {
    orientation match {
      case North => this.copy(y = y + 1)
      case East  => this.copy(x = x + 1)
      case South => this.copy(y = y - 1)
      case West  => this.copy(x = x - 1)
    }
  }
}
