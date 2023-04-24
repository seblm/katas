package name.lemerdy.sebastian.katas.adventofcode._2022.day06

import org.scalatest.TryValues.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

import scala.io.Source
import scala.util.Using

class TuningTroubleSpec extends AnyFlatSpec:

  "TuningTrouble" should "find start of packet marker" in {
    TuningTrouble.findStartMarker("mjqjpqmgbljsphdztnvjfqwrcgsmlb") should be(7)
  }

  it should "find start of packet marker with other example 1" in {
    TuningTrouble.findStartMarker("bvwbjplbgvbhsrlpgdmjqwftvncz") should be(5)
  }

  it should "find start of packet marker with other example 2" in {
    TuningTrouble.findStartMarker("nppdvjthqldpwncqszvftbrmjlhg") should be(6)
  }

  it should "find start of packet marker with other example 3" in {
    TuningTrouble.findStartMarker("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") should be(10)
  }

  it should "find start of packet marker with other example 4" in {
    TuningTrouble.findStartMarker("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw") should be(11)
  }

  private lazy val input =
    Using(Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2022/day06/input.txt"))(
      _.mkString
    ).success.value

  it should "find start of packet marker with real example" in {
    TuningTrouble.findStartMarker(input) should be(1912)
  }

  it should "find start of message marker" in {
    TuningTrouble.findStartMarker("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 14) should be(19)
  }

  it should "find start of message marker with other example 1" in {
    TuningTrouble.findStartMarker("bvwbjplbgvbhsrlpgdmjqwftvncz", 14) should be(23)
  }

  it should "find start of message marker with other example 2" in {
    TuningTrouble.findStartMarker("nppdvjthqldpwncqszvftbrmjlhg", 14) should be(23)
  }

  it should "find start of message marker with other example 3" in {
    TuningTrouble.findStartMarker("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 14) should be(29)
  }

  it should "find start of message marker with other example 4" in {
    TuningTrouble.findStartMarker("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 14) should be(26)
  }

  it should "find start of message marker with real example" in {
    TuningTrouble.findStartMarker(input, 14) should be(2122)
  }
