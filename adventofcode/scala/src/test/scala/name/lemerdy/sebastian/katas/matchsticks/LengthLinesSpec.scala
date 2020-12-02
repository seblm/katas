package name.lemerdy.sebastian.katas.matchsticks

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

class LengthLinesSpec extends AnyFlatSpec {

  "all four strings" should "compute lengths" in {
    val lengthLines = new LengthLines(
      "" +
        "\"\"\n" +
        "\"abc\"\n" +
        "\"aaa\\\"aaa\"\n" +
        "\"\\x27\""
    )

    val lengths = (lengthLines.numberOfCharactersOfStringCode, lengthLines.numberOfCharactersInMemory)

    lengths shouldBe (23, 11)
  }

}
