package name.lemerdy.sebastian.katas.adventofcode._2018.day6

import name.lemerdy.sebastian.katas.adventofcode._2018.day6.ChronalCoordinates.Coord
import org.scalatest.{FlatSpec, Matchers}

class ChronalCoordinatesSpec extends FlatSpec with Matchers {

  "ChronalCoordinates" should "find largest area" ignore {
    ChronalCoordinates.largestArea(
      1 → 1,
      1 → 6,
      8 → 3,
      3 → 4,
      5 → 5,
      8 → 9,
    ) should be(17)
  }

  "it" should "display shortest letter" ignore {
    val result = ChronalCoordinates.reduceAllDistancesToShortest(
      1 → 1,
      1 → 6,
      8 → 3,
      3 → 4,
      5 → 5,
      8 → 9,
    )
    toString(result) should be(
      "" +
        "aaaaa.cccc\n" +
        "aAaaa.cccc\n" +
        "aaaddecccc\n" +
        "aadddeccCc\n" +
        "..dDdeeccc\n" +
        "bb.deEeecc\n" +
        "bBb.eeee..\n" +
        "bbb.eeefff\n" +
        "bbb.eeffff\n" +
        "bbb.ffffFf"
    )
  }

  private def toLetter(c: Coord): Option[Char] = c match {
    case Coord(1, 1) ⇒ Some('A')
    case Coord(1, 6) ⇒ Some('B')
    case Coord(8, 3) ⇒ Some('C')
    case Coord(3, 4) ⇒ Some('D')
    case Coord(5, 5) ⇒ Some('E')
    case Coord(8, 9) ⇒ Some('F')
    case _ ⇒ None
  }

  def toString(result: Map[Coord, (Coord, Int)]): String =
    (for (y ← 0 to 9) yield (
      for {
        x ← 0 to 9
        coord = Coord(x, y)
      } yield
        toLetter(coord) match {
          case Some(l1) ⇒ l1.toString
          case None ⇒ toLetter(result.mapValues(_._1).getOrElse(coord, coord))
            .getOrElse('.')
            .toString
            .toLowerCase
        }
      ).mkString
      ).mkString("\n")
}
