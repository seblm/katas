package hexagonalthis

import cats.{Applicative, Id}
import cats.syntax.applicative._

class PoetryReader[F[_] : Applicative](poetryLibrary: IObtainPoems[F]) extends IRequestVerses[F] {

  override def giveMeSomePoetry(): F[String] =
    """If you could read a leaf or tree
      |you’d have no need of books.
      |-- © Alistair Cockburn (1987)""".stripMargin.pure

}

object PoetryReader {

  def apply(): PoetryReader[Id] = new PoetryReader[Id](UniquePoem)

  object UniquePoem extends IObtainPoems[Id] {

  }

}