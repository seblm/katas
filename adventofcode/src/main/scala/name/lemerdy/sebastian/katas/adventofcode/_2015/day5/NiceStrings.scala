package name.lemerdy.sebastian.katas.adventofcode._2015.day5

import scala.annotation.tailrec
import scala.io.Source

class NiceStrings:
  def isNice(value: String): Boolean =
    Seq(Vowels, TwiceLetter, ForbiddenPatterns).forall(_.isNice(value))
  def isVeryNice(value: String): Boolean =
    Seq(Pairs, RepeatWithOneLetterBetween).forall(_.isNice(value))

trait IsNice:
  def isNice(value: String): Boolean

object Vowels extends IsNice:
  override def isNice(value: String): Boolean = isNice(value.toList, 0)

  @tailrec
  def isNice(value: List[Char], vowels: Int): Boolean = value match {
    case Nil          => vowels >= 3
    case head :: tail => isNice(tail, vowels + (if ("aeiou".contains(head)) 1 else 0))
  }

object TwiceLetter extends IsNice:
  override def isNice(value: String): Boolean = "([a-z])\\1".r.findFirstIn(value).isDefined

object ForbiddenPatterns extends IsNice:
  override def isNice(value: String): Boolean = "(ab|cd|pq|xy)".r.findFirstIn(value).isEmpty

object Pairs extends IsNice:
  @tailrec
  override def isNice(value: String): Boolean = {
    if value.length <= 2 then false
    else
      hasPair((value.head, value.tail.head), value.drop(2)) ||
      isNice(value.tail)
  }

  private def hasPair(pair: (Char, Char), remainder: String): Boolean = remainder.contains(s"${pair._1}${pair._2}")

object RepeatWithOneLetterBetween extends IsNice:
  @tailrec
  override def isNice(value: String): Boolean =
    if value.length < 3 then false
    else
      hasRepeat((value.head, value.tail.head, value.tail.tail.head)) ||
      isNice(value.tail)

  private def hasRepeat(triple: (Char, Char, Char)): Boolean = triple._1.equals(triple._3)

object NiceStrings:
  def main(args: Array[String]): Unit =
    val niceStrings: NiceStrings = new NiceStrings()
    println(
      Source
        .fromInputStream(getClass.getResourceAsStream("input"))
        .getLines()
        .count(niceStrings.isNice)
    )
    println(
      Source
        .fromInputStream(getClass.getResourceAsStream("input"))
        .getLines()
        .count(niceStrings.isVeryNice)
    )
