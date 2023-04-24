package name.lemerdy.sebastian.katas.adventofcode._2021.day10

import org.scalatest.OptionValues.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

import scala.io.Source

class SyntaxScoringSpec extends AnyFlatSpec:

  "SyntaxScoring" should "find first illegal character " in {
    SyntaxScoring.findFirstIllegalCharacter("{()()()>").value should be('>')
  }

  private lazy val example = """[({(<(())[]>[[{[]{<()<>>
                               |[(()[<>])]({[<{<<[]>>(
                               |{([(<{}[<>[]}>{[]{[(<()>
                               |(((({<>}<{<{<>}{[]{[]{}
                               |[[<[([]))<([[{}[[()]]]
                               |[{[{({}]{}}([{[{{{}}([]
                               |{<[[]]>}<{[{[{[]{()[[[]
                               |[<(<(<(<{}))><([]([]()
                               |<{([([[(<>()){}]>(<<{{
                               |<{([{{}}[<[[[<>{}]]]>[]]""".stripMargin.split("\n").toList

  it should "find first illegal characters with example" in {
    SyntaxScoring.findAllFirstIllegalCharacters(example) should be("})])>")
  }

  it should "find total syntax error score" in {
    SyntaxScoring.totalSyntaxErrorScore(example) should be(26397)
  }

  private lazy val input: List[String] =
    Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2021/day10/input.txt").getLines().toList

  it should "find total syntax error score with real example" in {
    SyntaxScoring.totalSyntaxErrorScore(input) should be(319233)
  }

  it should "complete instructions" in {
    SyntaxScoring.complete("[({(<(())[]>[[{[]{<()<>>".toList) should be("}}]])})]")
  }

  it should "compute completion score" in {
    SyntaxScoring.computeCompletionScore("[({(<(())[]>[[{[]{<()<>>") should be(288957L)
  }

  it should "compute completion scores" in {
    SyntaxScoring.computeCompletionScores(example) should be(288957L)
  }

  it should "compute completion scores with real example" in {
    SyntaxScoring.computeCompletionScores(input) should be(1118976874L)
  }
