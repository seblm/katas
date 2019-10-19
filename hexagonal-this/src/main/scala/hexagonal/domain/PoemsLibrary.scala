package hexagonal.domain

class PoemsLibrary(poemRepository: PoemRepositoryPort) extends PoemsLibraryPort {

  override def printPoem(title: String): String =
    poemRepository.find(title) match {
      case None => s"no poem found with title $title"
      case Some(poem) => poem.toUpperCase
    }

}
