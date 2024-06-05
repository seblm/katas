sealed trait Instruction

case object MoveForward extends Instruction
case object TurnLeft extends Instruction
case object TurnRight extends Instruction
