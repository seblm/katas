package name.lemerdy.sebastian.pipolo

trait PipoloGame {

  def putCard(card: Card, cardCategory: CardCategory): ActionResult

  def player: Player

}

class PipoloGameStarting extends PipoloGame {

  val firstPlayer: Player = Player("player 1", Deck.apply("player 1"))

  override def putCard(card: Card, cardCategory: CardCategory): ActionResult =
    ActionResult(NextPlayer, new PipoloGamePlaying(Player("player 2", Deck.apply("player 2")), firstPlayer.without(card)))

  override def player: Player = firstPlayer

}

class PipoloGamePlaying(currentPlayer: Player, otherPlayers: Player*) extends PipoloGame {

  override def putCard(card: Card, cardCategory: CardCategory): ActionResult = {
    val currentPlayerWithoutCard: Player = currentPlayer.without(card)

    if (currentPlayerWithoutCard.deck.cards.isEmpty) {
      ActionResult(GameOver, new PipoloGameOver(currentPlayerWithoutCard, otherPlayers: _*))
    } else {
      ActionResult(NextPlayer, new PipoloGamePlaying(otherPlayers(0), otherPlayers.tail :+ currentPlayerWithoutCard: _*))
    }
  }

  override def player: Player = currentPlayer

  override def toString: String = s"PipoloGamePlaying($currentPlayer, $otherPlayers)"

}

class PipoloGameOver(winner: Player, otherPLayers: Player*) extends PipoloGame {

  override def putCard(card: Card, cardCategory: CardCategory): ActionResult = ActionResult(GameIsAlreadyOver, this)

  override def player: Player = winner

}
