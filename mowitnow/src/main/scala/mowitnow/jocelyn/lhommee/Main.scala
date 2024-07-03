package mowitnow.jocelyn.lhommee

import mowitnow.MowerContract

import scala.annotation.tailrec
import scala.util.{Try, Success, Failure}

object Main extends App with MowerContract {

  enum Orientation:
    case North, East, South, West

  case class Position(x: Int, y: Int)

  case class Mower(position: Position, orientation: Orientation)

  def turnLeft(mower: Mower): Mower = mower.copy(orientation = mower.orientation match {
    case Orientation.North => Orientation.West
    case Orientation.West  => Orientation.South
    case Orientation.South => Orientation.East
    case Orientation.East  => Orientation.North
  })

  def turnRight(mower: Mower): Mower = mower.copy(orientation = mower.orientation match {
    case Orientation.North => Orientation.East
    case Orientation.East  => Orientation.South
    case Orientation.South => Orientation.West
    case Orientation.West  => Orientation.North
  })

  def moveForward(mower: Mower, maxX: Int, maxY: Int, occupiedPositions: Set[Position]): Mower = {
    val newPosition = mower.orientation match {
      case Orientation.North => mower.position.copy(y = Math.min(mower.position.y + 1, maxY))
      case Orientation.East  => mower.position.copy(x = Math.min(mower.position.x + 1, maxX))
      case Orientation.South => mower.position.copy(y = Math.max(mower.position.y - 1, 0))
      case Orientation.West  => mower.position.copy(x = Math.max(mower.position.x - 1, 0))
    }
    if (occupiedPositions.contains(newPosition)) mower
    else mower.copy(position = newPosition)
  }

  def executeInstructions(
      mower: Mower,
      instructions: List[Char],
      maxX: Int,
      maxY: Int,
      occupiedPositions: Set[Position]
  ): (Mower, Set[Position]) = {
    @tailrec
    def executeRec(
        mower: Mower,
        remainingInstructions: List[Char],
        occupiedPositions: Set[Position]
    ): (Mower, Set[Position]) = {
      remainingInstructions match {
        case Nil => (mower, occupiedPositions)
        case instruction :: tail =>
          val newMower = instruction match {
            case 'G' => turnLeft(mower)
            case 'D' => turnRight(mower)
            case 'A' => moveForward(mower, maxX, maxY, occupiedPositions)
            case _   => mower // Ignore invalid instructions
          }
          executeRec(newMower, tail, occupiedPositions - mower.position + newMower.position)
      }
    }

    executeRec(mower, instructions, occupiedPositions)
  }

  def parseInput(input: List[String]): Either[String, (Int, Int, List[(Mower, List[Char])])] = {
    input match {
      case maxCoords :: mowerData =>
        val coords = maxCoords.split(" ").flatMap(s => Try(s.toInt).toOption)
        if (coords.length != 2) Left("Invalid lawn dimensions")
        else {
          val (maxX, maxY) = (coords(0), coords(1))
          val mowers = mowerData
            .grouped(2)
            .flatMap {
              case List(pos, instr) =>
                val parts = pos.split(" ")
                if (parts.length != 3) None
                else {
                  val xOption = Try(parts(0).toInt).toOption
                  val yOption = Try(parts(1).toInt).toOption
                  val orientationOption = parts(2) match {
                    case "N" => Some(Orientation.North)
                    case "E" => Some(Orientation.East)
                    case "S" => Some(Orientation.South)
                    case "W" => Some(Orientation.West)
                    case _   => None
                  }

                  for {
                    x <- xOption.toRight(s"Invalid x coordinate: ${parts(0)}").toOption
                    y <- yOption.toRight(s"Invalid y coordinate: ${parts(1)}").toOption
                    orientation <- orientationOption.toRight(s"Invalid orientation: ${parts(2)}").toOption
                  } yield (Mower(Position(x, y), orientation), instr.trim.toList)
                }
              case _ => None
            }
            .toList

          if (mowers.isEmpty) Left("Invalid mower positions or orientations")
          else Right((maxX, maxY, mowers))
        }
      case _ => Left("Invalid input format")
    }
  }

  def simulateMowers(maxX: Int, maxY: Int, mowers: List[(Mower, List[Char])]): List[Mower] = {
    @tailrec
    def simulateRec(
        remainingMowers: List[(Mower, List[Char])],
        completedMowers: List[Mower],
        occupiedPositions: Set[Position]
    ): List[Mower] = {
      remainingMowers match {
        case Nil => completedMowers
        case (mower, instructions) :: tail =>
          val (finalMower, newOccupiedPositions) =
            executeInstructions(mower, instructions, maxX, maxY, occupiedPositions)
          simulateRec(tail, completedMowers :+ finalMower, newOccupiedPositions)
      }
    }

    simulateRec(mowers, Nil, Set.empty[Position])
  }

  // Exemple d'utilisation de l'application :
  val input: List[String] = List(
    "5 5",
    "1 2 N",
    "GAGAGAGAA",
    "3 3 E",
    "AADAADADDA"
  )

  parseInput(input) match {
    case Left(error) =>
      println(s"Erreur lors de l'analyse de l'entrée : $error")
    case Right((maxX, maxY, mowers)) =>
      val finalPositions = simulateMowers(maxX, maxY, mowers)
      finalPositions.foreach { mower =>
        println(s"${mower.position.x} ${mower.position.y} ${mower.orientation}")
      }
  }

  override def computeFinalPositions(input: String): String =
    println(input)
    def orientationToString(orientation: Orientation): String = orientation match
      case Orientation.North => "N"
      case Orientation.East  => "E"
      case Orientation.West  => "W"
      case Orientation.South => "S"
    parseInput(input.split("\n").toList) match
      case Left(error) =>
        s"Erreur lors de l'analyse de l'entrée : $error"
      case Right((maxX, maxY, mowers)) =>
        val finalPositions = simulateMowers(maxX, maxY, mowers)
        finalPositions
          .map: mower =>
            s"${mower.position.x} ${mower.position.y} ${orientationToString(mower.orientation)}"
          .mkString("\n")

}
