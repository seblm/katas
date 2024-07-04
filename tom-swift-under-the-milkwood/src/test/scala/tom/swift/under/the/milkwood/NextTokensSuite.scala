package tom.swift.under.the.milkwood

import munit.FunSuite

class NextTokensSuite extends FunSuite:
  test("should analyze next tokens"):
    val book = "I wish I may I wish I might"

    val nextTokens = NextTokens.analyze(book)

    assertEquals(
      nextTokens,
      Map(
        "I wish" -> Vector("I", "I"),
        "wish I" -> Vector("may", "might"),
        "may I" -> Vector("wish"),
        "I may" -> Vector("I")
      )
    )
