package tom.swift.under.the.milkwood

import scala.jdk.StreamConverters.given
import java.io.{BufferedReader, InputStreamReader}
import java.util.Locale
import java.util.Locale.FRANCE
import scala.util.Using

object NextTokens:
  import tom.swift.under.the.milkwood.NextTokens.Accumulator.{OneToken, TwoTokens, Empty}

  def run(): List[String] =
    Using(new BufferedReader(new InputStreamReader(getClass.getClassLoader.getResourceAsStream("pg73831.txt")))):
      _.lines().toScala(List).flatMap[String](split).filterNot(_.isBlank)
    .fold(_ => List.empty[String], identity)

  private def split(line: String): LazyList[String] =
    line
      .replaceAll("[.,:!]\\s", " ")
      .replaceAll("[.,:!]$", " ")
      .replaceAll("--", "")
      .filter(!"[]#*(),".contains(_))
      .toLowerCase(FRANCE)
      .split("""\s+""")
      .to(LazyList)

  def analyze(book: List[String]): Map[String, Vector[String]] =
    book.foldLeft(Empty)(accumulate).tokens

  private enum Accumulator:
    val tokens: Map[String, Vector[String]] = Map.empty
    case Empty
    case OneToken(override val tokens: Map[String, Vector[String]], token: String)
    case TwoTokens(override val tokens: Map[String, Vector[String]], first: String, second: String)

  private def accumulate(accumulator: Accumulator, currentToken: String): Accumulator =
    accumulator match
      case empty @ Empty           => OneToken(empty.tokens, currentToken)
      case OneToken(tokens, first) => TwoTokens(tokens, first, currentToken)
      case TwoTokens(tokens, first, second) =>
        TwoTokens(tokens.updatedWith(s"$first $second")(remap(currentToken)), second, currentToken)

  private def remap(currentToken: String)(nextTokens: Option[Vector[String]]): Option[Vector[String]] =
    nextTokens.map(_.appended(currentToken)).orElse(Some(Vector(currentToken)))
