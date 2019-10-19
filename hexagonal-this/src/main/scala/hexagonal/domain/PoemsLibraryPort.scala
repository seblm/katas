package hexagonal.domain

trait PoemsLibraryPort {
  def printPoem(title: String): String
}
