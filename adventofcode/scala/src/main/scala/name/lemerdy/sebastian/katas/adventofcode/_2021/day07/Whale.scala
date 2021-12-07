package name.lemerdy.sebastian.katas.adventofcode._2021.day07

import scala.annotation.tailrec
import scala.collection.mutable
import scala.math.abs

object Whale:

  def alignCrabs(positions: String): Long =
    val pos = positions.split(",").map(_.toInt)
    Range
      .inclusive(pos.min, pos.max)
      .map(center => center -> pos.map(position => abs(position - center)).sum)
      .minBy(_._2)
      ._2

  def alignCrabsMoreExpensive(positions: String): Long =
    val pos = positions.split(",").map(_.toInt)
    Range
      .inclusive(pos.min, pos.max)
      .map(center => center -> pos.map(position => expensiveFuel(abs(position - center))).sum)
      .minBy(_._2)
      ._2

  private val memoizedFuel = mutable.Map[Int, Long]()

  def expensiveFuel(delta: Int): Long =
    memoizedFuel.getOrElse(
      delta, {
        val result = Range.inclusive(1, delta).sum
        memoizedFuel.addOne(delta, result)
        result
      }
    )
