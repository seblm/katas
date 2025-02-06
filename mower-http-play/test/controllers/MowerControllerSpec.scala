package controllers

import org.scalatestplus.play.*
import org.scalatestplus.play.guice.*
import play.api.test.*
import play.api.test.Helpers.*

class MowerControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting:

  "MowerController POST" should:
    "compute final position from a new instance of controller" in:
      val controller = new MowerController(stubControllerComponents())

      val request =
        FakeRequest(POST, "/").withBody(Mowers(6, 6, Seq(Mower("1 2 N", "GAGAGAGAA"), Mower("3 3 E", "AADAADADDA"))))
      val finalPositions = controller.compute()(request)

      status(finalPositions) mustBe OK
      contentType(finalPositions) mustBe Some("application/json")
      contentAsJson(finalPositions).as[Response] mustBe Response(Seq("1 3 N", "5 1 E"))
