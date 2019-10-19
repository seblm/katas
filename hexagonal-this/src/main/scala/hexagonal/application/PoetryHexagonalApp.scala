package hexagonal.application

import hexagonal.domain.{PoemRepositoryPort, PoemsLibrary, PoemsLibraryPort}
import hexagonal.infrastructure.PoemRepository

object PoetryHexagonalApp extends App {

  private val infrastructure: PoemRepositoryPort = new PoemRepository
  private val domain: PoemsLibraryPort = new PoemsLibrary(infrastructure)

  println("Please enter a poem title:")
  val title = Console.in.readLine()

  private val poem: String = domain.printPoem(title)

  println(poem)

}
