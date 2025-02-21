package controllers

import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers._
import play.api.mvc._
import play.api.libs.json.Json

class MowersControllerSpec extends PlaySpec {

  "MowersController" should {
    "respond with OK status" in {
      val controller = new MowersController(stubControllerComponents())

      val jsonRequest = Json.parse("""
        {
          "width": 5,
          "height": 5,
          "mowers": []
        }
      """)

      val result = controller.index().apply(FakeRequest(POST, "/").withJsonBody(jsonRequest))

      status(result) mustBe OK
    }
  }
}
