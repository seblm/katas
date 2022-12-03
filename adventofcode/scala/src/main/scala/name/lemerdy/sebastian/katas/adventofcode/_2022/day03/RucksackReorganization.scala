package name.lemerdy.sebastian.katas.adventofcode._2022.day03

import scala.jdk.StreamConverters.*

object RucksackReorganization:

  def sumPriorities(rucksacksContent: String): Long =
    priorityOfFirstCommonItem(
      eachRucksackContent(rucksacksContent)
        .map(rucksackContent => (rucksackContent.length / 2, rucksackContent))
        .map { case (half, rucksackContent) => Seq(rucksackContent.take(half), rucksackContent.drop(half)) }
    )

  def sumBadges(rucksacksContent: String): Long =
    priorityOfFirstCommonItem(eachRucksackContent(rucksacksContent).grouped(3))

  private def eachRucksackContent(rucksacksContent: String) = rucksacksContent.lines().toScala(Iterator)

  private def priorityOfFirstCommonItem: Iterator[Seq[String]] => Long =
    _.map(_.foldLeft("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ") { case (a, b) => a.intersect(b) }.head)
      .map(toPriority)
      .sum

  private def toPriority(char: Char): Long =
    if char.isUpper then char.toLong - 'A'.toLong + 27 else char.toLong - 'a'.toLong + 1
