package name.lemerdy.sebastian.pipolo

import name.lemerdy.sebastian.pipolo.CardCategory._
import name.lemerdy.sebastian.pipolo.CardClass._

case class Deck(cards: Seq[Card]) {

  def without(card: Card) = new Deck(cards.dropWhile(card.equals))

}

object Deck {

  def apply(playerName: String): Deck = {
    val cards: Seq[Card] = playerName match {
      case "player 1" => Seq(
        Card(class0, category0),
        Card(class0, category1),
        Card(class0, category2),
        Card(class0, category3),
        Card(class1, category0),
        Card(class1, category1),
        Card(class1, category2),
        Card(class1, category3),
      )
      case "player 2" => Seq(
        Card(class2, category0),
        Card(class2, category1),
        Card(class2, category2),
        Card(class2, category3),
        Card(class3, category0),
        Card(class3, category1),
        Card(class3, category2),
        Card(class3, category3),
      )
    }

    new Deck(cards)
  }

}
