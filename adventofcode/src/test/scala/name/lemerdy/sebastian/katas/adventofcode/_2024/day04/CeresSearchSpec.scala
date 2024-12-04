package name.lemerdy.sebastian.katas.adventofcode._2024.day04

import org.scalatest.TryValues.given
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

import scala.io.Source
import scala.util.Using

class CeresSearchSpec extends AnyFlatSpec:

  it should "countXMAS" in:
    val result = CeresSearch.countXMAS("""MMMSXXMASM
                                         |MSAMXMSMSA
                                         |AMXSXMAAMM
                                         |MSAMASMSMX
                                         |XMASAMXAMM
                                         |XXAMMXXAMA
                                         |SMSMSASXSS
                                         |SAXAMASAAA
                                         |MAMMMXMMMM
                                         |MXMXAXMASX""".stripMargin)

    result should be(18)

  private lazy val input =
    Using(Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2024/day04/input.txt"))(
      _.mkString
    ).success.value

  it should "countXMAS with real example" in:
    val result = CeresSearch.countXMAS(input)

    result should be(2_618)

  it should "countX-MAS" in:
    val result = CeresSearch.countX_MAS("""MMMSXXMASM
                                          |MSAMXMSMSA
                                          |AMXSXMAAMM
                                          |MSAMASMSMX
                                          |XMASAMXAMM
                                          |XXAMMXXAMA
                                          |SMSMSASXSS
                                          |SAXAMASAAA
                                          |MAMMMXMMMM
                                          |MXMXAXMASX""".stripMargin)

    result should be(9)

  it should "countX-MAS with real example" in:
    val result = CeresSearch.countX_MAS(input)

    result should be(2_011)
