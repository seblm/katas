package name.lemerdy.sebastian.katas.adventofcode._2021.day06

import scala.annotation.tailrec
import scala.collection.immutable.IntMap

object Lanternfish:

  def count(list: String, days: Int): Long =
    count(list.split(",").map(_.toInt).toSeq, days)

  @tailrec
  private def count(list: Seq[Int], days: Int): Long = days match {
    case 0 => list.length
    case _ =>
      val decremented = list.map(lanternfish => if (lanternfish == 0) 6 else lanternfish - 1)
      count(decremented.appendedAll(Seq.fill(list.count(_ == 0))(8)), days - 1)
  }

  def smartCount(list: String, days: Int): Long =
    smartCount(list.split(",").map(_.toInt).groupBy(identity).map { case (k, v) => (k, v.length.toLong) }, days)

  @tailrec
  def smartCount(list: Map[Int, Long], days: Int): Long = days match {
    case 0 => list.values.sum
    case _ =>
      val newLanternfish = list.getOrElse(0, 0L)
      smartCount(
        Map(
          0 -> list.getOrElse(1, 0L),
          1 -> list.getOrElse(2, 0L),
          2 -> list.getOrElse(3, 0L),
          3 -> list.getOrElse(4, 0L),
          4 -> list.getOrElse(5, 0L),
          5 -> list.getOrElse(6, 0L),
          6 -> (list.getOrElse(7, 0L) + newLanternfish),
          7 -> list.getOrElse(8, 0L),
          8 -> newLanternfish
        ),
        days - 1
      )
  }
