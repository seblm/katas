package domain

// Orientation de la tondeuse
// sealed sert à définir un ensemble fini de valeurs possibles
sealed trait Orientation

object Orientation {
  case object North extends Orientation
  case object East extends Orientation
  case object South extends Orientation
  case object West extends Orientation

  // Conversion Char <-> Orientation
  def fromChar(c: Char): Orientation = c match {
    case 'N' => North
    case 'E' => East
    case 'S' => South
    case 'W' => West
    case _   => throw new IllegalArgumentException(s"Orientation invalide: $c")
  }

  // Conversion Orientation -> Char
  def toChar(o: Orientation): Char = o match {
    case North => 'N'
    case East  => 'E'
    case South => 'S'
    case West  => 'W'
  }
}
