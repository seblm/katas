package tom.swift.under.the.milkwood

import munit.FunSuite

class NextTokensSuite extends FunSuite:
  test("should analyze next tokens"):
    val book = List("I", "wish", "I", "may", "I", "wish", "I", "might")

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

  test("should run with an entire book"):
    val analyzed = NextTokens.analyze(NextTokens.run())
    val randomStart = analyzed.keys.to(LazyList)((Math.random() * analyzed.keys.size).toInt)
    val book = Range(0, 100).foldLeft((randomStart, randomStart.split(" ").head)):
      case ((key, book), _) =>
        val selecteds = analyzed(key)
        val selected = selecteds((Math.random() * selecteds.size).toInt)
        val tokens = key.split(" ")
        (tokens.last + " " + selected, book + " " + tokens.head)
    println(book)
