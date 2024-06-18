package mowitnow

import mowitnow.Orientation.North

import scala.util.Try

case class Garden(topX: Int, topY: Int, mowers: List[Mower])

object Garden:

  def take(garden: Garden, index: Int): Garden =
    val mowerAtIndex = Try(
      garden.mowers
        .flatMap(mower => mower.instructions.map(instruction => mower.copy(instructions = List(instruction))))(index)
    ).getOrElse(
      garden.mowers.lastOption.fold(Mower(Position(0, 0, North), List.empty))(_.copy(instructions = List.empty))
    )
    Garden(
      garden.topX,
      garden.topY,
      garden.mowers
        .map: mower =>
          if mower.initialPosition == mowerAtIndex.initialPosition then mowerAtIndex
          else mower.copy(instructions = List.empty)
    )

  def toString(garden: Garden): String =
    s"${garden.topX} ${garden.topY}"
      + Option.when(garden.mowers.nonEmpty)("\n").getOrElse("")
      + garden.mowers.map(Mower.toString).mkString("\n")
