package name.lemerdy.sebastian.katas.adventofcode._2022.day11

import name.lemerdy.sebastian.katas.adventofcode._2022.day11.MonkeyInTheMiddle.*
import org.scalatest.TryValues.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

import scala.io.Source
import scala.util.Using

class MonkeyInTheMiddleSpec extends AnyFlatSpec:

  private val monkey0: Monkey = Monkey(Operation(Multiply, IntValue(19)), 23, 2, 3)
  private val monkey1: Monkey = Monkey(Operation(Add, IntValue(6)), 19, 2, 0)
  private val monkey2: Monkey = Monkey(Operation(Multiply, OldValue), 13, 1, 3)
  private val monkey3: Monkey = Monkey(Operation(Add, IntValue(3)), 17, 0, 1)
  private val startingMonkeys: Map[Int, MonkeyWithItems] = Map(
    0 -> MonkeyWithItems(monkey0, Vector(79, 98)),
    1 -> MonkeyWithItems(monkey1, Vector(54, 65, 75, 74)),
    2 -> MonkeyWithItems(monkey2, Vector(79, 60, 97)),
    3 -> MonkeyWithItems(monkey3, Vector(74))
  )

  "MonkeyInTheMiddle" should "parse notes" in {
    MonkeyInTheMiddle.parseNotes("""Monkey 0:
                                   |  Starting items: 79, 98
                                   |  Operation: new = old * 19
                                   |  Test: divisible by 23
                                   |    If true: throw to monkey 2
                                   |    If false: throw to monkey 3
                                   |
                                   |Monkey 1:
                                   |  Starting items: 54, 65, 75, 74
                                   |  Operation: new = old + 6
                                   |  Test: divisible by 19
                                   |    If true: throw to monkey 2
                                   |    If false: throw to monkey 0
                                   |
                                   |Monkey 2:
                                   |  Starting items: 79, 60, 97
                                   |  Operation: new = old * old
                                   |  Test: divisible by 13
                                   |    If true: throw to monkey 1
                                   |    If false: throw to monkey 3
                                   |
                                   |Monkey 3:
                                   |  Starting items: 74
                                   |  Operation: new = old + 3
                                   |  Test: divisible by 17
                                   |    If true: throw to monkey 0
                                   |    If false: throw to monkey 1""".stripMargin) should be(startingMonkeys)
  }

  it should "compute a round" in {
    MonkeyInTheMiddle.computeRound(startingMonkeys) should be(
      Map(
        0 -> MonkeyWithItems(monkey0.copy(inspected = 2), Vector(20L, 23L, 27L, 26L)),
        1 -> MonkeyWithItems(monkey1.copy(inspected = 4), Vector(2080L, 25L, 167L, 207L, 401L, 1046L)),
        2 -> MonkeyWithItems(monkey2.copy(inspected = 3), Vector.empty),
        3 -> MonkeyWithItems(monkey3.copy(inspected = 5), Vector.empty)
      )
    )
  }

  it should "compute 20 rounds" in {
    MonkeyInTheMiddle.computeRounds(startingMonkeys, 20) should be(
      Map(
        0 -> MonkeyWithItems(monkey0.copy(inspected = 101), Vector(10, 12, 14, 26, 34)),
        1 -> MonkeyWithItems(monkey1.copy(inspected = 95), Vector(245, 93, 53, 199, 115)),
        2 -> MonkeyWithItems(monkey2.copy(inspected = 7), Vector.empty),
        3 -> MonkeyWithItems(monkey3.copy(inspected = 105), Vector.empty)
      )
    )
  }

  it should "compute monkey business" in {
    MonkeyInTheMiddle.computeMonkeyBusiness(startingMonkeys) should be(10605)
  }

  private lazy val input =
    Using(Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2022/day11/input.txt"))(
      _.mkString
    ).success.value

  it should "compute monkey business with real example" in {
    MonkeyInTheMiddle.computeMonkeyBusiness(MonkeyInTheMiddle.parseNotes(input)) should be(76_728)
  }

  it should "compute 10 000 rounds without divide by three" in {
    MonkeyInTheMiddle.computeMonkeyBusiness(startingMonkeys, 10_000, DivisorsModulo) should be(2713310158L)
  }

  it should "compute 10 000 rounds without divide by three with real example" in {
    MonkeyInTheMiddle.computeMonkeyBusiness(MonkeyInTheMiddle.parseNotes(input), 10_000, DivisorsModulo) should be(
      21553910156L
    )
  }
