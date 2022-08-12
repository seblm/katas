import scala.util.Random

class CastawayIsland1(state: CastawayIsland = CastawayIsland(Random())):

  def currentPlayerCards: Vector[Card] = state.players(state.currentPlayerIndex).cards

  def put(card: Card): CastawayIsland1 =
    CastawayIsland1(
      state.copy(
        players = state.players.zipWithIndex.map {
          case (currentPlayer, index) if index == state.currentPlayerIndex =>
            currentPlayer.copy(currentPlayer.cards.filter(_ == card))
          case (otherPlayer, _) => otherPlayer
        },
        islands = state.islands.zipWithIndex.map {
          case (currentPlayerIsland, index) if index == state.currentPlayerIndex =>
            currentPlayerIsland.copy(currentPlayerIsland.resources.map {
              case (resource, cards) if resource == card.resource => resource -> cards.appended(card)
              case (resource, cards)                              => resource -> cards
            })
          case (otherIsland, _) =>
            otherIsland
        },
        currentPlayerIndex = state.currentPlayerIndex + 1 % 4
      )
    )

  def kind(islandIndex: Int, resource: Resource): Kind =
    state.islands(islandIndex).resources(resource).last.kind

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

  def apply(random: Random): CastawayIsland =
    val deck = random.shuffle(for {
      resource <- Resource.all
      kind <- Kind.all
      card <- Vector.fill(kind.count)(Card(resource, kind))
    } yield card)
    val players = Vector.range(0, 6 * 4, 6).map(index => Player(deck.slice(index, index + 6)))
    val islands = Vector.fill(4)(Island())
    CastawayIsland(players, islands, deck.drop(6 * 4))
