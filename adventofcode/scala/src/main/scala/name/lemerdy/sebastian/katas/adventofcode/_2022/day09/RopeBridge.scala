package name.lemerdy.sebastian.katas.adventofcode._2022.day09

import scala.annotation.tailrec
import scala.collection.immutable.Vector
import scala.jdk.StreamConverters.*
import scala.util.matching.Regex

object RopeBridge:

  def computePositions(input: String, size: Int): Long =
    computePositions(readDirections(input.lines().toScala(Iterator)), Vector.fill(size)(Coordinates(0, 0)))

  @tailrec
  private def readDirections(input: Iterator[String], directions: Vector[Direction] = Vector.empty): Vector[Direction] =
    if input.hasNext then
      input.next() match {
        case Down.regex(count)  => readDirections(input, directions.concat(Vector.fill(count.toInt)(Down)))
        case Left.regex(count)  => readDirections(input, directions.concat(Vector.fill(count.toInt)(Left)))
        case Right.regex(count) => readDirections(input, directions.concat(Vector.fill(count.toInt)(Right)))
        case Up.regex(count)    => readDirections(input, directions.concat(Vector.fill(count.toInt)(Up)))
      }
    else directions

  @tailrec
  private def computePositions(
      directions: Vector[Direction],
      rope: Vector[Coordinates],
      visited: Set[Coordinates] = Set.empty
  ): Long =
    if directions.isEmpty then visited.incl(rope.last).size
    else
      val futureRope = rope.tail.foldLeft(Vector(directions.head.move(rope.head)))((r, c) => r.appended(c.move(r.last)))
      computePositions(directions.tail, futureRope, visited.incl(rope.last))

  private case class Coordinates(row: Int, column: Int):
    def move(futureHead: Coordinates): Coordinates =
      (futureHead.row - row, futureHead.column - column) match {
        case (-2, -2)                     => Coordinates(futureHead.row + 1, futureHead.column + 1)
        case (-2, -1) | (-2, 0) | (-2, 1) => Coordinates(futureHead.row + 1, futureHead.column)
        case (2, -2)                      => Coordinates(futureHead.row - 1, futureHead.column + 1)
        case (-1, -2) | (0, -2) | (1, -2) => Coordinates(futureHead.row, futureHead.column + 1)
        case (2, 2)                       => Coordinates(futureHead.row - 1, futureHead.column - 1)
        case (-1, 2) | (0, 2) | (1, 2)    => Coordinates(futureHead.row, futureHead.column - 1)
        case (-2, 2)                      => Coordinates(futureHead.row + 1, futureHead.column - 1)
        case (2, -1) | (2, 0) | (2, 1)    => Coordinates(futureHead.row - 1, futureHead.column)
        case _                            => this
      }
  private sealed abstract class Direction(char: Char, delta: Coordinates):
    def regex: Regex = s"$char (\\d+)".r
    def move(coordinates: Coordinates): Coordinates =
      Coordinates(coordinates.row + delta.row, coordinates.column + delta.column)
  private object Down extends Direction('D', Coordinates(-1, 0))
  private object Left extends Direction('L', Coordinates(0, -1))
  private object Right extends Direction('R', Coordinates(0, 1))
  private object Up extends Direction('U', Coordinates(1, 0))
