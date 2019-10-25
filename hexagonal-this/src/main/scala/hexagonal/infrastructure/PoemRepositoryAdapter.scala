package hexagonal.infrastructure

import cats.effect.IO
import hexagonal.domain.PoemRepositoryPort
import io.circe.generic.auto._
import io.circe.parser.decode
import monolith.Poems

import scala.io.Source
import scala.util.Try

class PoemRepositoryAdapter extends PoemRepositoryPort[IO] {

  override def find(title: String): IO[Option[String]] = for {
    poems <- IO.fromTry(Try(Source.fromResource("Poetry.json").getLines().mkString))
    allPoems <- IO.fromEither(decode[Poems](poems))
  } yield {
    allPoems.poems
      .find(_.title.contains(title))
      .map(_.poem)
  }

}
