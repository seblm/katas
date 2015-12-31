package name.lemerdy.sebastian.katas.matchsticks

import org.scalatest.{FlatSpec, Matchers}

class LengthCharacterSpec extends FlatSpec with Matchers {

  "empty string" should "compute lengths" in {
    val lengthCharacter = new LengthCharacter("\"\"")

    val lengths = (lengthCharacter.numberOfCharactersOfStringCode, lengthCharacter.numberOfCharactersInMemory)

    lengths shouldBe (2, 0)
  }

  it should "be encoded" in {
    val emptyString = new LengthCharacter("\"\"")

    val encoded = emptyString.encode

    encoded shouldBe "\"\\\"\\\"\""
  }

  "some string without any escape sequence" should "compute lengths" in {
    val lengthCharacter = new LengthCharacter("\"abc\"")

    val lengths = (lengthCharacter.numberOfCharactersOfStringCode, lengthCharacter.numberOfCharactersInMemory)

    lengths shouldBe (5, 3)
  }

  it should "be encoded" in {
    val abc = new LengthCharacter("\"abc\"")

    val encoded = abc.encode

    encoded shouldBe "\"\\\"abc\\\"\""
  }

  "some string with quote escape sequence" should "compute lengths" in {
    val lengthCharacter = new LengthCharacter("\"aaa\\\"aaa\"")

    val lengths = (lengthCharacter.numberOfCharactersOfStringCode, lengthCharacter.numberOfCharactersInMemory)

    lengths shouldBe (10, 7)
  }

  it should "be encoded" in {
    val abc = new LengthCharacter("\"aaa\\\"aaa\"")

    val encoded = abc.encode

    encoded shouldBe "\"\\\"aaa\\\\\\\"aaa\\\"\""
  }

  "some string with hexadecimal escape sequence" should "compute lengths" in {
    val lengthCharacter = new LengthCharacter("\"\\x27\"")

    val lengths = (lengthCharacter.numberOfCharactersOfStringCode, lengthCharacter.numberOfCharactersInMemory)

    lengths shouldBe (6, 1)
  }

  it should "compute length with a to e hexadecimal characters" in {
    val lengthCharacter = new LengthCharacter("\"\\xab\"")

    val lengths = (lengthCharacter.numberOfCharactersOfStringCode, lengthCharacter.numberOfCharactersInMemory)

    lengths shouldBe (6, 1)
  }

  it should "be encoded" in {
    val x27 = new LengthCharacter("\"\\x27\"")

    val encoded = x27.encode

    encoded shouldBe "\"\\\"\\\\x27\\\"\""
  }

  "some string with backslash escape sequence" should "compute lengths" in {
    val lengthCharacter = new LengthCharacter("\"aaa\\\\aaa\"")

    val lengths = (lengthCharacter.numberOfCharactersOfStringCode, lengthCharacter.numberOfCharactersInMemory)

    lengths shouldBe (10, 7)
  }

}
