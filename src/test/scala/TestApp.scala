
object TestApp {
  def main(args: Array[String]): Unit = {
    val input =
      """
        |5 5
        |1 2 N
        |GAGAGAGAA
        |3 3 E
        |AADAADADDA
      """.stripMargin
    
    val lines = input.split("\n").toList

    val lawnEither = FileParser.parseLawn(lines.head)

    lawnEither match {
      case Right(lawn) =>
        val lawnmowers = FileParser.parseLawnmowers(lines.tail, lawn)
        var currentLawn = lawn
        lawnmowers.foreach { lawnmower =>
          val result = lawnmower.executeInstructions(currentLawn)
          result match {
            case Right(finalLawnmower) =>
              println(s"Final position: ${finalLawnmower.position.x} ${finalLawnmower.position.y} ${finalLawnmower.orientation}")
              // Ajouts d' assertions pour vérifier les positions finales
              assert(finalLawnmower.position.x == 1 && finalLawnmower.position.y == 3 && finalLawnmower.orientation == Orientation.North)
              assert(finalLawnmower.position.x == 5 && finalLawnmower.position.y == 1 && finalLawnmower.orientation == Orientation.East)
              currentLawn = lawn
            case Left(error) =>
              println(s"Error: $error")
          }
        }

      case Left(error) => println(s"Error parsing lawn: $error")
    }
  }
}