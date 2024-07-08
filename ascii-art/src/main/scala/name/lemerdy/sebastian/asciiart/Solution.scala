package name.lemerdy.sebastian.asciiart

import scala.annotation.tailrec
import scala.io.StdIn.{readInt, readLine}
import scala.util.*

object Solution:

  case class Letter(lines: List[String])

  @main def printArt(args: String*): Unit =
    val w = readInt
    val h = readInt
    val t = readLine

    @tailrec def letters(i: Int, list: List[Letter]): List[Letter] =
      if i == h then list
      else
        letters(
          i + 1,
          readLine
            .grouped(w)
            .toList
            .zip(list)
            .map:
              case (line: String, letter: Letter) =>
                Letter(letter.lines :+ line)
        )

    val lettersFromConsole = letters(0, (0 to 27).map(_ => Letter(List.empty[String])).toList)

    val tAsLetters: Seq[Letter] = t
      .toUpperCase()
      .map(c => c.toInt)
      .map(asciiCode => Try(lettersFromConsole(asciiCode - 'A'.toInt)).getOrElse(lettersFromConsole.last))

    println((0 until h).map(y => tAsLetters.map(_.lines(y)).mkString).mkString("\n"))
