package name.lemerdy.sebastian.katas.adventofcode._2020.day5

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

import scala.io.Source

class SeatIDDecoderSpec extends AnyFlatSpec {

  lazy val input: String =
    Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2020/day5/input.txt").getLines().mkString("\n")

  "SeatIDDecoder" should "decode seat id 1" in {
    SeatIDDecoder.decode("FBFBBFFRLR") shouldBe 357
  }

  it should "decode seat id 2" in {
    SeatIDDecoder.decode("BFFFBBFRRR") shouldBe 567
  }

  it should "decode seat id 3" in {
    SeatIDDecoder.decode("FFFBBBFRRR") shouldBe 119
  }

  it should "decode seat id 4" in {
    SeatIDDecoder.decode("BBFFBBFRLL") shouldBe 820
  }

  it should "compute highest seat ID on a boarding pass" in {
    SeatIDDecoder.highestSeatID("""FBFBBFFRLR
                                  |BFFFBBFRRR
                                  |FFFBBBFRRR
                                  |BBFFBBFRLL""".stripMargin) shouldBe 820
  }

  it should "decode seat ids from file" in {
    SeatIDDecoder.highestSeatID(input) shouldBe 953
  }

  it should "find my seat ID" in {
    SeatIDDecoder.findMySeatID(input) shouldBe 615
  }

}
