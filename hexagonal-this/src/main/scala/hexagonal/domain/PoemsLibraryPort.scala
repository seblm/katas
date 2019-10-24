package hexagonal.domain

import cats.data.EitherT

trait PoemsLibraryPort[F[_]] {

  def printPoem(title: String): EitherT[F, Throwable, String]

}
