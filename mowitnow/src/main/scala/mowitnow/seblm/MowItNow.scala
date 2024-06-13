package mowitnow.seblm

import cats.syntax.traverse.given
import mowitnow.MowerContract
import mowitnow.seblm.Instruction.{Forward, LeftInstruction, RightInstruction}
import mowitnow.seblm.Orientation.*

import scala.Function.const
import scala.annotation.tailrec
import scala.util.matching.Regex

object MowItNow extends MowerContract:

  override def computeFinalPositions(input: String): String =
    parseGarden(input).map(computePositions(_)).fold(identity, _.map(Position.toString).mkString("\n"))

  def parseGarden(input: String): Either[String, Garden] =
    val dimensions: Regex = """(\d+) (\d+)""".r
    val lines = input.split("\n").toList
    for
      (width, height) <- lines.head match
        case dimensions(width, height) => Right((width.toInt, height.toInt))
        case unparseableDimension      => Left(s"""Invalid dimensions: "$unparseableDimension"""")
      mowers <- lines.tail
        .grouped(2)
        .map: twoLines =>
          for
            firstLine <- twoLines.headOption.toRight("position is empty")
            secondLine = twoLines.applyOrElse(1, const(""))
            mower <- parseMower(firstLine, secondLine)
          yield mower
        .toList
        .sequence
    yield Garden(width, height, mowers)

  private def parseMower(position: String, inputInstructions: String): Either[String, Mower] =
    for
      initialPosition <- Position.fromString(position)
      instructions <- inputInstructions.map(Instruction.fromChar).toList.sequence
    yield Mower(initialPosition, instructions)

  @tailrec
  private def computePositions(garden: Garden, positions: List[Position] = List.empty): List[Position] =
    garden.mowers match
      case Nil           => positions
      case mower :: tail => computePositions(garden.copy(mowers = tail), positions :+ computePosition(garden, mower))

  private def computePosition(garden: Garden, mower: Mower): Position =
    mower.instructions.foldLeft(mower.initialPosition): (position, instruction) =>
      instruction match
        case LeftInstruction  => position.copy(orientation = turnLeft(position))
        case RightInstruction => position.copy(orientation = turnRight(position))
        case Forward          => moveForward(garden, position)

  private def moveForward(garden: Garden, position: Position): Position =
    position match
      case Position(x, y, North) => position.copy(y = Math.min(y + 1, garden.height))
      case Position(x, y, East)  => position.copy(x = Math.min(x + 1, garden.width))
      case Position(x, y, South) => position.copy(y = Math.max(y - 1, 0))
      case Position(x, y, West)  => position.copy(x = Math.max(x - 1, 0))
