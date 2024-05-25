import scala.annotation.tailrec

object Main extends App {


  case class Position(x: Int, y: Int)



  trait Orientation

  case object North extends Orientation
  case object East extends Orientation
  case object South extends Orientation
  case object West extends Orientation


  case class Mower(position: Position, orientation: Orientation)


  def turnLeft(mower: Mower): Mower = mower.copy(orientation = mower.orientation match {
    case North => West
    case West => South
    case South => East
    case East => North
  })

  def turnRight(mower: Mower): Mower = mower.copy(orientation = mower.orientation match {
    case North => East
    case East => South
    case South => West
    case West => North
  })


  def moveForward(mower: Mower, maxX: Int, maxY: Int, occupiedPositions: Set[Position]): Mower = {
    val newPosition = mower.orientation match {
      case North => mower.position.copy(y = Math.min(mower.position.y + 1, maxY))
      case East => mower.position.copy(x = Math.min(mower.position.x + 1, maxX))
      case South => mower.position.copy(y = Math.max(mower.position.y - 1, 0))
      case West => mower.position.copy(x = Math.max(mower.position.x - 1, 0))
    }
    if (occupiedPositions.contains(newPosition)) mower
    else mower.copy(position = newPosition)
  }

  def executeInstructions(mower: Mower, instructions: List[Char], maxX: Int, maxY: Int, occupiedPositions: Set[Position]): (Mower, Set[Position]) = {
    @tailrec
    def executeRec(mower: Mower, remainingInstructions: List[Char], occupiedPositions: Set[Position]): (Mower, Set[Position]) = {
      remainingInstructions match {
        case Nil => (mower, occupiedPositions)
        case instruction :: tail =>
          val newMower = instruction match {
            case 'G' => turnLeft(mower)
            case 'D' => turnRight(mower)
            case 'A' => moveForward(mower, maxX, maxY, occupiedPositions)
          }
          executeRec(newMower, tail, occupiedPositions - mower.position + newMower.position)
      }
    }

    executeRec(mower, instructions, occupiedPositions)
  }

  def parseInput(input: List[String]): List[Mower] = {
    @tailrec
    def parseRec(remainingData: List[String], maxX: Int, maxY: Int, mowers: List[Mower], occupiedPositions: Set[Position]): List[Mower] = {
      remainingData match {
        case Nil => mowers
        case pos :: instr :: tail =>
          val Array(x, y, orientation) = pos.split(" ")
          val initialMower = Mower(Position(x.toInt, y.toInt), orientation match {
            case "N" => North
            case "E" => East
            case "S" => South
            case "W" => West
          })
          val (finalMower, newOccupiedPositions) = executeInstructions(initialMower, instr.toList, maxX, maxY, occupiedPositions)
          parseRec(tail, maxX, maxY, mowers :+ finalMower, newOccupiedPositions)
        case _ => mowers // This case handles the situation where the input is malformed.
      }
    }
    input match {
      case maxCoords :: mowerData =>
        val Array(maxX, maxY) = maxCoords.split(" ").map(_.toInt)
        parseRec(mowerData, maxX, maxY, List.empty[Mower], Set.empty[Position])
      case _ => Nil
    }
  }
}