package name.lemerdy.sebastian.katas.adventofcode._2024.day04

import scala.util.Try

object CeresSearch:

  def countXMAS(input: String): Long =
    val lineWidth = input.linesIterator.toVector.headOption.map(_.length).getOrElse(0)
    def findWord = find(input, lineWidth)
    input.linesIterator.zipWithIndex
      .flatMap: (line, y) =>
        line.toVector.zipWithIndex.map: (c, x) =>
          Vector(
            findWord(x, y, 1, 0, "XMAS"),
            findWord(x, y, -1, 0, "XMAS"),
            findWord(x, y, 0, 1, "XMAS"),
            findWord(x, y, 0, -1, "XMAS"),
            findWord(x, y, 1, 1, "XMAS"),
            findWord(x, y, -1, 1, "XMAS"),
            findWord(x, y, -1, -1, "XMAS"),
            findWord(x, y, 1, -1, "XMAS")
          ).count(identity)
      .sum

  def countX_MAS(input: String): Long =
    val lineWidth = input.linesIterator.toVector.headOption.map(_.length).getOrElse(0)
    def findWord = find(input, lineWidth)
    input.linesIterator.zipWithIndex
      .flatMap: (line, y) =>
        line.toVector.zipWithIndex.map: (c, x) =>
          val found =
            (findWord(x - 1, y - 1, 1, 1, "MAS") || findWord(x - 1, y - 1, 1, 1, "SAM")) &&
              (findWord(x + 1, y - 1, -1, 1, "MAS") || findWord(x + 1, y - 1, -1, 1, "SAM"))
          if found then 1 else 0
      .sum

  private def find(input: String, lineWidth: Int)(x: Int, y: Int, dX: Int, dY: Int, word: String): Boolean =
    word.toVector.zipWithIndex.foldLeft(true):
      case (false, _)            => false
      case (true, (char, index)) => charAt(input, lineWidth)(x + index * dX, y + index * dY).contains(char)

  private def charAt(input: String, lineWidth: Int)(x: Int, y: Int): Option[Char] =
    Try(input.charAt(y * (lineWidth + 1) + x)).fold(_ => None, char => Some(char))
