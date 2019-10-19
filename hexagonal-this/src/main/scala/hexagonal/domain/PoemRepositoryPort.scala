package hexagonal.domain

trait PoemRepositoryPort {
  def find(title: String): Option[String]
}
