package tondeuse.routes

import cats.effect.IO
import cats.effect.unsafe.implicits.global
import org.http4s.*
import org.http4s.implicits.*
import org.http4s.circe.*
import io.circe.syntax.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.Json
import org.scalatest.funsuite.AnyFunSuite
import models.*
import routes.MowerRoutes
import services.MowerService

class MowerRoutesTest extends AnyFunSuite {

  test("POST / should return the final positions of mowers") {
    val requestJson =
      """
        {
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
        }
      """

    val request = Request[IO](Method.POST, uri"/").withEntity(requestJson)
    val response = MowerRoutes.mowerRoutes().orNotFound.run(request).unsafeRunSync()

    assert(response.status == Status.Ok)

    val responseBody = response.as[String].unsafeRunSync()

    val expectedJson =
      """
        {
          "finalPositions": ["1 3 N", "5 1 E"]
        }
      """

    val parsedResponse = parse(responseBody).getOrElse(Json.Null)
    val parsedExpected = parse(expectedJson).getOrElse(Json.Null)

    assert(parsedResponse == parsedExpected)
  }
}
