package name.lemerdy.sebastian.katas.adventofcode._2024.day03

object MullItOver:

  private val mul = """mul\((\d{1,3}),(\d{1,3})\)(.*)""".r
  private val do_ = """do\(\)(.*)""".r
  private val dont = """don't\(\)(.*)""".r
  private val first = ".(.*)".r

  def multiply(input: String): Long = input match
    case mul(left, right, rest) => left.toLong * right.toLong + multiply(rest)
    case first(rest)            => multiply(rest)
    case ""                     => 0

  def multiplyEnablement(input: String, enabled: Boolean = true): Long = input match
    case mul(left, right, rest) if enabled => left.toLong * right.toLong + multiplyEnablement(rest, enabled)
    case mul(left, right, rest)            => multiplyEnablement(rest, enabled)
    case do_(rest)                         => multiplyEnablement(rest, enabled = true)
    case dont(rest)                        => multiplyEnablement(rest, enabled = false)
    case first(rest)                       => multiplyEnablement(rest, enabled)
    case ""                                => 0
