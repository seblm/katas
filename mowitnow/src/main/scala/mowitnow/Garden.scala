package mowitnow

case class Garden(topX: Int, topY: Int, mowers: List[Mower])

object Garden:

  def take(garden: Garden, index: Int): Garden =
    println(s"\n+--------\n| $index\n+--------\n${toString(garden)}")
    val mowersWithCurrentInstruction = garden.mowers
      .flatMap: mower =>
        mower.instructions.zipWithIndex.map: (instruction, index) =>
          mower.copy(instructions = List(mower.instructions(index)))
      .take(index + 1)
    val mowersAtIndex =
      mowersWithCurrentInstruction.foldLeft(mowersWithCurrentInstruction.headOption.toList):
        case (mowers, mower) if mowers.last.initialPosition != mower.initialPosition => mowers.appended(mower)
        case (mowers, mower)                                                         => mowers.init.appended(mower)
    val result = Garden(garden.topX, garden.topY, mowersAtIndex)
    println(s"\n${toString(result)}\n")
    result

  def toString(garden: Garden): String =
    s"${garden.topX} ${garden.topY}"
      + Option.when(garden.mowers.nonEmpty)("\n").getOrElse("")
      + garden.mowers.map(Mower.toString).mkString("\n")
