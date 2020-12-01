package name.lemerdy.sebastian.katas.notquitelisp

import scala.io.Source

class NotQuiteLisp {
  def santaPositionForBasement(instructions: String): Int = {
    santaPositionForBasement(instructions.toList)
  }

  private def santaPositionForBasement(instructions: List[Char], level: Int = 0, position: Int = 0): Int = {
    if (level == -1) {
      position
    } else {
      instructions match {
        case Nil => throw new IllegalArgumentException("santa has never reach basement")
        case '(' :: tail => santaPositionForBasement(tail, level + 1, position + 1)
        case ')' :: tail => santaPositionForBasement(tail, level - 1, position + 1)
        case c :: tail => throw new IllegalArgumentException(s"character $c at position $position is not known")
      }
    }
  }

  def santaLastLevel(instructions: String): Int = {
    instructions.map {
      case '(' => 1
      case ')' => -1
      case _ => 0
    }.sum
  }

}

object NotQuiteLisp {
  def main(args: Array[String]): Unit = {
    val notQuiteLisp = new NotQuiteLisp()
    val input = Source.fromInputStream(getClass.getResourceAsStream("input")).mkString

    println(s"${notQuiteLisp.santaLastLevel(input)} ${notQuiteLisp.santaPositionForBasement(input)}")
  }
}