package mowitnow

import scala.util.Random
import scala.util.matching.Regex

case class Position(x: Int, y: Int, orientation: Orientation)

object Position:

  private val positionRegex: Regex = """(\d+) (\d+) ([NEWS])""".r

  def fromString(line: String): Either[String, Position] =
    line match
      case positionRegex(x, y, orientation) => Orientation.fromChar(orientation.head).map(Position(x.toInt, y.toInt, _))
      case unknownPosition                  => Left(s"""Invalid mower position: "$unknownPosition"""")

  def toString(position: Position): String = s"${position.x} ${position.y} ${position.orientation.toString.head}"

  def apply(topX: Int, topY: Int): Position =
    Position(Random.nextInt(topX + 1), Random.nextInt(topY + 1), Orientation.random())
