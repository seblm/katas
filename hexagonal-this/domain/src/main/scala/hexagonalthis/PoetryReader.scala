package hexagonalthis

import cats.Id

class PoetryReader[F[_]](poetryLibrary: IObtainPoems[F]) extends IRequestVerses[F] {
  override def giveMeSomePoetry(): String =
    """If you could read a leaf or tree
      |you’d have no need of books.
      |-- © Alistair Cockburn (1987)""".stripMargin
}

object PoetryReader {
  def apply(): IRequestVerses = new PoetryReader(new IObtainPoems[Id] {
    override def getAPoem(): Id[String] = ""
  })
}