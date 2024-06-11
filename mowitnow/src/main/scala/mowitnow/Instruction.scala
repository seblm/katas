package mowitnow

enum Instruction: // TODO encode char value ?
  case LeftInstruction, RightInstruction, Forward

object Instruction:

  def fromChar(value: Char): Either[String, Instruction] = value match
    case 'G' => Right(LeftInstruction)
    case 'D' => Right(RightInstruction)
    case 'A' => Right(Forward)
    case _   => Left(s"Invalid instruction: $value")

  def toString(instruction: Instruction): String = instruction match
    case LeftInstruction  => "G"
    case RightInstruction => "D"
    case Forward          => "A"
