package name.lemerdy.sebastian.katas.adventofcode._2018.day25

import java.lang.Math.abs

object FourDimensionalAdventure {

  case class Coord(x: Int, y: Int, z: Int, t: Int) {
    def distance(other: Coord): Int = abs(x - other.x) + abs(y - other.y) + abs(z - other.z) + abs(t - other.t)
  }

  def toCoord(coordinates: String): Coord = {
    val coords = coordinates.split(",").map(_.trim.toInt)
    Coord(coords(0), coords(1), coords(2), coords(3))
  }

  def count(setOfCoordinates: String): Int = {
    val coords = setOfCoordinates.split("\n").map(toCoord).toList

    val combinations = for {
      (first, firstIndex) <- coords.zipWithIndex
      (second, secondIndex) <- coords.zipWithIndex
      if secondIndex > firstIndex && first.distance(second) <= 3
    } yield (first, second)

    val neighborhoods = coords.map { c =>
      combinations.flatMap {
        case (a, b) if a == c ⇒ List(b, c)
        case (a, b) if b == c ⇒ List(a, c)
        case _ ⇒ List(c)
      }
    }.map(_.toSet)

    val grouped = neighborhoods.foldLeft(Set.empty[Set[Coord]]) { case (constellations, neighborhood) ⇒
      val (within, without) = constellations.partition(constellation ⇒ constellation.exists(neighborhood.contains))
      without + (within.flatten ++ neighborhood)
    }

    grouped.size
  }

}
