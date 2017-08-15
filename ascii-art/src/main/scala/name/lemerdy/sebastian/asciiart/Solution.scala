package name.lemerdy.sebastian.asciiart

import scala.io.StdIn.{readInt, readLine}
import scala.util._

object Solution extends App {

  case class Letter(lines: List[String])

  val w = readInt
  val h = readInt
  val t = readLine

  def letters(i: Int, list: List[Letter]): List[Letter] =
    if (i == h)
      list
    else
      letters(i + 1, readLine.grouped(w).toList.zip(list).map { case (line: String, letter: Letter) => Letter(letter.lines :+ line) })

  val lettersFromConsole = letters(0, (0 to 27).map(_ => Letter(List.empty[String])).toList)

  val tAsLetters: Seq[Letter] = t.toUpperCase()
    .map(c => c.toInt)
    .map(asciiCode => Try(lettersFromConsole(asciiCode - 'A'.toInt)).getOrElse(lettersFromConsole.last))

  println((0 until h).map(y => tAsLetters.map(_.lines(y)).mkString).mkString("\n"))

}
