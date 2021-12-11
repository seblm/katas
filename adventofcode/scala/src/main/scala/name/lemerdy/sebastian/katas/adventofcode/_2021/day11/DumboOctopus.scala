package name.lemerdy.sebastian.katas.adventofcode._2021.day11

import scala.annotation.tailrec
import scala.math.abs

case class DumboOctopus(x: Int, y: Int, initialEnergy: Int):
  var internalEnergy: Int = initialEnergy
  var flashing: Boolean = false
  var hasFlashed: Boolean = false
  var flashCount: Int = 0
  def increment(): DumboOctopus =
    if (!flashing && !hasFlashed)
      internalEnergy += 1
      if (internalEnergy > 9)
        internalEnergy = 0
        flashing = true
    this
  def emitEnergyToAdjacents(): DumboOctopus =
    flashing = false
    hasFlashed = true
    flashCount += 1
    this
  def reset(): DumboOctopus =
    hasFlashed = false
    this

object DumboOctopus:

  def flashCount(energyLevels: List[String], count: Int): Long =
    steps(parse(energyLevels), count).map(_.flashCount).sum

  @tailrec
  def steps(octopuses: List[DumboOctopus], count: Int): List[DumboOctopus] = count match {
    case 0 => octopuses
    case _ => steps(step(octopuses).map(_.reset()), count - 1)
  }

  def step(octopuses: List[DumboOctopus]): List[DumboOctopus] = emitEnergy(octopuses.map(_.increment()))

  @tailrec
  private def emitEnergy(octopuses: List[DumboOctopus]): List[DumboOctopus] = octopuses.find(_.flashing) match {
    case None          => octopuses
    case Some(emitter) => emitEnergy(adjacents(octopuses, emitter.emitEnergyToAdjacents(), _.increment()))
  }

  private def adjacents(
      octopuses: List[DumboOctopus],
      octopus: DumboOctopus,
      adjacentsHandler: DumboOctopus => DumboOctopus
  ): List[DumboOctopus] =
    octopuses.map { current =>
      (current.x, current.y) match {
        case (x, y) if octopus.x == x && octopus.y == y                   => octopus
        case (x, y) if abs(octopus.x - x) == 1 && abs(octopus.y - y) == 1 => adjacentsHandler(current)
        case (x, y) if abs(octopus.x - x) == 1 && octopus.y == y          => adjacentsHandler(current)
        case (x, y) if abs(octopus.y - y) == 1 && octopus.x == x          => adjacentsHandler(current)
        case _                                                            => current
      }
    }

  def parse(energyLevels: List[String]): List[DumboOctopus] =
    energyLevels.zipWithIndex.flatMap { (line, y) =>
      line.zipWithIndex.map { (column, x) =>
        DumboOctopus(x, y, column.toString.toInt)
      }
    }

  def flashSimultaneouslyStep(energyLevels: List[String]): Int =
    flashSimultaneouslyStep(parse(energyLevels), 1)

  @tailrec
  private def flashSimultaneouslyStep(octopuses: List[DumboOctopus], count: Int): Int =
    val afterStep = step(octopuses)
    if (afterStep.forall(_.hasFlashed))
      count
    else
      flashSimultaneouslyStep(afterStep.map(_.reset()), count + 1)
