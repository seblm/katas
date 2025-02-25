package models
import play.api.libs.json._

case class Mower(position : String, instructions : String){

  def getFinalPosition(lawnWidth : Int, lawnHeight : Int) : String = {
    var pos : (Int, Int, Char) = getTuplePosition
    instructions.foreach {
      case char@('D' | 'G') => pos = (pos._1, pos._2, rotate(pos._3, char))
      case 'A' => try {
        pos = moveForward(lawnWidth, lawnHeight, pos)
      } catch {
        case e: Exception => return s"${pos._1} ${pos._2} ${pos._3}"
      }
    }

    s"${pos._1} ${pos._2} ${pos._3}"

  }

  private def moveForward(lawnWidth : Int, lawnHeight : Int, pos : (Int, Int, Char)) : (Int, Int, Char) = {
    pos._3 match {
      case 'N' => if((pos._2 + 1) > lawnHeight) throw new IndexOutOfBoundsException("En dehors de la pelouse") else (pos._1, pos._2 + 1, pos._3)
      case 'S' => if((pos._2 - 1) < 0) throw new IndexOutOfBoundsException("En dehors de la pelouse") else (pos._1, pos._2 - 1, pos._3)

      case 'E' => if((pos._1 + 1) > lawnWidth) throw new IndexOutOfBoundsException("En dehors de la pelouse") else (pos._1 + 1, pos._2, pos._3)
      case 'W' => if((pos._1 - 1) < 0) throw new IndexOutOfBoundsException("En dehors de la pelouse") else (pos._1 - 1, pos._2, pos._3)
    }
  }

  private def rotate(orientation : Char, instruction: Char) : Char = {
    val cardinalPoints : List[Char] = List('N', 'E', 'S', 'W')

    val indexActualOrientation = cardinalPoints.indexOf(orientation)

    instruction match {
      case 'D' => if (indexActualOrientation == 3) 'N' else cardinalPoints(indexActualOrientation + 1)
      case 'G' => if (indexActualOrientation == 0)  'W' else cardinalPoints(indexActualOrientation - 1)
    }
  }

  private def getTuplePosition : (Int, Int, Char) = {
    val pos : Array[String] = this.position.split(" ")
    (pos(0).toInt, pos(1).toInt, pos(2).charAt(0))
  }

}

object Mower {
  implicit val mowerFormat: Format[Mower] = Json.format[Mower]
}

