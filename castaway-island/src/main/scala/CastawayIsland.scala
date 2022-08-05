import scala.util.Random

case class CastawayIsland(
    players: Vector[Player],
    islands: Vector[Island],
    draw: Vector[Card],
    discard: Vector[Card] = Vector.empty,
    currentPlayerIndex: Int = 0
)
