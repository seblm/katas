package mowitnow.mitchel.andriatsilavo

object TestApp {
  def main(args: Array[String]): Unit = {
    val input =
      """5 5
        |1 2 N
        |GAGAGAGAA
        |3 3 E
        |AADAADADDA""".stripMargin

    val lines = input.split("\n").toList

    val lawnEither = FileParser.parseLawn(lines.head)

    lawnEither match {
      case Right(lawn) =>
        val lawnmowers = FileParser.parseLawnmowers(lines.tail, lawn)
        var currentLawn = lawn
        lawnmowers.zip(Vector((1, 3, Orientation.North), (5, 1, Orientation.East))).foreach { (lawnmower, expected) =>
          val result = lawnmower.executeInstructions(currentLawn)
          result match {
            case Right(finalLawnmower) =>
              println(
                s"Final position: ${finalLawnmower.position.x} ${finalLawnmower.position.y} ${finalLawnmower.orientation}"
              )
              // Ajouts d' assertions pour vérifier les positions finales
              assert(
                finalLawnmower.position.x == expected._1 && finalLawnmower.position.y == expected._2 && finalLawnmower.orientation == expected._3
              )
              currentLawn = lawn
            case Left(error) =>
              println(s"Error: $error")
          }
        }

      case Left(error) => println(s"Error parsing lawn: $error")
    }
  }
}
