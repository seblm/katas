import Kind.{Desastre, ResourceKind}

import scala.annotation.tailrec
import scala.io.Source
import scala.util.{Random, Using}

@main def startGame(): Unit =
  loop(CastawayIsland())

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

def playThisCard(game: CastawayIsland, currentDeck: Vector[Card], card: Card): Vector[Player] =
  game.players.zipWithIndex.map {
    case (player, i) if i == game.currentPlayerIndex =>
      player.copy(cards = {
        val (a, b) = currentDeck.partition(_ == card)
        b ++ a.tail
      })
    case (otherPlayer, _) => otherPlayer
  }

def play(game: CastawayIsland, newCard: Card): CastawayIsland =
  val myCurrentDeck = currentPlayer(game).cards :+ newCard

  // place a disaster somewhere
  val myDisasters = myCurrentDeck.filter(_.kind == Desastre)
  if (myDisasters.nonEmpty)
    val islandThatCanHaveMyDisastersWithLeastDisasters = game.islands.zipWithIndex
      .filterNot(_._2 == game.currentPlayerIndex)
      .map { case (island, index) =>
        index -> (island.resources.flatMap {
          case (resource, cards)
              if myDisasters.map(_.resource).contains(resource) && cards.lastOption.exists(_.kind != Desastre) =>
            myDisasters.find(_.resource == resource)
          case _ => None
        }, island.resources.count(_._2.exists(_.resource == Desastre)))
      }
      .filter(_._2._1.nonEmpty)
      .sortBy(_._2._2)
      .map { case (index, (disasterCards, _)) => (disasterCards.head, index) }
      .headOption
    islandThatCanHaveMyDisastersWithLeastDisasters match {
      case Some((disasterCard, index)) =>
        println(s"player ${game.currentPlayerIndex} will put $disasterCard to island $index")
        game.copy(
          players = playThisCard(game, myCurrentDeck, disasterCard),
          islands = game.islands.zipWithIndex.map {
            case (island, i) if i == index =>
              island.copy(resources = island.resources.map {
                case (r, cards) if r == disasterCard.resource => r -> (cards :+ disasterCard)
                case other                                    => other
              })
            case (island, _) => island
          },
          currentPlayerIndex = (game.currentPlayerIndex + 1) % 4
        )
      case None =>
        // fix a resource
        // build a resource
        val myResources = myCurrentDeck.filter(_.kind == ResourceKind)
        println(myResources.mkString("myResources: ", ", ", ""))
        val myIslandEmptyResourceToBuild = game.islands.zipWithIndex
          .filter(_._2 == game.currentPlayerIndex)
          .map(_._1)
          .map(island =>
            island.resources.flatMap {
              case (resource, cards) if myResources.map(_.resource).contains(resource) && cards.isEmpty =>
                myResources.find(_.resource == resource)
              case _ => None
            }
          )
          .head
          .headOption
        println(s"myIslandEmptyResourceToBuild: $myIslandEmptyResourceToBuild")
        myIslandEmptyResourceToBuild match {
          case Some(myIslandEmptyResourceToBuild1) =>
            game.copy(
              players = playThisCard(game, myCurrentDeck, myIslandEmptyResourceToBuild1),
              islands = game.islands.zipWithIndex.map {
                case (island, i) if i == game.currentPlayerIndex =>
                  island.copy(resources = island.resources.map {
                    case (resource, cards) if resource == myIslandEmptyResourceToBuild1.resource && cards.isEmpty =>
                      resource -> (cards :+ myIslandEmptyResourceToBuild1)
                    case other => other
                  })
                case (island, _) => island
              },
              currentPlayerIndex = (game.currentPlayerIndex + 1) % 4
            )
          case None =>
            game
        }
    }
  else game

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
