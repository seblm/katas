package mowitnow

import mowitnow.MowerClient.PositionsResponse

class MowerClientInternal(client: MowerClient, topX: Int, topY: Int) extends MowerClient:

  var internalPosition: Position = Position(topX, topY)

  def position: Position = internalPosition

  override def computePositions(request: MowerClient.PositionsRequest): PositionsResponse =
    val response = client.computePositions(request)
    response.finalPositions.headOption.foreach(first => internalPosition = first)
    response

  override def toString: String = internalPosition.toString
