package name.lemerdy.sebastian.katas.adventofcode._2021.day10

import scala.annotation.tailrec

object SyntaxScoring:

  private val scores = Map(')' -> 3, ']' -> 57, '}' -> 1197, '>' -> 25137)

  def totalSyntaxErrorScore(lines: List[String]): Long =
    findAllFirstIllegalCharacters(lines).map(scores).sum

  @tailrec
  def findAllFirstIllegalCharacters(lines: List[String], firstIllegalCharacters: String = ""): String = lines match {
    case Nil => firstIllegalCharacters
    case head :: tail =>
      findFirstIllegalCharacter(head) match {
        case Some(firstIllegal) => findAllFirstIllegalCharacters(tail, firstIllegalCharacters :+ firstIllegal)
        case None               => findAllFirstIllegalCharacters(tail, firstIllegalCharacters)
      }
  }

  def findFirstIllegalCharacter(line: String): Option[Char] =
    findFirstIllegalCharacter(line.toList, Seq.empty)

  private val openChunks = "([{<"

  private val closedChunks = Map('(' -> ')', '[' -> ']', '{' -> '}', '<' -> '>')

  @tailrec
  private def findFirstIllegalCharacter(line: List[Char], openedChunks: Seq[Char]): Option[Char] = line match {
    case Nil =>
      None
    case head :: tail if openChunks.contains(head) =>
      findFirstIllegalCharacter(tail, openedChunks :+ head)
    case head :: tail if closedChunks(openedChunks.last) == head =>
      findFirstIllegalCharacter(tail, openedChunks.dropRight(1))
    case head :: _ =>
      Some(head)
  }

  def computeCompletionScores(lines: List[String]): Long =
    val allScores = lines.filter(line => findFirstIllegalCharacter(line).isEmpty).map(computeCompletionScore).sorted
    allScores(allScores.length / 2)

  private val completionScores = Map(')' -> 1, ']' -> 2, '}' -> 3, '>' -> 4)

  def computeCompletionScore(line: String): Long =
    complete(line.toList).map(completionScores).foldLeft(0L)((total, score) => total * 5 + score)

  @tailrec
  def complete(line: List[Char], openedChunks: Seq[Char] = Seq.empty): String = line match {
    case Nil                                       => openedChunks.map(closedChunks).reverse.mkString
    case head :: tail if openChunks.contains(head) => complete(tail, openedChunks :+ head)
    case head :: tail if scores.contains(head)     => complete(tail, openedChunks.dropRight(1))
    case _                                         => ""
  }
