package name.lemerdy.sebastian.katas.adventofcode._2022.day01

import scala.jdk.StreamConverters._

object CalorieCounting:

  def totalCaloriesOfMost(size: Int)(allCalories: String): Long =
    allCalories
      .lines()
      .toScala(Iterator)
      .foldLeft(Accumulator.start()) {
        case (accumulator, "")   => accumulator.accumulateGlobal()
        case (accumulator, line) => accumulator.accumulateCurrent(line.toLong)
      }
      .max(size)

  case class Accumulator(maximums: Vector[Long], currentMax: Long):
    def accumulateCurrent(current: Long): Accumulator = Accumulator(maximums, currentMax + current)
    def accumulateGlobal(): Accumulator = Accumulator(maximums :+ currentMax, 0L)
    def max(size: Int): Long = accumulateGlobal().maximums.sorted.reverse.take(size).sum

  object Accumulator:
    def start(): Accumulator = Accumulator(Vector.empty, 0L)
