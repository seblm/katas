package name.lemerdy.sebastian.katas.adventofcode._2020.day11

import scala.annotation.tailrec

object SeatingSystem {

  def occupiedAdjacentSeats(map: String): Int = {
    val withoutNewLines = map.replaceAll("\n", "")
    val height = map.count(_ == '\n') + 1
    countOccupiedAdjacentSeats(withoutNewLines, withoutNewLines.length / height, height)
  }

  @tailrec
  private def countOccupiedAdjacentSeats(map: String, width: Int, height: Int): Int = {
    val newMap = (for {
      y <- 0 until height
      x <- 0 until width
      seat = map.charAt(y * width + x)
      occupied = adjacents(map, width, height, x, y).count(_ == '#')
    } yield (seat, occupied) match {
      case ('L', 0)                         => '#'
      case ('#', occupied) if occupied >= 4 => 'L'
      case (seat, _)                        => seat
    }).mkString
    if (newMap == map) map.count(_ == '#') else countOccupiedAdjacentSeats(newMap, width, height)
  }

  private def adjacents(map: String, width: Int, height: Int, x: Int, y: Int): String = (for {
    dy <- y - 1 to y + 1 if 0 <= dy && dy < height
    dx <- x - 1 to x + 1 if 0 <= dx && dx < width
    if !(dy == y && dx == x)
  } yield map.charAt(dy * width + dx)).mkString

  def occupiedSeats(map: String): Int = {
    val withoutNewLines = map.replaceAll("\n", "")
    val height = map.count(_ == '\n') + 1
    countOccupiedSeats(withoutNewLines, withoutNewLines.length / height, height)._1
  }

  @tailrec
  def countOccupiedSeats(map: String, width: Int, height: Int): (Int, String) = {
    val newMap = (for {
      y <- 0 until height
      x <- 0 until width
      seat = map.charAt(y * width + x)
      occupied = occupiedSeatsNotAdjacent(map, width, height, x, y)
    } yield (seat, occupied) match {
      case ('L', 0)                         => '#'
      case ('#', occupied) if occupied >= 5 => 'L'
      case (seat, _)                        => seat
    }).mkString
    if (newMap == map) (map.count(_ == '#'), newMap)
    else countOccupiedSeats(newMap, width, height)
  }

  private def occupiedSeatsNotAdjacent(map: String, width: Int, height: Int, x: Int, y: Int): Int =
    List(
      isOccupied(map, width, height, x, y, "right     ")(x => x + 1, y => y + 0),
      isOccupied(map, width, height, x, y, "right down")(x => x + 1, y => y + 1),
      isOccupied(map, width, height, x, y, "      down")(x => x + 0, y => y + 1),
      isOccupied(map, width, height, x, y, "left  down")(x => x - 1, y => y + 1),
      isOccupied(map, width, height, x, y, "left      ")(x => x - 1, y => y + 0),
      isOccupied(map, width, height, x, y, "left  up  ")(x => x - 1, y => y - 1),
      isOccupied(map, width, height, x, y, "      up  ")(x => x + 0, y => y - 1),
      isOccupied(map, width, height, x, y, "right up  ")(x => x + 1, y => y - 1)
    ).count(identity)

  @tailrec
  private def isOccupied(map: String, width: Int, height: Int, x: Int, y: Int, direction: String)(
      xIncrement: Int => Int,
      yIncrement: Int => Int
  ): Boolean = {
    // print(s"isOccupied(map, $width, $height, $x, $y, $direction)")
    val dx = xIncrement(x)
    val dy = yIncrement(y)
    if (dx < 0 || dx >= width || dy < 0 || dy >= height) {
      // println(" out of range => not occupied")
      false
    } else if (map.charAt(dy * width + dx) == '#') {
      // println(" ")
      true
    } else isOccupied(map, width, height, dx, dy, direction)(xIncrement, yIncrement)
  }

}
