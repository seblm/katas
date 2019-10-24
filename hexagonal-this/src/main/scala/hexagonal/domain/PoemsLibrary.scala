package hexagonal.domain

import cats.Functor
import cats.data.EitherT

class PoemsLibrary[F[_] : Functor](poemRepository: PoemRepositoryPort[F]) extends PoemsLibraryPort[F] {

  override def printPoem(title: String): EitherT[F, Throwable, String] =
    poemRepository.find(title).map {
      case None => s"no poem found with title $title"
      case Some(poem) => poem.toUpperCase
    }

}
