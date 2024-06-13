package mowitnow

case class Mower(initialPosition: Position, instructions: List[Instruction])

object Mower:

  def toString(mower: Mower): String =
    Position.toString(mower.initialPosition) + mower.instructions.map(Instruction.toString).mkString("\n", "\n", "")
