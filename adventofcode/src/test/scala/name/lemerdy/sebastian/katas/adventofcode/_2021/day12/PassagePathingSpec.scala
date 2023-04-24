package name.lemerdy.sebastian.katas.adventofcode._2021.day12

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

import scala.io.Source

class PassagePathingSpec extends AnyFlatSpec:

  private lazy val example = """start-A
                               |start-b
                               |A-c
                               |A-b
                               |b-d
                               |A-end
                               |b-end""".stripMargin.split("\n").toList

  "PassagePathing" should "count paths" in {
    PassagePathing.countPaths(example) should be(10L)
  }

  private lazy val largerExample = """dc-end
                                     |HN-start
                                     |start-kj
                                     |dc-start
                                     |dc-HN
                                     |LN-dc
                                     |HN-end
                                     |kj-sa
                                     |kj-HN
                                     |kj-dc""".stripMargin.split("\n").toList

  it should "count paths with larger example" in {
    PassagePathing.countPaths(largerExample) should be(19L)
  }

  private lazy val evenLargerExample = """fs-end
                                         |he-DX
                                         |fs-he
                                         |start-DX
                                         |pj-DX
                                         |end-zg
                                         |zg-sl
                                         |zg-pj
                                         |pj-he
                                         |RW-he
                                         |fs-DX
                                         |pj-RW
                                         |zg-RW
                                         |start-pj
                                         |he-WI
                                         |zg-he
                                         |pj-fs
                                         |start-RW""".stripMargin.split("\n").toList

  it should "count paths with even larger example" in {
    PassagePathing.countPaths(evenLargerExample) should be(226L)
  }

  private lazy val input: List[String] =
    Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2021/day12/input.txt").getLines().toList

  it should "count paths with real example" in {
    PassagePathing.countPaths(input) should be(4659L)
  }

  it should "count paths with one small cave at most twice" in {
    PassagePathing.countPathsOneSmallCaveTwice(example) should be(36L)
  }

  it should "count paths with one small cave at most twice with larger example" in {
    PassagePathing.countPathsOneSmallCaveTwice(largerExample) should be(103L)
  }

  it should "count paths with one small cave at most twice with even larger example " in {
    PassagePathing.countPathsOneSmallCaveTwice(evenLargerExample) should be(3509L)
  }

  it should "count paths with one small cave at most twice with real example " in {
    PassagePathing.countPathsOneSmallCaveTwice(input) should be(148962L)
  }
