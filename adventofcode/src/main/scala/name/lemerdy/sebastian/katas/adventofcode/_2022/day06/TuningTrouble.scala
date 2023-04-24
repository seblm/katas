package name.lemerdy.sebastian.katas.adventofcode._2022.day06

import scala.Function.const
import scala.annotation.tailrec
import scala.collection.mutable
import scala.jdk.StreamConverters.*

object TuningTrouble:

  def findStartMarker(input: String, limit: Int = 4): Int =
    input
      .foldLeft(Accumulator()) { case (accumulator, currentChar) =>
        accumulator match {
          case Accumulator(_, _, true)                 => accumulator
          case Accumulator(i, current, _) if i < limit => Accumulator(i + 1, current :+ currentChar)
          case Accumulator(i, current, _) if accumulator.next(currentChar).distinct.length == limit =>
            Accumulator(i + 1, current, true)
          case Accumulator(i, _, _) => Accumulator(i + 1, accumulator.next(currentChar))
        }
      }
      .index

  case class Accumulator(index: Int = 0, current: String = "", done: Boolean = false):
    def next(currentChar: Char): String = current.drop(1) :+ currentChar
