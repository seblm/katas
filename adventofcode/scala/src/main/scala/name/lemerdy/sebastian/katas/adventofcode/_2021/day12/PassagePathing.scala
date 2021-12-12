package name.lemerdy.sebastian.katas.adventofcode._2021.day12

import scala.annotation.tailrec

object PassagePathing:

  def countPaths(map: List[String]): Long =
    visit(computeGraph(map))

  def countPathsOneSmallCaveTwice(map: List[String]): Long =
    visit1(computeGraph(map)).size

  private def visit(graph: Map[Cave, Set[Cave]], current: Cave = Start, visited: Set[SmallCave] = Set.empty): Long =
    graph.get(current) match {
      case None => 1
      case Some(nexts) =>
        nexts.filterNot(alreadyVisited(visited)).toList.map(c => visit(graph, c, updateVisited(visited)(c))).sum
    }

  private def visit1(
      graph: Map[Cave, Set[Cave]],
      current: Cave = Start,
      singleSmallCave: Option[SmallCave] = None,
      visited: Set[SmallCave] = Set.empty,
      ancestors: List[Cave] = List.empty
  ): Set[List[Cave]] =
    (graph.get(current), singleSmallCave) match {
      case (None, _) => Set(ancestors)
      case (Some(nexts), s @ Some(_)) =>
        nexts
          .filterNot(alreadyVisited(visited))
          .toList
          .flatMap(c => visit1(graph, c, s, updateVisited(visited)(c), ancestors :+ c))
          .toSet
      case (Some(nexts), None) =>
        nexts
          .filterNot(alreadyVisited(visited))
          .toList
          .flatMap {
            case c @ SmallCave(_) =>
              visit1(graph, c, Some(c), visited, ancestors :+ c) ++ visit1(
                graph,
                c,
                None,
                updateVisited(visited)(c),
                ancestors :+ c
              )
            case c =>
              visit1(graph, c, None, updateVisited(visited)(c), ancestors :+ c)
          }
          .toSet
    }

  private def alreadyVisited(visited: Set[SmallCave]): Cave => Boolean =
    case cave: SmallCave => visited.contains(cave)
    case _               => false

  private def updateVisited(visited: Set[SmallCave]): Cave => Set[SmallCave] =
    case cave: SmallCave => visited + cave
    case _               => visited

  private val line = "([A-Za-z]+)-([A-Za-z]+)".r

  @tailrec
  private def computeGraph(map: List[String], caves: Map[Cave, Set[Cave]] = Map.empty): Map[Cave, Set[Cave]] =
    map match {
      case Nil =>
        caves.removed(End).map((k, v) => (k, v.filterNot(_ == Start)))
      case line(a, b) :: tail =>
        (Cave.unapply(a), Cave.unapply(b)) match {
          case (Some(c), Some(d)) => computeGraph(tail, addConnections(caves, c, d))
          case _                  => Map.empty
        }
      case _ => Map.empty
    }

  private def addConnections(caves: Map[Cave, Set[Cave]], cave1: Cave, cave2: Cave) =
    addConnection(addConnection(caves, cave1, cave2), cave2, cave1)

  private def addConnection(caves: Map[Cave, Set[Cave]], cave1: Cave, cave2: Cave) =
    caves.updatedWith(cave1) {
      case None              => Some(Set(cave2))
      case Some(connections) => Some(connections + cave2)
    }

  trait Cave

  object Cave:
    private val bigRegex = "([A-Z]+)".r
    private val smallRegex = "([a-z]+)".r
    def unapply(name: String): Option[Cave] = name match {
      case "start"           => Some(Start)
      case "end"             => Some(End)
      case bigRegex(big)     => Some(BigCave(big))
      case smallRegex(small) => Some(SmallCave(small))
      case _                 => None
    }

  case class BigCave(name: String) extends Cave:
    override def toString: String = name
  case class SmallCave(name: String) extends Cave:
    override def toString: String = name
  case object Start extends Cave:
    override def toString: String = "start"
  case object End extends Cave:
    override def toString: String = "end"
