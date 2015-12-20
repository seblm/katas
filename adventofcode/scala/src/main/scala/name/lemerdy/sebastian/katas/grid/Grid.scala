package name.lemerdy.sebastian.katas.grid

import scala.io.Source

class Grid() {
  def countHousesWithAtLeastOnePresentNoRec(specification: String): Int = {
    val visited = scala.collection.mutable.Set[(Int, Int)]((0, 0))
    val directions: Iterator[Char] = specification.iterator
    var x = 0
    var y = 0
    while (directions.hasNext) {
      directions.next match {
        case '^' => y += 1
        case 'v' => y -= 1
        case '<' => x -= 1
        case '>' => x += 1
        case c => throw new IllegalArgumentException(s"$c is not accepted")
      }
      visited add (x, y)
    }
    visited.size
  }

  def countHousesWithAtLeastOnePresent(specification: String, x: Int = 0, y: Int = 0, visited: Set[(Int, Int)] = Set((0, 0))): Int = {
    specification match {
      case "" => (visited + ((x, y))).size
      case north if north startsWith "^" => countHousesWithAtLeastOnePresent(north.substring(1), x, y + 1, visited + ((x, y)))
      case south if south startsWith "v" => countHousesWithAtLeastOnePresent(south.substring(1), x, y - 1, visited + ((x, y)))
      case west if west startsWith "<" => countHousesWithAtLeastOnePresent(west.substring(1), x - 1, y, visited + ((x, y)))
      case est if est startsWith ">" => countHousesWithAtLeastOnePresent(est.substring(1), x + 1, y, visited + ((x, y)))
      case anotherString => throw new IllegalArgumentException(s"${anotherString.substring(0, 1)} is not accepted")
    }
  }

}

object Grid {

  def main(args: Array[String]) {
    println(new Grid().countHousesWithAtLeastOnePresentNoRec(Source.fromInputStream(getClass.getResourceAsStream("input")).mkString))
  }

}
