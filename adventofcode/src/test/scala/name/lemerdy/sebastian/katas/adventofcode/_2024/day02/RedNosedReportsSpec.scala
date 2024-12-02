package name.lemerdy.sebastian.katas.adventofcode._2024.day02

import org.scalatest.TryValues.given
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

import scala.io.Source
import scala.util.Using

class RedNosedReportsSpec extends AnyFlatSpec:

  it should "evaluate reports" in:
    val result = RedNosedReports.evaluateReports("""7 6 4 2 1
                                                   |1 2 7 8 9
                                                   |9 7 6 2 1
                                                   |1 3 2 4 5
                                                   |8 6 4 4 1
                                                   |1 3 6 7 9""".stripMargin)

    result should be(2)

  private lazy val input =
    Using(Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2024/day02/input.txt"))(
      _.mkString
    ).success.value

  it should "compute distance with real example" in:
    val result = RedNosedReports.evaluateReports(input)

    result should be(591)

  it should "evaluate reports with tolerance" in:
    val result = RedNosedReports.evaluateReportsTolerant("""7 6 4 2 1
                                                           |1 2 7 8 9
                                                           |9 7 6 2 1
                                                           |1 3 2 4 5
                                                           |8 6 4 4 1
                                                           |1 3 6 7 9""".stripMargin)

    result should be(4)

  it should "evaluate reports with tolerance with real example" in:
    val result = RedNosedReports.evaluateReportsTolerant(input)

    result should be(621)
