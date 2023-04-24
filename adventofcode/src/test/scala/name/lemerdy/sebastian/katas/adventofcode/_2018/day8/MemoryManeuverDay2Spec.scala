package name.lemerdy.sebastian.katas.adventofcode._2018.day8

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

import scala.io.Source

class MemoryManeuverDay2Spec extends AnyFlatSpec {

  "MemoryManeuver" should "sum all metadata" ignore {
    MemoryManeuverDay2.sumMetadataEntries("2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2") should be(66)
  }

  it should "works with real data" ignore {
    val dataset = Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2018/day8/dataset.txt").mkString

    MemoryManeuverDay2.sumMetadataEntries(dataset) should be(41521)
  }

}
