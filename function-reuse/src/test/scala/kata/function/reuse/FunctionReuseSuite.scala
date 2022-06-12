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

  test("item more expensive than 500 is note affordable") {
    assertEquals(affordable(expensiveItem), false)
  }
