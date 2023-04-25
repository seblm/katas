package name.lemerdy.sebastian.katas.adventofcode._2015.day7

import scala.collection.mutable
import scala.io.Source

class Circuit(circuit: String):

  private val instructions = circuit
    .split("\n")
    .map(new Instruction(_, this))
    .map(instruction => instruction.wire -> instruction)

  val cache: mutable.Map[Wire, Signal] = mutable.Map.empty

  def signal(wire: Wire): Signal = cache.getOrElseUpdate(
    wire,
    instructions
      .filter(_._1.equals(wire))
      .head
      ._2
      .expression
      .run(this)
  )

object Circuit:
  def main(args: Array[String]): Unit =
    val input = Source.fromInputStream(getClass.getResourceAsStream("input")).mkString
    println(new Circuit(input).signal(Wire("a"))) // 32 is too low !
