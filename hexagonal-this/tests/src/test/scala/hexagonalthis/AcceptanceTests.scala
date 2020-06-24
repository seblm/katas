package hexagonalthis

import cats.Id
import cats.syntax.applicative._
import org.scalatest.FunSuite
import org.scalatest.Matchers._
import org.scalatestplus.mockito.MockitoSugar.mock
import org.mockito.Mockito.when

class AcceptanceTests extends FunSuite {

  test("Should give verses when asked for poetry") {
    val poetryReader: IRequestVerses[Id] = PoetryReader()

    val verses: String = poetryReader.giveMeSomePoetry()

    verses shouldBe
      """If you could read a leaf or tree
        |you’d have no need of books.
        |-- © Alistair Cockburn (1987)""".stripMargin
  }


  test("Should give verses from a PoetryLibrary") {
    val poetryLibrary: IObtainPoems[Id] = mock[IObtainPoems[Id]]
    val poetryReader: IRequestVerses[Id] = new PoetryReader[Id](poetryLibrary)
    when(poetryLibrary.getAPoem()).thenReturn("".pure)

    val verses: String = poetryReader.giveMeSomePoetry()

    verses shouldBe
      """If you could read a leaf or tree
        |you’d have no need of books.
        |-- © Alistair Cockburn (1987)""".stripMargin
  }

}
