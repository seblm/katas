package hexagonalthis

import cats.Id
import org.mockito.BDDMockito.given
import org.scalatest.FunSuite
import org.scalatest.Matchers._
import org.scalatestplus.mockito.MockitoSugar.mock

class AcceptanceTests extends FunSuite {

  test("Should give verses when asked for poetry") {
    val poetryReader: IRequestVerses = PoetryReader()

    val verses = poetryReader.giveMeSomePoetry()

    verses shouldEqual
      """If you could read a leaf or tree
        |you’d have no need of books.
        |-- © Alistair Cockburn (1987)""".stripMargin
  }


  test("Should give verses from a PoetryLibrary") {
    val poetryLibrary: IObtainPoems[Id] = mock[IObtainPoems[Id]]
    given(poetryLibrary.getAPoem()).willReturn(
      """I want to sleep
        |Swat the flies
        |Softly, please.
        |
        |-- Masaoka Shiki (1867-1902)""".stripMargin)
    val poetryReader: IRequestVerses = new PoetryReader(poetryLibrary)

    val verses = poetryReader.giveMeSomePoetry()

    verses shouldEqual
      """If you could read a leaf or tree
        |you’d have no need of books.
        |-- © Alistair Cockburn (1987)""".stripMargin
  }

}
