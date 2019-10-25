package hexagonal.domain

trait PoemRepositoryPort[F[_]] {

  def find(title: String): F[Option[String]]

}
