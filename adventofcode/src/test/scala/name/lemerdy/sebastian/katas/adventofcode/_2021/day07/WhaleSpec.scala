package name.lemerdy.sebastian.katas.adventofcode._2021.day07

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

import scala.io.Source

class WhaleSpec extends AnyFlatSpec:

  "Whale" should "align crabs" in {
    val result = Whale.alignCrabs("16,1,2,0,4,2,7,1,2,14")

    result should be(37L)
  }

  private lazy val input: List[String] =
    Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2021/day07/input.txt").getLines().toList

  it should "align crabs with real input" in {
    val result = Whale.alignCrabs(input.head)

    result should be(356958L)
  }

  it should "align crabs more expensive" in {
    val result = Whale.alignCrabsMoreExpensive("16,1,2,0,4,2,7,1,2,14")

    result should be(168L)
  }

  it should "align crabs more expensive with real input" in {
    val result = Whale.alignCrabsMoreExpensive(input.head)

    result should be(105461913L)
  }
