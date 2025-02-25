package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.libs.json.{JsValue, Json}
import play.api.test._
import play.api.test.Helpers._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 *
 * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
 */
class HomeControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "HomeController GET" should {

    "render the index page from a new instance of controller" in {
      val controller = new HomeController(stubControllerComponents())
      val home = controller.index().apply(FakeRequest(GET, "/"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
    }

    "render the index page from the application" in {
      val controller = inject[HomeController]
      val home = controller.index().apply(FakeRequest(GET, "/"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
    }

    "render the index page from the router" in {
      val request = FakeRequest(GET, "/")
      val home = route(app, request).get

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
    }
  }

  "HomeController POST" should{

    "give the correct result with the teacher's test JSON" in {
      val controller = inject[HomeController]

      val jsonBody = Json.parse(
        """{
          "width": 6,
          "height": 6,
          "mowers": [
            {
              "position": "1 2 N",
              "instructions": "GAGAGAGAA"
            },
            {
              "position": "3 3 E",
              "instructions": "AADAADADDA"
            }
          ]
        }"""
      )

      val home = controller.mowersCalculator().apply(FakeRequest(POST, "/").withJsonBody(jsonBody))
      val expectedJson: JsValue = Json.parse(
        """{
          "finalPositions": [
            "1 3 N",
            "5 1 E"
          ]
        }"""
      )

      status(home) mustBe OK
      contentType(home) mustBe Some("application/json")
      contentAsJson(home) mustBe expectedJson
    }

    "return a BadRequest if the JSON is invalid" in {
      val controller = inject[HomeController]

      //retirer volontairement certaines propriétés du JSON
      val jsonBody = Json.parse(
        """{
          "width": 6,

          "mowers": [
            {

              "instructions": "GAGAGAGAA"
            },
            {
              "position": "3 3 E"

            }
          ]
        }"""
      )

      val home = controller.mowersCalculator().apply(FakeRequest(POST, "/").withJsonBody(jsonBody))
      val expectedJson: JsValue = Json.parse(
        """{
             "error": "JSON invalide"
           }"""
      )

      status(home) mustBe BAD_REQUEST
      contentType(home) mustBe Some("application/json")
      contentAsJson(home) mustBe expectedJson
    }

    "return a BadRequest if the JSON is empty" in {
      val controller = inject[HomeController]

      //requete sans json dans le body
      val home = controller.mowersCalculator().apply(FakeRequest(POST, "/"))
      val expectedJson: JsValue = Json.parse(
        """{
             "error": "Aucun JSON dans le body"
           }"""
      )

      status(home) mustBe BAD_REQUEST
      contentType(home) mustBe Some("application/json")
      contentAsJson(home) mustBe expectedJson
    }
  }
}
