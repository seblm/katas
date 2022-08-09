import scala.util.Random

case class CastawayIsland(
    players: Vector[Player],
    islands: Vector[Island],
    draw: Vector[Card],
    discard: Vector[Card] = Vector.empty,
    currentPlayerIndex: Int = 0
)

object CastawayIsland:

  def apply(): CastawayIsland = CastawayIsland(Random())

  def apply(seed: Long): CastawayIsland = CastawayIsland(Random(seed))

  private def apply(random: Random): CastawayIsland =
    val deck = random.shuffle(for {
      resource <- Resource.all
      kind <- Kind.all
      card <- Vector.fill(kind.count)(Card(resource, kind))
    } yield card)
    val players = Vector.range(0, 6 * 4, 6).map(index => Player(deck.slice(index, index + 6)))
    val islands = Vector.fill(4)(Island())
    CastawayIsland(players, islands, deck.drop(6 * 4))
