package name.lemerdy.sebastian.katas.adventofcode._2022.day08

import name.lemerdy.sebastian.katas.adventofcode._2022.day08.TreetopTreeHouse.Tree
import org.scalatest.TryValues.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

import scala.io.Source
import scala.util.Using

class TreetopTreeHouseSpec extends AnyFlatSpec:

  "TreetopTreeHouse" should "count visible trees" in {
    TreetopTreeHouse.countVisibleTrees("""30373
                                         |25512
                                         |65332
                                         |33549
                                         |35390""".stripMargin) should be(21)
  }

  private lazy val input =
    Using(Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2022/day08/input.txt"))(
      _.mkString
    ).success.value

  it should "compute with real example" ignore {
    TreetopTreeHouse.countVisibleTrees(input) should be(1805)
  }

  it should "compute scenic score" in {
    TreetopTreeHouse.computeScenicScore(
      """30373
                                          |25512
                                          |65332
                                          |33549
                                          |35390""".stripMargin,
      Tree(1, 2, 5)
    ) should be(4)
  }

  it should "compute scenic score 2" in {
    TreetopTreeHouse.computeScenicScore(
      """30373
                                          |25512
                                          |65332
                                          |33549
                                          |35390""".stripMargin,
      Tree(3, 2, 5)
    ) should be(8)
  }

  it should "compute max scenic score with real example" ignore {
    TreetopTreeHouse.computeMaxScenicScore(input) should be(444_528)
  }
