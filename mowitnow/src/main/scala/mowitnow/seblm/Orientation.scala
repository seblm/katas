package mowitnow.seblm

enum Orientation:
  case North, East, South, West

object Orientation:

  def fromChar(value: Char): Either[String, Orientation] =
    value match
      case 'N' => Right(North)
      case 'E' => Right(East)
      case 'S' => Right(South)
      case 'W' => Right(West)
      case _   => Left(s"Invalid orientation: $value")

  def turnLeft(position: Position): Orientation =
    position.orientation match
      case North => West
      case West  => South
      case South => East
      case East  => North

  def turnRight(position: Position): Orientation =
    position.orientation match
      case North => East
      case East  => South
      case South => West
      case West  => North
