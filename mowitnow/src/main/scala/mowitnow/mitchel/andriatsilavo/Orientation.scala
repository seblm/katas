package mowitnow.mitchel.andriatsilavo

trait Orientation {
  def left: Orientation
  def right: Orientation
}

object Orientation {
  case object North extends Orientation {
    override def left: Orientation = West

    override def right: Orientation = East
  }

  case object East extends Orientation {
    override def left: Orientation = North

    override def right: Orientation = South
  }

  case object South extends Orientation {
    override def left: Orientation = East

    override def right: Orientation = West
  }

  case object West extends Orientation {
    override def left: Orientation = South

    override def right: Orientation = North
  }

  def withName(name: String): Orientation = name.toUpperCase match {
    case "N" => North
    case "E" => East
    case "S" => South
    case "W" => West
    case _   => throw new IllegalArgumentException(s"Invalid orientation: $name")
  }
}
