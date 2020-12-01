package name.lemerdy.sebastian.katas.adventofcode._2018.day6

import scala.annotation.tailrec

object ChronalCoordinates {

  def reduceAllDistancesToShortest(coordinates: (Int, Int)*): Map[Coord, (Coord, Int)] = {
    val coords = Coord.toCoords(coordinates: _*)
    val allDistances = computeAllDistances(coords)(coords)
    println(allDistances.get(Coord(5, 0)))
    allDistances
      .flatMap { case (key, distancesByCoord) =>
        val minimumDistance = distancesByCoord.map(_._2).min
        distancesByCoord.filter(_._2 == minimumDistance) match {
          case onlyOne :: Nil => List(key -> onlyOne)
          case _ => Nil
        }
      }
  }

  def largestArea(coordinates: (Int, Int)*): Int = {
    val result = reduceAllDistancesToShortest(coordinates: _*)
    println(result.values.map(_._1).groupBy(identity).mkString("\n"))
    -1
  }

  case class Coord(x: Int, y: Int)

  object Coord {

    def toCoords(coordinates: (Int, Int)*): Set[Coord] = coordinates.map { case (x, y) => Coord(x, y) }.toSet

  }

  @tailrec
  private def computeAllDistances(coordinates: Set[Coord])
                                 (coordinatesToCompute: Set[Coord],
                                  allCoordinates: Map[Coord, List[(Coord, Int)]] = Map.empty): Map[Coord, List[(Coord, Int)]] =
    coordinatesToCompute match {
      case _ if coordinatesToCompute.isEmpty => allCoordinates
      case _ =>
        val (head, tail) = (coordinatesToCompute.head, coordinatesToCompute.tail)
        val newCoords = computeOneDistances(coordinates)(head, allCoordinates)
        computeAllDistances(coordinates)(tail, newCoords)
    }

  def computeOneDistances(coordinates: Set[Coord])
                         (coordinateToCompute: Coord,
                          allCoordinates: Map[Coord, List[(Coord, Int)]],
                          currentDistance: Int = 1): Map[Coord, List[(Coord, Int)]] = {
    val coordOfEquiDistance = for {
      x <- -currentDistance to currentDistance
      y <- -currentDistance to currentDistance
      if x.abs + y.abs == currentDistance
    } yield {
      Coord(x + coordinateToCompute.x, y + coordinateToCompute.y)
    }

    val reachAnotherCoordinate = coordOfEquiDistance.toSet.intersect(coordinates).nonEmpty

    val newCoords = coordOfEquiDistance.foldLeft(allCoordinates) { case (allCoordinatesCurrent, coord) =>
      allCoordinatesCurrent.updated(
        key = coord,
        value = allCoordinatesCurrent.getOrElse(coord, Nil) :+ (coordinateToCompute -> currentDistance)
      )
    }

    if (reachAnotherCoordinate)
      newCoords
    else
      computeOneDistances(coordinates)(coordinateToCompute, newCoords, currentDistance + 1)
  }

}
