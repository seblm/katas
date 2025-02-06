package mower

enum Orientation:
  case N, E, W, S

object Orientation:
  def left: Orientation => Orientation =
    case N => W
    case E => N
    case W => S
    case S => E

  def right: Orientation => Orientation =
    case N => E
    case E => S
    case W => N
    case S => W
