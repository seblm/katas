package name.lemerdy.sebastian.pipolo

case class Player(name: String, deck: Deck) {

  def without(card: Card): Player = Player(name, deck.without(card))

}
