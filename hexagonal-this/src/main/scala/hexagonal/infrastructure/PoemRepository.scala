package hexagonal.infrastructure

import hexagonal.domain.PoemRepositoryPort
import io.circe
import io.circe.generic.auto._
import io.circe.parser.decode
import monolith.Poems

import scala.io.Source

class PoemRepository extends PoemRepositoryPort {

  val poems: Either[circe.Error, Poems] = decode[Poems](Source.fromResource("Poetry.json").getLines().mkString("\n"))

  override def find(title: String): Option[String] = poems.toOption
    .flatMap(_.poems.find(_.title.contains(title)))
    .map(_.poem)


}
