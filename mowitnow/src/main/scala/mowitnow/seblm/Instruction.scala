package mowitnow.seblm

enum Instruction:
  case LeftInstruction, RightInstruction, Forward

object Instruction:

  def fromChar(value: Char): Either[String, Instruction] = value match
    case 'G' => Right(LeftInstruction)
    case 'D' => Right(RightInstruction)
    case 'A' => Right(Forward)
    case _   => Left(s"""Invalid instruction: "$value"""")
