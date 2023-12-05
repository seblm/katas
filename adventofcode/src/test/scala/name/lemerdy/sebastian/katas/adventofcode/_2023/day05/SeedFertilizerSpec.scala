package name.lemerdy.sebastian.katas.adventofcode._2023.day05

import org.scalatest.TryValues.given
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

import scala.io.Source
import scala.util.Using

class SeedFertilizerSpec extends AnyFlatSpec:

  "SeedFertilizer" should "compute lowest location" in:
    val result = SeedFertilizer.lowestLocation("""seeds: 79 14 55 13
                                                 |
                                                 |seed-to-soil map:
                                                 |50 98 2
                                                 |52 50 48
                                                 |
                                                 |soil-to-fertilizer map:
                                                 |0 15 37
                                                 |37 52 2
                                                 |39 0 15
                                                 |
                                                 |fertilizer-to-water map:
                                                 |49 53 8
                                                 |0 11 42
                                                 |42 0 7
                                                 |57 7 4
                                                 |
                                                 |water-to-light map:
                                                 |88 18 7
                                                 |18 25 70
                                                 |
                                                 |light-to-temperature map:
                                                 |45 77 23
                                                 |81 45 19
                                                 |68 64 13
                                                 |
                                                 |temperature-to-humidity map:
                                                 |0 69 1
                                                 |1 0 69
                                                 |
                                                 |humidity-to-location map:
                                                 |60 56 37
                                                 |56 93 4""".stripMargin)

    result should be(35)

  private lazy val input =
    Using(Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2023/day05/input.txt"))(
      _.mkString
    ).success.value

  it should "compute lowest location with real example" in:
    val result = SeedFertilizer.lowestLocation(input)

    result should be(3_374_647)
