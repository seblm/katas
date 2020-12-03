package name.lemerdy.sebastian.katas.adventofcode._2020.day3

import scala.annotation.tailrec

object TobogganTrajectory {

  def countTrees(slope: Slope, map: String): Long =
    traverse(slope, treeCountCharacter(map(0)), map.split('\n').toList, 0)

  def countTrees(slopes: List[Slope], map: String): Long =
    slopes.map(slope => traverse(slope, treeCountCharacter(map(0)), map.split('\n').toList, 0)).product

  @tailrec
  private def traverse(slope: Slope, treeCount: Long, map: List[String], currentX: Int): Long =
    map match {
      case Nil                                             => treeCount
      case tooSmallMap if tooSmallMap.length <= slope.down => treeCount
      case currentLine :: _ =>
        val newX = (currentX + slope.right) % currentLine.length
        val newPosition = map(slope.down).charAt(newX)
        traverse(slope, treeCount + treeCountCharacter(newPosition), map.drop(slope.down), newX)
    }

  private def treeCountCharacter(character: Char): Int = if (character == '.') 0 else 1

}
