package name.lemerdy.sebastian.katas.adventofcode._2024.day06

import scala.annotation.tailrec
import scala.collection.mutable
import scala.util.Try

object GuardGallivant:

  private case class Position(x: Int, y: Int)

  private enum Direction:
    case North, East, South, West

  private def turnRight: Direction => Direction =
    case Direction.North => Direction.East
    case Direction.East  => Direction.South
    case Direction.South => Direction.West
    case Direction.West  => Direction.North

  def countDistinctPositions(input: String): Int =
    val lineWidth = input.linesIterator.toVector.headOption.map(_.length).getOrElse(0)
    val initialPosition = find(input, lineWidth, '^')
    val visited = mutable.HashSet[Position]()
    var exit: Boolean = false
    var currentPosition = initialPosition
    var currentDirection = Direction.North
    var i = 0
    while !exit && i <= 6027 do
      i += 1
      visited.add(currentPosition)
      println(s"$i ${visited.size}")
      val nextPosition = currentDirection match
        case Direction.North => currentPosition.copy(y = currentPosition.y - 1)
        case Direction.East  => currentPosition.copy(x = currentPosition.x + 1)
        case Direction.South => currentPosition.copy(y = currentPosition.y + 1)
        case Direction.West  => currentPosition.copy(x = currentPosition.x - 1)
      val nextChar = charAt(input, lineWidth)(nextPosition.x, nextPosition.y)
      if (i == 6026)
        println(s"currentDirection: $currentDirection")
        println(s"currentPosition: $currentPosition")
        println(s"nextPosition: $nextPosition")
        println(s"nextChar: $nextChar")
        printMap(input, lineWidth, visited, currentDirection, currentPosition)
      nextChar match
        case None      => exit = true
        case Some('#') => currentDirection = turnRight(currentDirection)
        case _         => currentPosition = nextPosition
    printMap(input, lineWidth, visited, currentDirection, currentPosition)
    visited.size

  private def find(input: String, lineWidth: Int, char: Char): Position =
    val index = input.indexOf(char)
    val y = index / (lineWidth + 1)
    Position(index - (y * (lineWidth + 1)), y)

  private def charAt(input: String, lineWidth: Int)(x: Int, y: Int): Option[Char] =
    Try(input.charAt(y * (lineWidth + 1) + x)).fold(_ => None, char => Some(char))

  private def printMap(
      input: String,
      lineWidth: Int,
      visited: mutable.HashSet[Position],
      currentDirection: Direction,
      currentPosition: Position
  ): Unit =
    val height = input.count(_ == '\n') + 1
    Range(0, height).foreach: y =>
      Range(0, lineWidth).foreach: x =>
        val p = Position(x, y)
        print(
          if currentPosition == p then
            currentDirection match
              case Direction.North => '^'
              case Direction.East  => '>'
              case Direction.South => 'v'
              case Direction.West  => '<'
          else if visited.contains(Position(x, y)) then 'X'
          else charAt(input, lineWidth)(x, y).get
        )
      println()
