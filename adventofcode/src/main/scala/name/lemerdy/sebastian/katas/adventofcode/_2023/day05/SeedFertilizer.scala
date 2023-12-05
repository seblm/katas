package name.lemerdy.sebastian.katas.adventofcode._2023.day05

import scala.annotation.tailrec
import scala.collection.mutable

object SeedFertilizer:
  private case class Mapping(sourceRange: Long, destinationRange: Long, length: Long)
  private def createMapping(line: String): Mapping =
    val numbers = line.split(' ')
    Mapping(numbers(1).toLong, numbers.head.toLong, numbers(2).toLong)

  private case class Mappings(value: Seq[Mapping] = Seq.empty)

  @tailrec
  private def readMappings(
      lines: List[String],
      acc: List[Mappings] = List.empty,
      currentMappings: Mappings = Mappings()
  ): List[Mappings] =
    lines match
      case Nil                                     => acc :+ currentMappings
      case "" :: tail                              => readMappings(tail, acc :+ currentMappings)
      case line :: tail if line.matches("[a-z].+") => readMappings(tail, acc)
      case line :: tail => readMappings(tail, acc, currentMappings.copy(currentMappings.value :+ createMapping(line)))

  private def applyOneMappings(mappings: Mappings)(seed: Long): Long =
    val mapping = mappings.value
      .find:
        case Mapping(sourceRange, destinationRange, length) => sourceRange <= seed && seed < sourceRange + length
      .getOrElse(Mapping(seed, seed, 1))
    seed - mapping.sourceRange + mapping.destinationRange

  @tailrec
  private def applyAllMappings(allMappings: List[Mappings])(seed: Long): Long = allMappings match
    case Nil              => seed
    case mappings :: rest => applyAllMappings(rest)(applyOneMappings(mappings)(seed))

  def lowestLocation(input: String): Long =
    val lines = input.split("\n")
    val seeds = lines.head.split(": ").last.split(" ").map(_.toLong)
    val mappings = readMappings(lines.drop(2).toList)
    seeds.map(applyAllMappings(mappings)).min

  private def memoize[I, O](f: I => O): I => O = new mutable.HashMap[I, O]():
    override def apply(key: I) = getOrElseUpdate(key, f(key))
