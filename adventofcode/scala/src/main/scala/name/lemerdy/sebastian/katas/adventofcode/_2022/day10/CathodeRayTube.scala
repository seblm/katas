package name.lemerdy.sebastian.katas.adventofcode._2022.day10

import scala.annotation.tailrec
import scala.jdk.StreamConverters.*
import scala.util.matching.Regex

object CathodeRayTube:

  def display(input: String): String = display(input.lines().toScala(Vector).flatMap(instructionToCycles).zipWithIndex)

  @tailrec
  private def display(cycles: Vector[(Cycle, Int)], output: String = "", x: Int = 0): String =
    if cycles.isEmpty then output.grouped(40).mkString("\n")
    else
      val (cycle, column) = cycles.head
      val pixel = if (Range.inclusive(x, x + 2).contains(column % 40)) '#' else '.'
      display(cycles.tail, output.appended(pixel), cycle.updateX(x))

  def computeSignalStrengths(input: String): Int =
    val program = input.lines().toScala(Vector).flatMap(instructionToCycles)
    Vector(20, 60, 100, 140, 180, 220).map(cycles => computeSignalStrength(program, cycles)).sum

  private def computeSignalStrength(program: Vector[Cycle], cycles: Int): Int = computeX(program, cycles - 1) * cycles

  private def computeX(program: Vector[Cycle], cycles: Int): Int = execute(program.take(cycles))

  @tailrec
  private def execute(cycles: Vector[Cycle], x: Int = 1): Int =
    if cycles.isEmpty then x else execute(cycles.tail, cycles.head.updateX(x))

  private def instructionToCycles: String => Vector[Cycle] = {
    case noop()      => Vector(NoopCycle())
    case addX(value) => Vector(NoopCycle(), UpdateXCycle(_ + value.toInt))
  }

  private sealed trait Cycle:
    def updateX(x: Int): Int
  private class NoopCycle extends Cycle:
    override def updateX(x: Int): Int = x
  private class UpdateXCycle(compute: Int => Int) extends Cycle:
    override def updateX(x: Int): Int = compute(x)

  private val noop: Regex = "noop".r
  private val addX: Regex = """addx (-?\d+)""".r
