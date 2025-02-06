package server

import cats.effect.*
import org.http4s.server.blaze.*
import routes.MowerRoutes

object MowerServer extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {
    val httpApp = MowerRoutes.mowerRoutes().orNotFound
    BlazeServerBuilder[IO]
      .bindHttp(8080, "0.0.0.0")
      .withHttpApp(httpApp)
      .serve
      .compile
      .drain
      .as(ExitCode.Success)
  }
}
