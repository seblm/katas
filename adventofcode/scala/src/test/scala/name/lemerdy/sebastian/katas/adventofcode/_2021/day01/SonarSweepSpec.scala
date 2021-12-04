package name.lemerdy.sebastian.katas.adventofcode._2021.day01

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

import scala.io.Source
import scala.util.Try

class SonarSweepSpec extends AnyFlatSpec:

  "SonarSweep" should "count depth measurement increases" in {
    val depthMeasurementIncreasesCount =
      SonarSweep.depthMeasurementIncreasesCount(List(199, 200, 208, 210, 200, 207, 240, 269, 260, 263))

    depthMeasurementIncreasesCount should be(7)
  }

  private lazy val input: List[Long] =
    Source
      .fromResource("name/lemerdy/sebastian/katas/adventofcode/_2021/day01/input.txt")
      .getLines()
      .flatMap(line => Try(line.toLong).toOption)
      .toList

  it should "count depth measurement increases with real example" in {
    val depthMeasurementIncreaseCount = SonarSweep.depthMeasurementIncreasesCount(input)

    depthMeasurementIncreaseCount should be(1692)
  }

  it should "count sum of depth measurement windows increases" in {
    val depthMeasurementIncreasesCount = SonarSweep.sumOfDepthMeasurementWindowsIncreasesCount(
      List(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)
    )

    depthMeasurementIncreasesCount should be(5)
  }

  it should "count sum of depth measurement windows increases with real example" in {
    val depthMeasurementIncreasesCount = SonarSweep.sumOfDepthMeasurementWindowsIncreasesCount(input)

    depthMeasurementIncreasesCount should be(1724)
  }
