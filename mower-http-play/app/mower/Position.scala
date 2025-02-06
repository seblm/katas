package mower

case class Position(x: Int, y: Int, orientation: Orientation):
  override def toString: String = s"$x $y $orientation"
