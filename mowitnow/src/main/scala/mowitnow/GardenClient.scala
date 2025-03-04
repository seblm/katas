package mowitnow

import mowitnow.GardenClient.GardenState

trait GardenClient:

  def updateGardenState(state: GardenState): Unit

object GardenClient:

  case class GardenState(width: Int, height: Int, mowers: Seq[Position])
