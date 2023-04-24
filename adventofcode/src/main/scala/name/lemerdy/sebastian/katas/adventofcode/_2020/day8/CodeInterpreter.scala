package name.lemerdy.sebastian.katas.adventofcode._2020.day8

import name.lemerdy.sebastian.katas.adventofcode._2020.day8.CodeInterpreter.Instruction._
import name.lemerdy.sebastian.katas.adventofcode._2020.day8.CodeInterpreter.Result._

import scala.util.matching.Regex

object CodeInterpreter {

  private val nop: Regex = """nop \+?(-?\d+)""".r
  private val acc: Regex = """acc \+?(-?\d+)""".r
  private val jmp: Regex = """jmp \+?(-?\d+)""".r

  sealed trait Instruction
  object Instruction {
    case class Nop(useless: Int) extends Instruction
    case class Acc(value: Int) extends Instruction
    case class Jmp(lines: Int) extends Instruction
    case object Ukn extends Instruction
    implicit class InstructionOps(content: String) {

      def toInstructions: Map[Int, Instruction] =
        content.split('\n').zipWithIndex.map { case (line, lineNumber) => lineNumber -> line.toInstruction }.toMap

      def toInstruction: Instruction = content match {
        case nop(useless) => Nop(useless.toInt)
        case acc(value)   => Acc(value.toInt)
        case jmp(lines)   => Jmp(lines.toInt)
        case _            => Ukn
      }

    }
  }

  sealed trait Result
  object Result {
    case class InfiniteLoop(accumulator: Int) extends Result
    case class Terminate(accumulator: Int) extends Result
    case class SyntaxError(accumulator: Int) extends Result
  }

  def run(code: String, mutate: Boolean = false): Result = run(code.count(_ == '\n'))(code.toInstructions, 0, 0, mutate)

  private def run(
      length: Int
  )(instructions: Map[Int, Instruction], offset: Int, accumulator: Int, mutate: Boolean): Result =
    instructions.get(offset) match {
      case None if instructions.isEmpty || offset > length => Terminate(accumulator)
      case None                                            => InfiniteLoop(accumulator)
      case Some(Nop(lines)) if mutate =>
        run(length)(instructions.filterNot(_._1 == offset), offset + lines, accumulator, mutate = false) match {
          case end: Terminate => end
          case _ =>
            run(length)(instructions.filterNot(_._1 == offset), offset + 1, accumulator, mutate = true)
        }
      case Some(Nop(_)) =>
        run(length)(instructions.filterNot(_._1 == offset), offset + 1, accumulator, mutate)
      case Some(Acc(value)) =>
        run(length)(instructions.filterNot(_._1 == offset), offset + 1, accumulator + value, mutate)
      case Some(Jmp(lines)) if mutate =>
        run(length)(instructions.filterNot(_._1 == offset), offset + 1, accumulator, mutate = false) match {
          case end: Terminate => end
          case _ => run(length)(instructions.filterNot(_._1 == offset), offset + lines, accumulator, mutate = true)
        }
      case Some(Jmp(lines)) =>
        run(length)(instructions.filterNot(_._1 == offset), offset + lines, accumulator, mutate)
      case Some(Ukn) => SyntaxError(accumulator)
    }

}
