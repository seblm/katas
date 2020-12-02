package name.lemerdy.sebastian.katas.adventofcode._2020.day1

import scala.annotation.tailrec

object ExpenseReportFixer {

  def product2Numbers(expenseReport: List[Long], sum: Long = 2020): Either[String, Long] = expenseReport match {
    case Nil          => Left("expense report can't be empty")
    case head :: tail => productRecursive(sum, head, tail).orElse(product2Numbers(tail, sum))
  }

  def product3Numbers(expenseReport: List[Long], sum: Long = 2020): Either[String, Long] = expenseReport match {
    case Nil           => Left("expense report can't be empty")
    case first :: Nil  => Left(s"expense report can't have only one value $first")
    case first :: tail => product2Numbers(tail, sum - first).map(_ * first).orElse(product3Numbers(tail, sum))
  }

  @tailrec
  private def productRecursive(sum: Long, fixed: Long, numbers: List[Long]): Either[String, Long] = numbers match {
    case head :: _ if fixed + head == sum => Right(fixed * head)
    case _ :: tail                        => productRecursive(sum, fixed, tail)
    case Nil                              => Left(s"there is no other number which $fixed + x = $sum")
  }

}
