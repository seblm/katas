package mowitnow

import scala.util.matching.Regex

case class Position(x: Int, y: Int, orientation: Orientation)

object Position:

  private val positionRegex: Regex = """(\d+) (\d+) (.)""".r

  def fromString(line: String): Either[String, Position] =
    line match
      case positionRegex(x, y, orientation) => Orientation.fromChar(orientation.head).map(Position(x.toInt, y.toInt, _))
      case _                                => Left("Invalid mower position")

  def toString(position: Position): String = s"${position.x} ${position.y} ${position.orientation.toString.head}"
