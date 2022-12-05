package name.lemerdy.sebastian.katas.adventofcode._2022.day05

import scala.Function.const
import scala.annotation.tailrec
import scala.collection.mutable
import scala.jdk.StreamConverters.*

object SupplyStack:

  def computeTopOfStacks(input: String, byBlock: Boolean, real: Boolean = false): String =
    val (stacks, rearrangements) = readStacks(input.lines().toScala(Iterator), real)
    rearrangements.foreach { rearrangement =>
      val from = stacks(rearrangement.from - 1)
      val to = stacks(rearrangement.to - 1)
      val blocksToMoveTo = Range(0, rearrangement.size).map(_ => from.pop())
      val byBlockOrOneByOne = if byBlock then blocksToMoveTo.reverse else blocksToMoveTo
      byBlockOrOneByOne.foreach(to.push)
    }
    stacks.map(_.top).mkString

  @tailrec
  private def readStacks(input: Iterator[String], real: Boolean): (Vector[mutable.Stack[Char]], Vector[Rearrangement]) =
    val currentLine = input.next()
    if currentLine.isEmpty then
      if real then
        (
          Vector(
            mutable.Stack('W', 'P', 'G', 'Z', 'V', 'S', 'B'),
            mutable.Stack('F', 'Z', 'C', 'B', 'V', 'J'),
            mutable.Stack('C', 'D', 'Z', 'N', 'H', 'M', 'L', 'V'),
            mutable.Stack('B', 'J', 'F', 'P', 'Z', 'M', 'D', 'L'),
            mutable.Stack('H', 'Q', 'B', 'J', 'G', 'C', 'F', 'V'),
            mutable.Stack('B', 'L', 'S', 'T', 'Q', 'F', 'G'),
            mutable.Stack('V', 'Z', 'C', 'G', 'L'),
            mutable.Stack('G', 'L', 'N'),
            mutable.Stack('C', 'H', 'F', 'J')
          ),
          readRearrangements(input)
        )
      else
        (Vector(mutable.Stack('N', 'Z'), mutable.Stack('D', 'C', 'M'), mutable.Stack('P')), readRearrangements(input))
    else readStacks(input, real)

  private val rearrangementPattern = """move (\d+) from (\d+) to (\d+)""".r
  @tailrec
  private def readRearrangements(
      input: Iterator[String],
      rearrangements: Vector[Rearrangement] = Vector.empty
  ): Vector[Rearrangement] =
    if input.isEmpty then rearrangements
    else
      input.next() match {
        case rearrangementPattern(size, from, to) =>
          readRearrangements(
            input,
            rearrangements :+ Rearrangement(size.toInt, from.toInt, to.toInt)
          )
      }

  private case class Rearrangement(size: Int, from: Int, to: Int)
