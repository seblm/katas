package example

import scala.collection.JavaConverters._

class Boggle(grid: String) {

  grid.lines.zipWithIndex.flatMap((line: String, y: Int) => line.toCharArray.toSeq.zipWithIndex.map { case (c: Char, x: Int) => (c, x, y) } )

  def boggle(word: String): Boolean = {

  }

}
