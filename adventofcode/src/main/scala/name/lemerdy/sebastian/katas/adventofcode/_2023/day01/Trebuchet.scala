package name.lemerdy.sebastian.katas.adventofcode._2023.day01

import scala.annotation.tailrec

object Trebuchet:

  def calibrate(document: String): Long =
    document.split('\n').map(lineToNumber).sum
  def calibrateWithDigitAsLetters(document: String): Long =
    document.split('\n').map(line => digitAsLetters(line)).map(lineToNumber).sum

  private def lineToNumber(line: String): Long =
    val numbers = line.flatMap {
      case '1' => Some(1L)
      case '2' => Some(2L)
      case '3' => Some(3L)
      case '4' => Some(4L)
      case '5' => Some(5L)
      case '6' => Some(6L)
      case '7' => Some(7L)
      case '8' => Some(8L)
      case '9' => Some(9L)
      case _   => None
    }
    numbers.head * 10 + numbers.last

  @tailrec
  private def digitAsLetters(line: String, acc: String = ""): String =
    if (line.isEmpty) acc
    else if (line.startsWith("one")) digitAsLetters(line.tail, acc + "1")
    else if (line.startsWith("two")) digitAsLetters(line.tail, acc + "2")
    else if (line.startsWith("three")) digitAsLetters(line.tail, acc + "3")
    else if (line.startsWith("four")) digitAsLetters(line.tail, acc + "4")
    else if (line.startsWith("five")) digitAsLetters(line.tail, acc + "5")
    else if (line.startsWith("six")) digitAsLetters(line.tail, acc + "6")
    else if (line.startsWith("seven")) digitAsLetters(line.tail, acc + "7")
    else if (line.startsWith("eight")) digitAsLetters(line.tail, acc + "8")
    else if (line.startsWith("nine")) digitAsLetters(line.tail, acc + "9")
    else digitAsLetters(line.tail, acc + line.head)
