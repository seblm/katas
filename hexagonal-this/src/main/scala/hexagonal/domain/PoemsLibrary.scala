package hexagonal.domain

import cats.Functor
import cats.syntax.functor._

class PoemsLibrary[F[_] : Functor](poemRepository: PoemRepositoryPort[F]) extends PoemsLibraryPort[F] {

  override def printPoem(title: String): F[String] =
    poemRepository.find(title).map {
      case None => s"no poem found with title $title"
      case Some(poem) => poem.toUpperCase
    }

}
