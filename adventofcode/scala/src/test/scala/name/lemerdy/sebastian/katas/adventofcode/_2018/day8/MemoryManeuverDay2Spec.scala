package name.lemerdy.sebastian.katas.adventofcode._2018.day8

import org.scalatest.{FlatSpec, Matchers}

import scala.io.Source

class MemoryManeuverDay2Spec extends FlatSpec with Matchers {

  "MemoryManeuver" should "sum all metadata" in {
    MemoryManeuverDay2.sumMetadataEntries("2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2") should be(66)
  }

  it should "works with real data" ignore {
    val dataset = Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2018/day8/dataset.txt").mkString

    MemoryManeuverDay2.sumMetadataEntries(dataset) should be(41521)
  }

}
