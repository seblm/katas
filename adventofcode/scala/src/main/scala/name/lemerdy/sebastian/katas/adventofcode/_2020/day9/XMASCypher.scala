package name.lemerdy.sebastian.katas.adventofcode._2020.day9

import scala.annotation.tailrec

object XMASCypher {

  def wrongNumber(preambleLength: Int, numbers: String): Long = {
    val (preamble, number :: tail) = toList(numbers).splitAt(preambleLength)
    wrongNumber(preamble, number, tail)
  }

  @tailrec
  private def wrongNumber(preamble: List[Long], number: Long, tail: List[Long]): Long =
    if (preamble.combinations(2).map(_.sum).contains(number))
      wrongNumber(preamble.tail.appended(number), tail.head, tail.tail)
    else
      number

  def sumOfMinAndMaxContiguousThatSumTo(sum: Long, numbers: String): Long = {
    val numbersAsLong = toList(numbers)
    sumOfMinAndMaxContiguousThatSumTo(sum, numbersAsLong, numbersAsLong.take(2), numbersAsLong.drop(2))
  }

  @tailrec
  private def sumOfMinAndMaxContiguousThatSumTo(
      sum: Long,
      numbers: List[Long],
      current: List[Long],
      rest: List[Long]
  ): Long =
    if (current.sum == sum)
      current.min + current.max
    else if (current.sum > sum)
      sumOfMinAndMaxContiguousThatSumTo(sum, numbers.tail, numbers.tail.take(2), numbers.tail.drop(2))
    else
      sumOfMinAndMaxContiguousThatSumTo(sum, numbers, current.appended(rest.head), rest.tail)

  private def toList(numbers: String) = numbers.split('\n').map(_.toLong).toList

}
