package mowitnow.mitchel.andriatsilavo

import scala.util.{Either, Left, Right}

object FileParser {
  def parseLawn(line: String): Either[String, Lawn] = {
    try {
      val Array(width, height) = line.split(" ").map(_.toInt)
      Right(Lawn(width, height))
    } catch {
      case e: Throwable => Left(s"Error parsing lawn: ${e.getMessage}")
    }
  }

  def parseLawnmowers(lines: List[String], lawn: Lawn): List[Lawnmower] = {
    lines.grouped(2).toList.flatMap {
      case List(positionLine, instructions) =>
        parseLawnmower(positionLine, instructions, lawn) match {
          case Right(lawnmower) => List(lawnmower)
          case Left(error) =>
            println(s"Error parsing lawnmower: $error")
            List.empty[Lawnmower]
        }
      case list =>
        println(s"Error when reading lines ${list.mkString(", ")}")
        List.empty[Lawnmower]
    }
  }

  def parseLawnmower(positionLine: String, instructions: String, lawn: Lawn): Either[String, Lawnmower] = {
    try {
      val positionParts = positionLine.split(" ")
      val orientation = Orientation.withName(positionParts(2))
      val Array(x, y) = positionParts.take(2).map(_.toInt)
      Right(Lawnmower(Position(x, y), orientation, instructions))
    } catch {
      case e: Throwable => Left(s"Error parsing lawnmower: ${e.getMessage}")
    }
  }
}
