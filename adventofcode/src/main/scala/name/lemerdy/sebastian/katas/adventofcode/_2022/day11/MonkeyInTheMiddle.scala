package name.lemerdy.sebastian.katas.adventofcode._2022.day11

import scala.annotation.tailrec
import scala.jdk.StreamConverters.*
import scala.util.matching.Regex

object MonkeyInTheMiddle:

  def parseNotes(input: String): Map[Int, MonkeyWithItems] =
    input
      .split("\n\n")
      .map(_.lines().toScala(Vector))
      .map(parseMonkeyWithItems)
      .zipWithIndex
      .map { case (monkey, index) => index -> monkey }
      .toMap

  def computeMonkeyBusiness(
      monkeys: Map[Int, MonkeyWithItems],
      rounds: Int = 20,
      mode: ComputeMode = DivideByThree
  ): Long = {
    computeRounds(monkeys, rounds, mode).values.map(_.monkey.inspected).to(Vector).sorted.takeRight(2).product
  }

  @tailrec
  def computeRounds(
      monkeys: Map[Int, MonkeyWithItems],
      n: Int,
      mode: ComputeMode = DivideByThree
  ): Map[Int, MonkeyWithItems] =
    if n == 0 then monkeys else computeRounds(computeRound(monkeys, mode), n - 1, mode)

  def computeRound(monkeys: Map[Int, MonkeyWithItems], mode: ComputeMode = DivideByThree): Map[Int, MonkeyWithItems] =
    computeRound(monkeys, monkeys.keys.toVector.sorted, mode)

  @tailrec
  private def computeRound(
      monkeys: Map[Int, MonkeyWithItems],
      monkeyIds: Vector[Int],
      mode: ComputeMode
  ): Map[Int, MonkeyWithItems] =
    if monkeyIds.isEmpty then monkeys
    else computeRound(inspectAll(monkeys, monkeyIds.head, mode), monkeyIds.tail, mode)

  @tailrec
  private def inspectAll(
      monkeys: Map[Int, MonkeyWithItems],
      monkeyId: Int,
      mode: ComputeMode
  ): Map[Int, MonkeyWithItems] =
    val monkeyWithItems = monkeys(monkeyId)
    val items = monkeyWithItems.items
    if items.isEmpty then monkeys
    else
      val monkey = monkeyWithItems.monkey
      val computeResult = compute(monkey.operation, items.head)
      val newItem =
        mode match {
          case DivideByThree => computeResult / 3
          case DivisorsModulo =>
            val allDivisors = monkeys.map(_._2.monkey.test.toLong)
            computeResult % allDivisors.product
        }
      val isDivisible = newItem % monkey.test == 0
      val newMonkey = if (isDivisible) monkey.testTrue else monkey.testFalse
      val newMonkeys = monkeys
        .updatedWith(monkeyId)(
          _.map(m => m.copy(monkey = m.monkey.copy(inspected = m.monkey.inspected + 1), items = items.tail))
        )
        .updatedWith(newMonkey)(_.map(m => m.copy(items = m.items :+ newItem)))
      inspectAll(newMonkeys, monkeyId, mode)

  private def compute(operation: Operation, item: Long): Long =
    val rightValue = resolve(item)(operation.rightValue)
    operation.operand match {
      case Add      => item + rightValue
      case Multiply => item * rightValue
    }

  private def resolve(item: Long): Term => Long =
    case OldValue        => item
    case IntValue(value) => value

  private val startingItemsRegex: Regex = " {2}Starting items: (.+)".r
  private val operationRegex: Regex = """ {2}Operation: new = old ([+*]) (.+)""".r
  private val testRegex: Regex = """ {2}Test: divisible by (\d+)""".r
  private val testResultRegex: Regex = """ {4}If (?:true|false): throw to monkey (\d+)""".r
  private def parseMonkeyWithItems(lines: Vector[String]): MonkeyWithItems =
    val op = operationRegex.findAllIn(lines(2))
    MonkeyWithItems(
      monkey = Monkey(
        operation = Operation(Operand(op.group(1)), rightValue = Term(op.group(2))),
        test = testRegex.findAllIn(lines(3)).group(1).toInt,
        testTrue = testResultRegex.findAllIn(lines(4)).group(1).toInt,
        testFalse = testResultRegex.findAllIn(lines(5)).group(1).toInt
      ),
      items = startingItemsRegex.findAllIn(lines(1)).group(1).split(", ").toVector.map(_.toInt)
    )

  case class MonkeyWithItems(monkey: Monkey, items: Vector[Long])
  case class Monkey(operation: Operation, test: Int, testTrue: Int, testFalse: Int, inspected: Long = 0)
  case class Operation(operand: Operand, rightValue: Term)
  sealed trait Operand
  object Add extends Operand
  object Multiply extends Operand
  private object Operand:
    def apply(operand: String): Operand = operand match {
      case "+" => Add
      case "*" => Multiply
    }
  sealed trait Term
  object OldValue extends Term
  case class IntValue(value: Int) extends Term
  private object Term:
    def apply(term: String): Term = if term == "old" then OldValue else IntValue(term.toInt)

  sealed trait ComputeMode
  object DivideByThree extends ComputeMode
  object DivisorsModulo extends ComputeMode
