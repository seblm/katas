package name.lemerdy.sebastian.katas.adventofcode._2018.day5

object AlchemicalReduction {

  def reduce(polymer: String): String = polymer
    .foldLeft(Seq.empty[Char]) {
      case (Nil, current)                                     => Seq(current)
      case (reduced, current) if react(reduced.last, current) => reduced.dropRight(1)
      case (reduced, current)                                 => reduced :+ current
    }
    .mkString

  def reduceMore(polymer: String): String = polymer.toLowerCase.toSet
    .map { (c: Char) => polymer.replaceAll(s"${c.toUpper}", "").replaceAll(s"${c.toLower}", "") }
    .map(reduce)
    .minBy(_.length)

  private def react(c1: Char, c2: Char): Boolean =
    (c1.toLower == c2 || c1 == c2.toLower) && c1 != c2

}
