package name.lemerdy.sebastian.pipolo

import name.lemerdy.sebastian.pipolo.CardCategory.category0
import org.scalatest.{FlatSpec, Matchers, OptionValues}

class PipoloSpec extends FlatSpec with Matchers with OptionValues {

  "Pipolo" should "end without any lyer" in {
    val pipolo00 = new PipoloGameStarting()

    val pipolo01 = pipolo00.putCard(pipolo00.player.deck.cards.head, category0).pipoloGame
    val pipolo02 = pipolo01.putCard(pipolo01.player.deck.cards.head, category0).pipoloGame
    val pipolo03 = pipolo02.putCard(pipolo02.player.deck.cards.head, category0).pipoloGame
    val pipolo04 = pipolo03.putCard(pipolo03.player.deck.cards.head, category0).pipoloGame
    val pipolo05 = pipolo04.putCard(pipolo04.player.deck.cards.head, category0).pipoloGame
    val pipolo06 = pipolo05.putCard(pipolo05.player.deck.cards.head, category0).pipoloGame
    val pipolo07 = pipolo06.putCard(pipolo06.player.deck.cards.head, category0).pipoloGame
    val pipolo08 = pipolo07.putCard(pipolo07.player.deck.cards.head, category0).pipoloGame
    val pipolo09 = pipolo08.putCard(pipolo08.player.deck.cards.head, category0).pipoloGame
    val pipolo10 = pipolo09.putCard(pipolo09.player.deck.cards.head, category0).pipoloGame
    val pipolo11 = pipolo10.putCard(pipolo10.player.deck.cards.head, category0).pipoloGame
    val pipolo12 = pipolo11.putCard(pipolo11.player.deck.cards.head, category0).pipoloGame
    val pipolo13 = pipolo12.putCard(pipolo12.player.deck.cards.head, category0).pipoloGame
    val pipolo14 = pipolo13.putCard(pipolo13.player.deck.cards.head, category0).pipoloGame
    val pipolo15 = pipolo14.putCard(pipolo14.player.deck.cards.head, category0)

    pipolo15.event should be(GameOver)
    pipolo15.pipoloGame should be(a[PipoloGameOver])
    pipolo15.pipoloGame.player.name should be("player 1")
  }

  it should "remove first played card" in {
    val pipolo00 = new PipoloGameStarting()

    val card = pipolo00.player.deck.cards.head
    val pipolo01 = pipolo00.putCard(card, category0).pipoloGame

    val pipolo02 = pipolo01.putCard(pipolo01.player.deck.cards.head, category0).pipoloGame
    pipolo02.player.deck.cards should not contain card
  }

  it should "switch from first to second player" in {
    val pipolo00 = new PipoloGameStarting()

    val pipolo01 = pipolo00.putCard(pipolo00.player.deck.cards.head, category0).pipoloGame

    pipolo01.player.name should be("player 2")
  }

  it should "remove second played card" in {
    val pipolo00 = new PipoloGameStarting()
    val pipolo01 = pipolo00.putCard(pipolo00.player.deck.cards.head, category0).pipoloGame

    val card = pipolo01.player.deck.cards.head
    val pipolo02 = pipolo01.putCard(card, category0).pipoloGame

    val pipolo03 = pipolo02.putCard(pipolo02.player.deck.cards.head, category0).pipoloGame
    pipolo03.player.deck.cards should not contain card
  }

}
