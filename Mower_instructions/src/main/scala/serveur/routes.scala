package serveur

import cats.effect.IO
import io.circe.syntax._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl.io._
import org.http4s.server.Router
import modele._
import simulation.mowersimulation
object routes {
  implicit val decoder: EntityDecoder[IO, Simulation] = jsonOf[IO, Simulation]

  val routes = HttpRoutes.of[IO] {
    case GET -> Root => Ok("Server is running")
    case GET -> Root / "status" => Ok("Mower API is up and running!")

    case req @ POST -> Root / "mower" / "simulation" =>
      req.as[Simulation].attempt.flatMap {
        case Left(error) =>
          println(s"Erreur de parsing JSON: ${error.getMessage}")
          BadRequest(s"Erreur de parsing: ${error.getMessage}")

        case Right(simulation) =>
          println(s"Simulation reçue: $simulation")
          val result = mowersimulation.runSimulation(simulation)
          Ok(result.asJson)
      }
  }

  val httpApp: HttpApp[IO] = Router(
    "/" -> routes
  ).orNotFound
}
