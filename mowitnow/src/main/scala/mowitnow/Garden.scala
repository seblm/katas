package mowitnow

case class Garden(topX: Int, topY: Int, mowers: List[Mower])

object Garden:

  def toString(garden: Garden): String =
    s"${garden.topX} ${garden.topY}"
      + Option.when(garden.mowers.nonEmpty)("\n").getOrElse("")
      + garden.mowers.map(Mower.toString).mkString("\n")
