package name.lemerdy.sebastian.katas.adventofcode._2021.day09

object SmokeBasin:

  def computeSumOfRiskLevelsOfAllLowPoints(heightmap: Seq[String]): Long =
    extractLowPoints(heightmap).map(_.height + 1).sum

  private def extractLowPoints(heightmap: Seq[String]): Seq[Position] =
    heightmap.indices.flatMap { y =>
      Range(0, heightmap.head.length).flatMap { x =>
        val current = position(heightmap, x, y).get
        Option.when(adjacents(heightmap, current).forall(_.height > current.height))(current)
      }
    }

  def adjacents(heightmap: Seq[String], reference: Position): Seq[Position] =
    Seq(
      position(heightmap, reference.x, reference.y - 1), // up
      position(heightmap, reference.x, reference.y + 1), // down
      position(heightmap, reference.x - 1, reference.y), // left
      position(heightmap, reference.x + 1, reference.y) // right
    ).flatten

  private def position(heightmap: Seq[String], x: Int, y: Int): Option[Position] =
    Option.when(x >= 0 && x < heightmap.head.length && y >= 0 && y < heightmap.length) {
      Position(x, y, heightmap(y).substring(x, x + 1).toInt)
    }

  def productOfThreeLargestBasinSizes(heightmap: Seq[String]): Long =
    basinSizes(heightmap).sorted.reverse.take(3).product

  def basinSizes(heightmap: Seq[String]): Seq[Int] =
    extractLowPoints(heightmap).map { lowpoint =>
      walk(heightmap, Set(lowpoint), Set(lowpoint)).size
    }

  private def walk(heightmap: Seq[String], toVisit: Set[Position], visited: Set[Position]): Set[Position] =
    toVisit.headOption.fold(visited) { first =>
      val newToVisit = adjacents(heightmap, first)
        .filterNot(toVisit.contains)
        .filterNot(visited.contains)
        .filterNot(_.height == 9)
        .toSet
      walk(heightmap, newToVisit ++ toVisit.tail, visited.incl(first))
    }

  case class Position(x: Int, y: Int, height: Int)
