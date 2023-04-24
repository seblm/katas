package name.lemerdy.sebastian.katas.adventofcode._2018.day2

import scala.annotation.tailrec

object InventoryManagementSystem {

  def checksum(boxIDs: Iterator[String]): BigInt = {
    val (twoOccurrences, threeOccurences) = countLetters(boxIDs.toSeq)
    twoOccurrences * threeOccurences
  }

  def commonLettersBetweenTwoCorrectBoxIDs(boxIDs: Iterator[String]): String =
    commonLettersBetweenTwoCorrectBoxIDs(boxIDs.toList)

  @tailrec
  private def countLetters(boxIDs: Seq[String], twoLetters: Int = 0, threeLetters: Int = 0): (Int, Int) = boxIDs match {
    case Nil => (twoLetters, threeLetters)
    case boxID :: tail =>
      countLetters(
        boxIDs = tail,
        twoLetters = twoLetters + booleanToInt(hasLetterOfExactlyNumber(boxID, 2)),
        threeLetters = threeLetters + booleanToInt(hasLetterOfExactlyNumber(boxID, 3))
      )
  }

  private def hasLetterOfExactlyNumber(boxID: String, exactly: Int): Boolean =
    boxID.groupBy(identity).values.map(_.length).exists(_ == exactly)

  private def booleanToInt(value: Boolean): Int = if (value) 1 else 0

  private def commonLettersBetweenTwoCorrectBoxIDs(boxIDs: List[String]): String = boxIDs match {
    case Nil => ""
    case boxID :: tail =>
      tail
        .flatMap(sameLettersIfOnlyOneLetterIsDifferent(boxID))
        .headOption
        .getOrElse(commonLettersBetweenTwoCorrectBoxIDs(tail))
  }

  private def sameLettersIfOnlyOneLetterIsDifferent(firstBoxID: String)(secondBoxID: String): Option[String] =
    firstBoxID.zip(secondBoxID).partition { case (firstLetter, secondLetter) => firstLetter == secondLetter } match {
      case (same, different) if different.length == 1 => Some(same.map(_._1).mkString(""))
      case _                                          => None
    }

}
