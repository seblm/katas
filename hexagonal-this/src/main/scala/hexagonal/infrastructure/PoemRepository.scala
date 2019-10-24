package hexagonal.infrastructure

import cats.data.EitherT
import cats.effect.{IO, Resource}
import hexagonal.domain.PoemRepositoryPort
import io.circe.generic.auto._
import io.circe.parser.decode
import monolith.Poems

import scala.io.Source

class PoemRepository extends PoemRepositoryPort[IO] {

  override def find(title: String): EitherT[IO, Throwable, Option[String]] = for {
    file <- EitherT.right(
      Resource.fromAutoCloseable(IO(Source.fromResource("Poetry.json")))
        .use(poems => IO(poems.getLines().mkString("\n"))))
    allPoems <- EitherT.fromEither[IO](decode[Poems](file)).leftMap(new Throwable(_))
  } yield {
    allPoems.poems
      .find(_.title.contains(title))
      .map(_.poem)
  }

}
