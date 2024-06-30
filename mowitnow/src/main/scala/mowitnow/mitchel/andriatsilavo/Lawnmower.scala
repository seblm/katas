package mowitnow.mitchel.andriatsilavo

case class Lawnmower(position: Position, orientation: Orientation, instructions: String) {
  def executeInstructions(lawn: Lawn): Either[String, Lawnmower] = {
    instructions.foldLeft(Right(this): Either[String, Lawnmower]) { (result, instruction) =>
      result.flatMap(lawnmower => lawnmower.move(instruction, lawn))
    }
  }

  def move(instruction: Char, lawn: Lawn): Either[String, Lawnmower] = {
    instruction match {
      case 'A' => lawn.moveForward(this)
      case 'D' => Right(this.copy(orientation = this.orientation.right))
      case 'G' => Right(this.copy(orientation = this.orientation.left))
      case _   => Left("Invalid instruction")
    }
  }
}
