package name.lemerdy.sebastian.katas.adventofcode._2020.day1

object ExpenseReportFixer {

  def productOf2020Numbers(expenseReport: List[Long], n: Int): Option[Long] =
    expenseReport.combinations(n).find(_.sum == 2020).map(_.product)

}
