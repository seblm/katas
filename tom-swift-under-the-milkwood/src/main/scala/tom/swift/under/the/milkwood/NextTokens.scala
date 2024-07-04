package tom.swift.under.the.milkwood

object NextTokens:
  import tom.swift.under.the.milkwood.NextTokens.Accumulator.{OneToken, TwoTokens, Empty}

  def analyze(book: String): Map[String, Vector[String]] =
    val tokens = book.split("""\s+""")
    tokens.foldLeft(Empty)(accumulate).tokens

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
