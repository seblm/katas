package name.lemerdy.sebastian.katas.adventofcode._2024.day05

import org.scalatest.TryValues.given
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

import scala.io.Source
import scala.util.Using

class PrintQueueSpec extends AnyFlatSpec:

  private val smallInput = """47|53
                             |97|13
                             |97|61
                             |97|47
                             |75|29
                             |61|13
                             |75|53
                             |29|13
                             |97|29
                             |53|29
                             |61|53
                             |97|53
                             |61|29
                             |47|13
                             |75|47
                             |97|75
                             |47|61
                             |75|61
                             |47|29
                             |75|13
                             |53|13
                             |
                             |75,47,61,53,29
                             |97,61,53,29,13
                             |75,29,13
                             |75,97,47,61,53
                             |61,13,29
                             |97,13,75,29,47""".stripMargin

  it should "check order" in:
    val result = PrintQueue.checkOrder(smallInput)

    result should be(143)

  private lazy val input =
    Using(Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2024/day05/input.txt"))(
      _.mkString
    ).success.value

  it should "check order with real example" in:
    val result = PrintQueue.checkOrder(input)

    result should be(5_452)

  it should "fix order" in:
    val result = PrintQueue.fixOrder(smallInput)

    result should be(123)

  it should "fix order with real example" in:
    val result = PrintQueue.fixOrder(input)

    result should be(4_598)
