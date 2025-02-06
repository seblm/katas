package serveur

import cats.effect._
import com.comcast.ip4s._
import org.http4s.ember.server.EmberServerBuilder

object serveur extends IOApp {
  def run(args: List[String]): IO[ExitCode] =
    EmberServerBuilder.default[IO]
      .withHost(ip"0.0.0.0")
      .withPort(port"8080")
      .withHttpApp(routes.httpApp)
      .build
      .use(_ => IO.never)
      .as(ExitCode.Success)
}
