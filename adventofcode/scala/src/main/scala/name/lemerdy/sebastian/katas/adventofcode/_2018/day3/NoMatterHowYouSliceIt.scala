package name.lemerdy.sebastian.katas.adventofcode._2018.day3

import scala.annotation.tailrec
import scala.util.matching.Regex

object NoMatterHowYouSliceIt {

  private case class Claim(x: Int, y: Int, width: Int, height: Int) {

    lazy val area: Set[(Int, Int)] = (for {
      pointX <- x until x + width
      pointY <- y until y + width
    } yield (pointX, pointY)).toSet

  }

  private object Claim {

    def toClaim(value: String): Option[Claim] = value match {
      case regex(x, y, width, height) ⇒ Some(Claim(x.toInt, y.toInt, width.toInt, height.toInt))
      case _ ⇒ None
    }

    private lazy val regex: Regex = """#\d+ @ (\d+),(\d+): (\d+)x(\d+)""".r

  }

  def computeOverlap(claims: Iterator[String]): Int = computeOverlapAll(claims.flatMap(Claim.toClaim).toSeq)

  //  private def computeOverlapAll(claims: Seq[Claim]): Int = {
  //    println(s"${claims.length}")
  //    (for {
  //      i <- claims.indices
  //      j <- claims.indices if i < j
  //    } yield {
  //      claims(i).area.intersect(claims(j).area)
  //    }).toSet.flatten.size
  //  }

  @tailrec
  private def computeOverlapAll(claims: Seq[Claim], overlaps: Set[(Int, Int)] = Set.empty): Int = claims match {
    case Nil ⇒ overlaps.size
    case claim #:: tail ⇒ computeOverlapAll(tail, overlaps ++ computeOverlapOne(claim, tail.toList))
  }

  @tailrec
  private def computeOverlapOne(claim: Claim,
                                claims: List[Claim],
                                overlaps: Set[(Int, Int)] = Set.empty): Set[(Int, Int)] = claims match {
    case Nil ⇒ overlaps
    case otherClaim :: tail ⇒ computeOverlapOne(claim, tail, overlaps ++ claim.area.intersect(otherClaim.area))
  }

}
