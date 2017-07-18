package name.lemerdy.sebastian.kata.labyrinth

import scala.io.StdIn

class Place(val x: Int, val y: Int) {
  var up: Option[Place] = None
  var down: Option[Place] = None
  var left: Option[Place] = None
  var right: Option[Place] = None
  var alreadyVisited: Boolean = false

  def exit(w: Int, h: Int): Boolean = x == 0 || x == w - 1 || y == 0 || y == h - 1

  override def toString: String = s"($x, $y)"
}

object Solution extends App {

  private def createPlaces(h: Int, y: Int): List[Place] =
    y match {
      case v if v == h => Nil
      case _ => createPlaces(y, 0, StdIn.readLine.toList) ++ createPlaces(h, y + 1)
    }

  private def createPlaces(y: Int, x: Int, line: List[Char]): List[Place] =
    line match {
      case Nil => Nil
      case '#' :: tail => createPlaces(y, x + 1, tail)
      case '.' :: tail => new Place(x, y) :: createPlaces(y, x + 1, tail)
      case _ => createPlaces(y, x + 1, Nil)
    }

  private def findNeighbor(maybeNeighbor: Option[Place]): List[Place] =
    maybeNeighbor.map { neighbor =>
      if (neighbor.alreadyVisited) {
        Nil
      } else if (neighbor.exit(w, h)) {
        List(neighbor)
      } else {
        find(neighbor)
      }
    }
      .getOrElse(Nil)

  private def find(place: Place): List[Place] = {
    place.alreadyVisited = true
    findNeighbor(place.up) ++ findNeighbor(place.left) ++ findNeighbor(place.right) ++ findNeighbor(place.down)
  }

  private def placeOrderRelation(left: Place, right: Place): Boolean =
    if (left.x == right.x) left.y < right.y else left.x < right.x

  val Array(w, h) = for (i <- StdIn.readLine.split(" ")) yield i.toInt
  val Array(x, y) = for (i <- StdIn.readLine.split(" ")) yield i.toInt

  val places: List[Place] = createPlaces(h, 0)

  places.foreach { p =>
    p.left  = places.find(o => o.x == p.x - 1 && o.y == p.y    )
    p.right = places.find(o => o.x == p.x + 1 && o.y == p.y    )
    p.up    = places.find(o => o.x == p.x     && o.y == p.y - 1)
    p.down  = places.find(o => o.x == p.x     && o.y == p.y + 1)
  }

  print(places.find(p => p.x == x && p.y == y)
    .map(firstPlace => find(firstPlace))
    .map { reachableExits =>
      val size = reachableExits.size
      val exits = reachableExits
        .sortWith(placeOrderRelation)
        .map(p => s"${p.x} ${p.y}").mkString("\n")

      if (size == 0) "0" else s"$size\n$exits"
    }
    .getOrElse("0"))

}