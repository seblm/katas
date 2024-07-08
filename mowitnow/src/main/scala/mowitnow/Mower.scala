package mowitnow

case class Mower(initialPosition: Position, instructions: List[Instruction])

object Mower:

  def toString(mower: Mower): String =
    Position.toString(mower.initialPosition) + "\n" + mower.instructions.map(Instruction.toString).mkString
