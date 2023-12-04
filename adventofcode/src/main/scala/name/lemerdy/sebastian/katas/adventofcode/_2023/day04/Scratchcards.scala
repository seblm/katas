package name.lemerdy.sebastian.katas.adventofcode._2023.day04

import scala.annotation.tailrec
import scala.math.{max, pow}

case class Scratchcards(winningNumbers: Seq[Int], myNumbers: Seq[Int]):
  private val matchCount: Int = winningNumbers.intersect(myNumbers).length
  private def computePoints: Int = max(0, pow(2.doubleValue, (matchCount - 1).doubleValue).toInt)

object Scratchcards:
  private def toNumbers(numbers: String): Seq[Int] = numbers.trim.split("""\s+""").map(_.toInt).toIndexedSeq
  private val regex = """Card\s+\d+:(.+)\|(.+)""".r
  private def cards(input: String) = input
    .split('\n')
    .map:
      case regex(winningNumbers, myNumbers) =>
        Scratchcards(toNumbers(winningNumbers), toNumbers(myNumbers))
  def count(input: String): Int = cards(input).map(_.computePoints).sum
  @tailrec
  private def countTotal(cards: List[(Scratchcards, Int)], count: Int = 0): Int =
    cards match
      case Nil => count
      case (card, c) :: rest =>
        val appliedCardCount = rest
          .zipAll(Iterable.fill(card.matchCount)(c), null, 0)
          .map:
            case ((currentCard, currentCount), additionalCardCount) => (currentCard, currentCount + additionalCardCount)
        countTotal(appliedCardCount, count + c)
  def totalCount(input: String): Int = countTotal(cards(input).map(card => (card, 1)).toList)
