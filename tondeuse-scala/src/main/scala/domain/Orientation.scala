package domain


sealed trait Orientation
case object North extends Orientation
case object East  extends Orientation
case object South extends Orientation
case object West  extends Orientation

object Orientation {
  // pour convertir 'N', 'E', 'S', 'W' en type Orientation
  def fromChar(c: Char): Orientation = c match {
    case 'N' => North
    case 'E' => East
    case 'S' => South
    case 'W' => West
    case _   => throw new IllegalArgumentException(s"Invalid orientation: $c")
  }

  // pour retransformer l'Orientation en un Char
  def toChar(o: Orientation): Char = o match {
    case North => 'N'
    case East  => 'E'
    case South => 'S'
    case West  => 'W'
  }
}
