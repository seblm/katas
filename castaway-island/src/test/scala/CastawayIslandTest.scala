import Kind.{Desastre, ResourceKind, Riposte}
import Resource.{Betail, Bois, Eau, Feu}
import munit.FunSuite

import scala.util.Random

class CastawayIslandTest extends FunSuite:

  test("current player should have 6 cards into his hands") {
    val castawayIsland = CastawayIsland1()

    assertEquals(castawayIsland.currentPlayerCards.length, 6)
  }

  test("current player cards should be some values") {
    val castawayIsland = CastawayIsland1(CastawayIsland(Random(1)))

    assertEquals(
      castawayIsland.currentPlayerCards,
      Vector(
        Card(Betail, Desastre),
        Card(Bois, ResourceKind),
        Card(Eau, ResourceKind),
        Card(Bois, Riposte),
        Card(Bois, Desastre),
        Card(Feu, Riposte)
      )
    )
  }

  test("current player should put a resource into board") {
    val castawayIsland = CastawayIsland1(CastawayIsland(Random(1)))

    val nextCastawayIsland = castawayIsland.put(Card(Bois, ResourceKind))

    assertEquals(nextCastawayIsland.kind(islandIndex = 0, resource = Bois), ResourceKind)
  }
