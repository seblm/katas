package name.lemerdy.sebastian.katas.adventofcode._2021.day14

import scala.annotation.tailrec
import scala.collection.mutable

object ExtendedPolymerization:

  private val pairInsertionRegex = """([A-Z][A-Z]) -> ([A-Z])""".r

  def mostMinusLeastCommon(input: List[String], steps: Int): BigInt =
    val (template, pairInsertions) = parse(input)
    val cache: mutable.Map[(Char, Char, Int), Map[Char, BigInt]] = mutable.Map.empty
    val counts = template
      .zip(template.tail)
      .map { case (left, right) => formula(left, right, pairInsertions, steps, cache) }
      .concat(Seq(Map(template.last -> BigInt(1))))
      .fold(Map.empty)(merge)
      .toList
      .sortBy(_._2)
      .map(_._2)
    counts.last - counts.head

  private def parse(input: List[String]): (String, Map[String, Char]) = (
    input.head,
    input.tail.tail.foldLeft(Map.empty) { case (acc, pairInsertionRegex(pair, inserted)) =>
      acc + (pair -> inserted.head)
    }
  )

  private def formula(
      left: Char,
      right: Char,
      pairInsertions: Map[String, Char],
      step: Int,
      cache: mutable.Map[(Char, Char, Int), Map[Char, BigInt]]
  ): Map[Char, BigInt] =
    if (step == 0)
      Map(left -> 1)
    else
      cache.getOrElse(
        (left, right, step), {
          val inserted = pairInsertions(s"$left$right")
          val result = merge(
            formula(left, inserted, pairInsertions, step - 1, cache),
            formula(inserted, right, pairInsertions, step - 1, cache)
          )
          cache.updateWith((left, right, step))(_ => Some(result))
          result
        }
      )

  private def merge(a: Map[Char, BigInt], b: Map[Char, BigInt]): Map[Char, BigInt] =
    (a.toSeq ++ b.toSeq).groupBy(_._1).map(a => a._1 -> a._2.map(_._2).sum)
