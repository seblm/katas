package name.lemerdy.sebastian.katas.nicestrings

import scala.io.Source

class NiceStrings {
  def isNice(value: String): Boolean = {
    Seq(Vowels, TwiceLetter, ForbiddenPatterns).forall(_.isNice(value))
  }
}

trait IsNice {
  def isNice(value: String): Boolean
}

object Vowels extends IsNice {
  override def isNice(value: String): Boolean = isNice(value.toList, 0)

  def isNice(value: List[Char], vowels: Int): Boolean = value match {
    case Nil => vowels >= 3
    case head :: tail => isNice(tail, vowels + (if ("aeiou".contains(head)) 1 else 0))
  }
}

object TwiceLetter extends IsNice {
  override def isNice(value: String) = "([a-z])\\1".r.findFirstIn(value).isDefined
}

object ForbiddenPatterns extends IsNice {
  override def isNice(value: String): Boolean = "(ab|cd|pq|xy)".r.findFirstIn(value).isEmpty
}

object NiceStrings {
  def main(args: Array[String]) {
    val niceStrings: NiceStrings = new NiceStrings()
    println(Source
      .fromInputStream(getClass.getResourceAsStream("input"))
      .getLines()
      .count(niceStrings.isNice))
  }
}