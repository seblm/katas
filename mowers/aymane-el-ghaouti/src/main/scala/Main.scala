import cats.effect.{IO, IOApp}
import org.http4s.implicits.*
import org.http4s.server.blaze.BlazeServerBuilder
import routes.MowerRoutes

object Main extends IOApp.Simple {
  override def run: IO[Unit] = {
    val httpApp = MowerRoutes.mowerRoutes().orNotFound

    BlazeServerBuilder[IO]
      .bindHttp(8080, "0.0.0.0")
      .withHttpApp(httpApp)
      .serve
      .compile
      .drain
  }
}
