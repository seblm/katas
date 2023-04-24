package name.lemerdy.sebastian.katas.adventofcode._2018.day8

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

import scala.io.Source

class MemoryManeuverSpec extends AnyFlatSpec {

  "MemoryManeuver" should "sum all metadata entries" in {
    MemoryManeuver.sumMetadataEntries("2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2") should be(138)
  }

  it should "without child" in {
    MemoryManeuver.sumMetadataEntries("0 1 99") should be(99)
  }

  it should "without child with more than one metadata" in {
    MemoryManeuver.sumMetadataEntries("0 3 10 11 12") should be(33)
  }

//  it should "without child with two sequences of metadata" in {
//    MemoryManeuver.sumMetadataEntries("0 3 10 11 12 0 3 1 2 3") should be(39)
//  }

  it should "without one child" in {
    MemoryManeuver.sumMetadataEntries("1 1 0 1 99 2") should be(101)
  }

  // 2 3  0 3  10 11 12  1 1  0 1  99  2  1 1 2
  // 2 3  Leaf(10 11 12) 1 1  0 1  99  2  1 1 2
  // 2 3  Leaf(10 11 12) 1 1  Leaf(99) 2  1 1 2
  // 2 3  Leaf(10 11 12) Node(Leaf(99) 2) 1 1 2
  // Node(Leaf(10 11 12) Node(Leaf(99) 2) 1 1 2)

  it should "works with real data" in {
    val dataset = Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2018/day8/dataset.txt").mkString

    MemoryManeuver.sumMetadataEntries(dataset) should be(41521)
  }

}
