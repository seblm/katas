package controllers

import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers._
import play.api.libs.json._
import models._

class MowerControllerSpec extends PlaySpec {

  "MowerController" should {

    "return final positions of mowers" in {
      val json = Json.obj(
        "width" -> 6,
        "height" -> 6,
        "mowers" -> Json.arr(
          Json.obj(
            "position" -> "1 2 N",
            "instructions" -> "GAGAGAGAA"
          ),
          Json.obj(
            "position" -> "3 3 E",
            "instructions" -> "AADAADADDA"
          )
        )
      )

      val request = FakeRequest(POST, "/").withBody(json)

      val response = route(app, request).get

      status(response) mustBe OK
      contentType(response) mustBe Some("application/json")
      (contentAsJson(response) \ "finalPositions").as[Seq[String]] mustBe Seq("1 3 N", "5 1 E")
    }
  }
}
