package mowitnow

import mowitnow.MowerClient.{PositionsRequest, PositionsResponse}

trait MowerClient:
  
  def computePositions(request: PositionsRequest): PositionsResponse

object MowerClient:
  
  case class PositionsRequestMower(position: Position, instructions: Seq[Instruction])

  case class PositionsRequest(width: Int, height: Int, mowers: Seq[PositionsRequestMower])

  case class PositionsResponse(finalPositions: Seq[Position])
