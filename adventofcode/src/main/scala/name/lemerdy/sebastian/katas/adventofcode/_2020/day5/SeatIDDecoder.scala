package name.lemerdy.sebastian.katas.adventofcode._2020.day5

object SeatIDDecoder {

  def findMySeatID(specifications: String): Int = {
    val sortedSeatIDs = seatIDs(specifications).sorted

    sortedSeatIDs
      .zip(sortedSeatIDs.drop(1))
      .map { case (current, next) =>
        (current + 1, next - current)
      }
      .find(_._2 != 1)
      .map(_._1)
      .getOrElse(0)
  }

  def highestSeatID(specifications: String): Int = seatIDs(specifications).max

  private def seatIDs(specifications: String): Array[Int] =
    specifications.split('\n').map(decode)

  def decode(specification: String): Int = Integer.parseInt(
    specification.replaceAll("F", "0").replaceAll("B", "1").replaceAll("L", "0").replaceAll("R", "1"),
    2
  )

}
