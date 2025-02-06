package routes

import cats.effect.IO
import io.circe.generic.auto.*
import io.circe.syntax.*
import models.{Lawn, MowerRequest, MowerResponse}
import org.http4s.*
import org.http4s.circe.*
import org.http4s.dsl.io.*
import services.MowerService
import org.http4s.circe.CirceEntityDecoder.circeEntityDecoder

object MowerRoutes {
  def mowerRoutes(): HttpRoutes[IO] = {
    HttpRoutes.of[IO] {
      case req @ POST -> Root =>
        req.as[MowerRequest].flatMap { request =>
          val lawn = Lawn(request.width, request.height)
          val finalPositions = request.mowers.map { mower =>
            val Array(x, y, dir) = mower.position.split(" ")
            val finalPos = MowerService.moveMower(lawn, (x.toInt, y.toInt, dir.head), mower.instructions)
            s"${finalPos._1} ${finalPos._2} ${finalPos._3}"
          }
          Ok(MowerResponse(finalPositions).asJson)
        }
    }
  }
}
