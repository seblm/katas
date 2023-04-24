package name.lemerdy.sebastian.katas.adventofcode._2021.day06

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

import scala.io.Source

class LanternfishSpec extends AnyFlatSpec:

  "Lanternfish" should "count lanternfishs" in {
    val result = Lanternfish.count("3,4,3,1,2", 80)

    result should be(5934)
  }

  private lazy val input: List[String] =
    Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2021/day06/input.txt").getLines().toList

  it should "count lanternfishs with real example" in {
    val result = Lanternfish.count(input.head, 80)

    result should be(388739)
  }

  it should "count lanternfishs during 256 days" in {
    val result = Lanternfish.smartCount("3,4,3,1,2", 256)

    result should be(26984457539L)
  }

  it should "count lanternfishs during 256 days with real example" in {
    val result = Lanternfish.smartCount(input.head, 256)

    result should be(1741362314973L)
  }
