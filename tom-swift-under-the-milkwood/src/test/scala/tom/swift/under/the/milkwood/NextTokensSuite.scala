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
    println(s"number of keys: ${analyzed.keys.size}")
    val sorted =
      analyzed.toSeq.sortBy(_._2.length).reverse.take(5).map((key, words) => s"$key ${words.length}").mkString("\n")
    println(s"keys with higher word number:\n$sorted")
    val randomStart = analyzed.keys.to(LazyList)((Math.random() * analyzed.keys.size).toInt)
    val book = Range(0, 100).foldLeft((randomStart, randomStart.split(" ").head)):
      case ((key, book), _) =>
        val selecteds = analyzed(key)
        val selected = selecteds((Math.random() * selecteds.size).toInt)
        val tokens = key.split(" ")
        (tokens.last + " " + selected, book + " " + tokens.head)
    println(book)
