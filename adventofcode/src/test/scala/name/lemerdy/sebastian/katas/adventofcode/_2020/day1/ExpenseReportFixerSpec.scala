package name.lemerdy.sebastian.katas.adventofcode._2020.day1

import org.scalatest.OptionValues._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

import scala.io.Source
import scala.util.Try

class ExpenseReportFixerSpec extends AnyFlatSpec {

  lazy val input: List[Long] = Source
    .fromResource("name/lemerdy/sebastian/katas/adventofcode/_2020/day1/input.txt")
    .getLines()
    .flatMap(line => Try(line.toLong).toOption)
    .toList

  "ExpenseReportFixer" should "compute 2 numbers product that sum is 2020" in {
    val expenseReport: List[Long] = List(1721, 979, 366, 299, 675, 1456)

    val result = ExpenseReportFixer.productOf2020Numbers(expenseReport, 2)

    result.value shouldBe 514579
  }

  it should "compute 2 numbers product with real example" in {
    val expenseReport: List[Long] = input

    val result = ExpenseReportFixer.productOf2020Numbers(expenseReport, 2)

    result.value shouldBe 913824
  }

  it should "compute 3 numbers product that sum is 2020" in {
    val expenseReport: List[Long] = List(1721, 979, 366, 299, 675, 1456)

    val result = ExpenseReportFixer.productOf2020Numbers(expenseReport, 3)

    result.value shouldBe 241861950
  }

  it should "compute 3 numbers product with real example" in {
    val expenseReport: List[Long] = input

    val result = ExpenseReportFixer.productOf2020Numbers(expenseReport, 3)

    result.value shouldBe 240889536
  }

}
