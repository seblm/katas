import Kind.Desastre

import scala.annotation.tailrec
import scala.io.Source
import scala.util.{Random, Using}

@main def startGame(): Unit =
  loop(createCastawayIsland())

def createCastawayIsland(): CastawayIsland =
  val deck = Random.shuffle(for {
    resource <- Resource.all
    kind <- Kind.all
    card <- Vector.fill(kind.count)(Card(resource, kind))
  } yield card)
  val players = Vector.range(0, 6 * 4, 6).map(index => Player(deck.slice(index, index + 6)))
  val islands = Vector.fill(4)(Island())
  CastawayIsland(players, islands, deck.drop(6 * 4))

def winner(game: CastawayIsland): Option[Player] =
  game.islands.zipWithIndex.find { case (i, _) => hasAllResources(i) }.map(_._2).map(game.players(_))

def currentPlayer(game: CastawayIsland): Player = game.players(game.currentPlayerIndex)

@tailrec def peekCard(game: CastawayIsland): CastawayIsland =
  if (game.draw.isEmpty) peekCard(game.copy(draw = Random.shuffle(game.discard), discard = Vector.empty))
  else
    val newCard = game.draw.head
    val discard = game.discard.headOption
    // TODO choose between discard and newCard
    play(game.copy(draw = game.draw.tail), newCard)

def play(game: CastawayIsland, newCard: Card): CastawayIsland =
  val myCurrentDeck = currentPlayer(game).cards :+ newCard

  // place a disaster somewhere
  val myDisasters = myCurrentDeck.filter(_.kind == Desastre).map(_.resource)
  if (myDisasters.nonEmpty)
    val islandThatCanHaveMyDisasters = game.islands.zipWithIndex
      .filter(_._2 == game.currentPlayerIndex)
      .map { case (island, index) =>
        index -> island.resources
          .filter { case (resource, _) => myDisasters.contains(resource) }
          .filter { case (_, kind) => kind.exists(_ != Desastre) }
          .keys
      }
      .filter(_._2.nonEmpty)
      .toMap
    val disasterResourcesByIsland = game.islands.zipWithIndex
      .filter(_._2 == game.currentPlayerIndex)
      .map { case (island, index) =>
        index -> island.resources.filter(_._2.exists(_ == Desastre)).keys
      }
      .filter(_._2.nonEmpty)
      .toMap
    ()
  else ()

  game

def hasAllResources(island: Island): Boolean = island.resources.count(_._2.exists(_ != Desastre)) == Resource.all.length

def loop(game: CastawayIsland): Unit =
  println(
    game.islands.zipWithIndex
      .map { case (island, index) =>
        s"$index ${island.resources.toVector.sortBy(_._1.toString).map { case (resource, kind) => s"$resource $kind" }.mkString(", ")}"
      }
      .mkString("islands: ", "\n         ", "")
  )
  println(s"   draw: ${game.draw.length}")
  println(s"discard: ${game.discard.length}${game.discard.headOption.getOrElse("")}")
  println()
  winner(game) match {
    case Some(winner) => println(s"winner is $winner")
    case None         => loop(peekCard(game))
  }
  ()
