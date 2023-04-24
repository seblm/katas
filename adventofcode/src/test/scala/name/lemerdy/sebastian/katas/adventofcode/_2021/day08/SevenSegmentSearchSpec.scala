package name.lemerdy.sebastian.katas.adventofcode._2021.day08

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

import scala.io.Source

class SevenSegmentSearchSpec extends AnyFlatSpec:

  private lazy val firstExample = "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf"

  "SevenSegmentSearch" should "count 1, 4, 7 and 8" in {
    val result = SevenSegmentSearch.count(firstExample)

    result should be(0)
  }

  private lazy val secondExample =
    """be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe
      >edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc
      >fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg
      >fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb
      >aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea
      >fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb
      >dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe
      >bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef
      >egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb
      >gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce"""
      .stripMargin('>')
      .split("\n")
      .toList

  it should "count 1, 4, 7 and 8 several times" in {
    val result = SevenSegmentSearch.count(secondExample)

    result should be(26)
  }

  private lazy val input: List[String] =
    Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2021/day08/input.txt").getLines().toList

  it should "count 1, 4, 7 and 8 with real example" in {
    val result = SevenSegmentSearch.count(input)

    result should be(245)
  }

  it should "compute configuration" in {
    val configuration = SevenSegmentSearch.computeConfiguration(firstExample)

    configuration should be(Map('d' -> 'a', 'e' -> 'b', 'a' -> 'c', 'f' -> 'd', 'g' -> 'e', 'b' -> 'f', 'c' -> 'g'))
  }

  it should "decode one line" in {
    val output = SevenSegmentSearch.decode(firstExample)

    output should be(5353)
  }

  it should "decode another line" in {
    val output = SevenSegmentSearch.decode(
      "be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe"
    )

    output should be(8394)
  }

  it should "decode all lines" in {
    val output = SevenSegmentSearch.decode(secondExample)

    output should be(61229)
  }

  it should "decode all lines with real example" in {
    val output = SevenSegmentSearch.decode(input)

    output should be(983026)
  }
