package name.lemerdy.sebastian.katas.adventofcode._2022.day12

import scala.collection.mutable
import scala.jdk.StreamConverters.*
import scala.math.abs

object HillClimbingAlgorithm:

  case class Point(x: Int, y: Int):
    def move(dx: Int, dy: Int): Point = Point(x + dx, y + dy)
  end Point

  private val up = (0, 1)
  private val down = (0, -1)
  private val left = (-1, 0)
  private val right = (1, 0)
  private val possibleMoves = List(up, down, left, right)

  private def path(point: Point, net: Map[Point, Char]): Seq[Point] = possibleMoves.map(point.move).filter(net.contains)

  private def matching(point: Point, net: Map[Point, Char]): Char = net(point) match
    case 'S'   => 'a'
    case 'E'   => 'z'
    case other => other

  def computeFewestSteps(input: String, searchedChar: Char = 'S'): Int =
    val points: Map[Point, Char] = input
      .lines()
      .toScala(Vector)
      .zipWithIndex
      .flatMap { case (line, row) =>
        line.chars().toScala(Vector).zipWithIndex.map { case (char, column) => Point(column, row) -> char.toChar }
      }
      .toMap
    val initial = points.map(_.swap)('E')
    val toVisit = mutable.Queue(initial)
    val length = mutable.Map(initial -> 0)
    while toVisit.nonEmpty do
      val visited = toVisit.dequeue()
      if points(visited) == searchedChar then return length(visited)
      for neighbor <- path(visited, points) do
        val shouldVisitNeighbor =
          !length.contains(neighbor) && matching(visited, points) - matching(neighbor, points) <= 1
        if shouldVisitNeighbor then
          toVisit.enqueue(neighbor)
          length(neighbor) = length(visited) + 1
      end for
    end while
    throw IllegalStateException("unexpected end of search area")

end HillClimbingAlgorithm
