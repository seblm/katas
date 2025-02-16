package mowitnow

import mowitnow.GardenClient.GardenState
import mowitnow.GardenSuite.{MockGardenClient, MockMowerClient}
import mowitnow.MowerClient.{PositionsRequest, PositionsResponse}
import munit.FunSuite

import scala.collection.mutable

class GardenSuite extends FunSuite:

  test("should handle basic scenario"):
    val garden = Garden()
    val gardenClient = MockGardenClient()
    garden.subscribeGarden(gardenClient)
    assertEquals(gardenClient.receivedGardenState(), Some(GardenState(5, 5, Seq.empty)))
    garden.updateTopX(8)
    assertEquals(gardenClient.receivedGardenState().map(_.width), Some(8))
    garden.updateTopY(9)
    assertEquals(gardenClient.receivedGardenState().map(_.height), Some(9))
    val mowerClient = MockMowerClient()
    garden.subscribeMower(mowerClient)
    val mowers = gardenClient.receivedGardenState().map(_.mowers)
    assertEquals(mowers.map(_.length), Some(1))

object GardenSuite:

  class MockGardenClient extends GardenClient:
    private var lastGardenState: Option[GardenState] = None
    def receivedGardenState(): Option[GardenState] = lastGardenState
    override def updateGardenState(state: GardenState): Unit =
      lastGardenState = Some(state)
      ()

  class MockMowerClient extends MowerClient:
    private val requests = mutable.ListBuffer[PositionsRequest]()
    override def computePositions(request: PositionsRequest): PositionsResponse =
      requests.addOne(request)
      PositionsResponse(request.mowers.map(_.position))
    def clearRequests(): Seq[PositionsRequest] =
      val allRequests = requests.toSeq
      requests.clear()
      allRequests
