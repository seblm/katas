package name.lemerdy.sebastian.katas.adventofcode._2021.day08

import scala.annotation.tailrec

object SevenSegmentSearch:

  private val line =
    "([a-g]+) ([a-g]+) ([a-g]+) ([a-g]+) ([a-g]+) ([a-g]+) ([a-g]+) ([a-g]+) ([a-g]+) ([a-g]+) \\| ([a-g]+) ([a-g]+) ([a-g]+) ([a-g]+)".r

  def count(lines: List[String]): Long = lines.map(count).sum

  def count: String => Long =
    case line(_, _, _, _, _, _, _, _, _, _, a, b, c, d) => hasUniqueNumberOfSegments(Seq(a, b, c, d))

  private def hasUniqueNumberOfSegments(digits: Seq[String]): Long =
    digits.map(hasUniqueNumberOfSegments).count(identity)

  private def hasUniqueNumberOfSegments(digit: String): Boolean =
    Seq(2, 3, 4, 7).contains(digit.length)

  private val possibilitiesByLength = Map(
    2 -> List('c', 'f'),
    3 -> List('a', 'c', 'f'),
    4 -> List('b', 'c', 'd', 'f')
  )

  private val frequencies = Map('a' -> 8, 'b' -> 6, 'c' -> 8, 'd' -> 7, 'e' -> 4, 'f' -> 9, 'g' -> 7)

  def computeConfiguration: String => Map[Char, Char] =
    case line(q, r, s, t, u, v, w, x, y, z, a, b, c, d) =>
      computeConfiguration(
        List(q, r, s, t, u, v, w, x, y, z),
        Map('a' -> 0, 'b' -> 0, 'c' -> 0, 'd' -> 0, 'e' -> 0, 'f' -> 0, 'g' -> 0),
        Map(
          'a' -> List('a', 'b', 'c', 'd', 'e', 'f', 'g'),
          'b' -> List('a', 'b', 'c', 'd', 'e', 'f', 'g'),
          'c' -> List('a', 'b', 'c', 'd', 'e', 'f', 'g'),
          'd' -> List('a', 'b', 'c', 'd', 'e', 'f', 'g'),
          'e' -> List('a', 'b', 'c', 'd', 'e', 'f', 'g'),
          'f' -> List('a', 'b', 'c', 'd', 'e', 'f', 'g'),
          'g' -> List('a', 'b', 'c', 'd', 'e', 'f', 'g')
        )
      )

  @tailrec
  private def computeConfiguration(
      digits: List[String],
      observedFrequencies: Map[Char, Int],
      configurations: Map[Char, List[Char]]
  ): Map[Char, Char] =
    digits match {
      case Nil =>
        configurations.map { case (segment, possibilites) =>
          reduceWithFrequencies(observedFrequencies, segment, possibilites)
        }
      case digit :: tail if possibilitiesByLength.contains(digit.length) =>
        val (matchedSegments, noMatchedSegments) = configurations.partition((segment, _) => digit.contains(segment))
        val included = matchedSegments.map { case (segment, possibilities) =>
          (segment, possibilities.filter(possibilitiesByLength(digit.length).contains))
        }
        val excluded = noMatchedSegments.map { case (segment, possibilities) =>
          (segment, possibilities.filterNot(possibilitiesByLength(digit.length).contains))
        }
        computeConfiguration(tail, updateFrequencies(observedFrequencies, digit), included.concat(excluded))
      case digit :: tail =>
        computeConfiguration(tail, updateFrequencies(observedFrequencies, digit), configurations)
    }

  private def updateFrequencies(observedFrequencies: Map[Char, Int], digit: String): Map[Char, Int] =
    observedFrequencies.map {
      case (segment, observedFrequency) if digit.contains(segment) => (segment, observedFrequency + 1)
      case other                                                   => other
    }

  private def reduceWithFrequencies(
      observedFrequencies: Map[Char, Int],
      segment: Char,
      possibilities: List[Char]
  ): (Char, Char) =
    val filteredByFrequencies = possibilities match {
      case unique :: Nil => List(unique)
      case _ => possibilities.filter(possibility => frequencies(possibility) == observedFrequencies(segment))
    }
    (segment, filteredByFrequencies.head)

  def decode(lines: List[String]): Long = lines.map(decode).sum

  private val reference: Map[String, Int] = Map(
    "abcefg" -> 0,
    "cf" -> 1,
    "acdeg" -> 2,
    "acdfg" -> 3,
    "bcdf" -> 4,
    "abdfg" -> 5,
    "abdefg" -> 6,
    "acf" -> 7,
    "abcdefg" -> 8,
    "abcdfg" -> 9
  )

  def decode(currentLine: String): Long = currentLine match {
    case line(_, _, _, _, _, _, _, _, _, _, a, b, c, d) =>
      val configuration = computeConfiguration(currentLine)
      List(a, b, c, d).map(digit => reference(digit.map(configuration).sorted)).mkString.toLong
  }
