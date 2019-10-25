package hexagonal.domain

trait PoemsLibraryPort[F[_]] {

  def printPoem(title: String): F[String]

}
