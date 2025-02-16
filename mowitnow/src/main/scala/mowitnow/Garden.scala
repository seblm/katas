package mowitnow

import mowitnow.GardenClient.GardenState
import mowitnow.Orientation.North

import scala.collection.mutable
import scala.util.Try

class Garden:
  private var topX: Int = 5
  private var topY: Int = 5
  private val mowers = mutable.ListBuffer[MowerClientInternal]()
  private val gardenClients = mutable.ListBuffer[GardenClient]()
  def subscribeGarden(garden: GardenClient): Unit =
    gardenClients.addOne(garden)
    garden.updateGardenState(GardenState(width = topX, height = topY, mowers = mowers.map(_.position).toSeq))
    ()
  def updateTopX(topX: Int): Unit =
    this.topX = topX
    gardenClients.foreach: gardenClient =>
      gardenClient
        .updateGardenState(GardenState(width = topX, height = topY, mowers = mowers.map(_.position).toSeq))
    ()
  def updateTopY(topY: Int): Unit =
    this.topY = topY
    gardenClients.foreach: gardenClient =>
      gardenClient
        .updateGardenState(GardenState(width = topX, height = topY, mowers = mowers.map(_.position).toSeq))
    ()
  def subscribeMower(mower: MowerClient): Unit =
    mowers.addOne(MowerClientInternal(mower, topX, topY))
    gardenClients.foreach: gardenClient =>
      gardenClient
        .updateGardenState(GardenState(width = topX, height = topY, mowers = mowers.map(_.position).toSeq))
    ()

object Garden:

  def toString(garden: Garden): String =
    s"${garden.topX} ${garden.topY}"
      + Option.when(garden.mowers.nonEmpty)("\n").getOrElse("")
      + garden.mowers.mkString("\n")
