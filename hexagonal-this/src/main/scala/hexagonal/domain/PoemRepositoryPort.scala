package hexagonal.domain

import cats.data.EitherT

trait PoemRepositoryPort[F[_]] {

  def find(title: String): EitherT[F, Throwable, Option[String]]

}
