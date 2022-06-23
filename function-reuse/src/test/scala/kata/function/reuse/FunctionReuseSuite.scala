package kata.function.reuse

import kata.function.reuse.Item.*
import munit.FunSuite

import java.util.UUID
import scala.util.{Failure, Success, Try}

class FunctionReuseSuite extends FunSuite:
  val cheapItem: Item = Item(UUID.randomUUID(), "playstation 5", 499, UUID.randomUUID())
  val expensiveItem: Item = Item(UUID.randomUUID(), "playstation 5", 509, UUID.randomUUID())

  test("item cheaper than 500 is affordable") {
    assertEquals(affordable(cheapItem), true)
  }

  test("item more expensive than 500 is not affordable") {
    assertEquals(affordable(expensiveItem), false)
  }

  test("some cheap item is affordable") {
    val someCheapItem: Option[Item] = Some(cheapItem)

    assertEquals(affordableF(someCheapItem), Some(true))
  }

  test("some expensive item is not affordable") {
    val someExpensiveItem: Option[Item] = Some(expensiveItem)

    assertEquals(affordableF(someExpensiveItem), Some(false))
  }

  test("can't tell if some item is affordable when no value is provided") {
    assertEquals(affordableF(None), None)
  }

  test("successful cheap item is affordable") {
    val successfulCheapItem: Try[Item] = Success(cheapItem)

    assertEquals(affordableF(successfulCheapItem), Success(true))
  }

  test("successful expensive item is not affordable") {
    val successfulExpensiveItem: Try[Item] = Success(expensiveItem)

    assertEquals(affordableF(successfulExpensiveItem), Success(false))
  }

  test("can't tell if some item is affordable when an error occurs") {
    val exception = new Throwable()
    val failure: Try[Item] = Failure(exception)

    assertEquals(affordableF(failure), Failure(exception))
  }

  test("cheapest item between two is the one with lower price") {
    assertEquals(cheapest(cheapItem, expensiveItem), cheapItem)
  }

  test("cheapest item between two defined is the one with lower price") {
    val someCheapItem: Option[Item] = Some(cheapItem)
    val someExpensiveItem: Option[Item] = Some(expensiveItem)

    assertEquals(cheapestF(someCheapItem, someExpensiveItem), Some(cheapItem))
  }

  test("can't get cheapest item when one item is undefined") {
    val someCheapItem: Option[Item] = Some(cheapItem)

    assertEquals(cheapestF(someCheapItem, None), None)
  }

  test("cheapest item between two successful is the one with lower price") {
    val successfulCheapItem: Try[Item] = Success(cheapItem)
    val successfulExpensiveItem: Try[Item] = Success(expensiveItem)

    assertEquals(cheapestF(successfulCheapItem, successfulExpensiveItem), Success(cheapItem))
  }

  test("can't get cheapest item when an error occurs") {
    val successfulCheapItem: Try[Item] = Success(cheapItem)
    val throwable = new Throwable()
    val failure: Try[Item] = Failure(throwable)

    assertEquals(cheapestF(successfulCheapItem, failure), Failure(throwable))
  }

  test("sum item prices to compute total cost") {
    assertEquals(totalCost(List(cheapItem, expensiveItem)), 1008)
  }

  test("sum item prices to compute total cost of some items") {
    val someCheapItem: Option[Item] = Some(cheapItem)
    val someExpensiveItem: Option[Item] = Some(expensiveItem)

    assertEquals(totalCostF(List(someCheapItem, someExpensiveItem)), Some(1008))
  }

  test("can't sum item prices to compute total cost when one item is undefined") {
    val someCheapItem: Option[Item] = Some(cheapItem)

    assertEquals(totalCostF(List(someCheapItem, None)), None)
  }

  test("sum item prices to compute total cost of successful items") {
    val successfulCheapItem: Try[Item] = Success(cheapItem)
    val successfulExpensiveItem: Try[Item] = Success(expensiveItem)

    assertEquals(totalCostF(List(successfulCheapItem, successfulExpensiveItem)), Success(1008))
  }

  test("can't sum item prices to compute total cost when an error occurred") {
    val successfulCheapItem: Try[Item] = Success(cheapItem)
    val throwable = new Throwable()
    val failure: Try[Item] = Failure(throwable)

    assertEquals(totalCostF(List(successfulCheapItem, failure)), Failure(throwable))
  }
