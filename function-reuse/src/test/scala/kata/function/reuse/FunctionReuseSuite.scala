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
