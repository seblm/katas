package name.lemerdy.sebastian.katas.adventofcode._2021.day05

object HydrothermalVenture:

  private val lineRegex = """(\d+),(\d+) -> (\d+),(\d+)""".r

  def computeOverlapCount(lines: List[String]): Int =
    lines
      .flatMap { case lineRegex(x1, y1, x2, y2) =>
        Line(Point(x1.toInt, y1.toInt), Point(x2.toInt, y2.toInt)).points()
      }
      .groupBy(identity)
      .count(_._2.length >= 2)

  def computeDiagonalOverlapCount(lines: List[String]): Int =
    lines
      .flatMap { case lineRegex(x1, y1, x2, y2) =>
        Line(Point(x1.toInt, y1.toInt), Point(x2.toInt, y2.toInt)).points(includeDiagnoals = true)
      }
      .groupBy(identity)
      .count(_._2.length >= 2)

  case class Line(start: Point, end: Point):
    def points(includeDiagnoals: Boolean = false): List[Point] = (start, end) match {
      case (Point(x1, y1), Point(x2, y2)) if y1 == y2 =>
        Range.inclusive(Math.min(x1, x2), Math.max(x1, x2)).map(x => Point(x, y1)).toList
      case (Point(x1, y1), Point(x2, y2)) if x1 == x2 =>
        Range.inclusive(Math.min(y1, y2), Math.max(y1, y2)).map(y => Point(x1, y)).toList
      case (Point(x1, y1), Point(x2, y2)) if includeDiagnoals && x1 < x2 && y1 < y2 =>
        Range.inclusive(0, x2 - x1).map(i => Point(x1 + i, y1 + i)).toList
      case (Point(x1, y1), Point(x2, y2)) if includeDiagnoals && x1 < x2 && y1 > y2 =>
        Range.inclusive(0, x2 - x1).map(i => Point(x1 + i, y1 - i)).toList
      case (Point(x1, y1), Point(x2, y2)) if includeDiagnoals && x1 > x2 && y1 < y2 =>
        Range.inclusive(0, x1 - x2).map(i => Point(x1 - i, y1 + i)).toList
      case (Point(x1, y1), Point(x2, y2)) if includeDiagnoals && x1 > x2 && y1 > y2 =>
        Range.inclusive(0, x1 - x2).map(i => Point(x1 - i, y1 - i)).toList
      case _ =>
        List.empty
    }

  case class Point(x: Int, y: Int)
