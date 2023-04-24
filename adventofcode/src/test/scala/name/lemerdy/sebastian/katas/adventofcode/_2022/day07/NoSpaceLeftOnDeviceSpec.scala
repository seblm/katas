package name.lemerdy.sebastian.katas.adventofcode._2022.day07

import org.scalatest.TryValues.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

import scala.io.Source
import scala.util.Using

class NoSpaceLeftOnDeviceSpec extends AnyFlatSpec:

  "NoSpaceLeftOnDevice" should "compute" in {
    NoSpaceLeftOnDevice.compute("""$ cd /
                                  |$ ls
                                  |dir a
                                  |14848514 b.txt
                                  |8504156 c.dat
                                  |dir d
                                  |$ cd a
                                  |$ ls
                                  |dir e
                                  |29116 f
                                  |2557 g
                                  |62596 h.lst
                                  |$ cd e
                                  |$ ls
                                  |584 i
                                  |$ cd ..
                                  |$ cd ..
                                  |$ cd d
                                  |$ ls
                                  |4060174 j
                                  |8033020 d.log
                                  |5626152 d.ext
                                  |7214296 k""".stripMargin) should be(95437)
  }

  private lazy val input =
    Using(Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2022/day07/input.txt"))(
      _.mkString
    ).success.value

  it should "compute in real world" in {
    NoSpaceLeftOnDevice.compute(input) should be(1_141_028)
  }

  it should "compute smallest directory to remove" in {
    NoSpaceLeftOnDevice.computeSmallest("""$ cd /
                                          |$ ls
                                          |dir a
                                          |14848514 b.txt
                                          |8504156 c.dat
                                          |dir d
                                          |$ cd a
                                          |$ ls
                                          |dir e
                                          |29116 f
                                          |2557 g
                                          |62596 h.lst
                                          |$ cd e
                                          |$ ls
                                          |584 i
                                          |$ cd ..
                                          |$ cd ..
                                          |$ cd d
                                          |$ ls
                                          |4060174 j
                                          |8033020 d.log
                                          |5626152 d.ext
                                          |7214296 k""".stripMargin) should be(24_933_642)
  }

  it should "compute smallest directory to remove in real world" in {
    NoSpaceLeftOnDevice.computeSmallest(input) should be(8_278_005)
  }
