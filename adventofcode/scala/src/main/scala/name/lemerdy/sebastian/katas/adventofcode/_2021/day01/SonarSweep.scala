package name.lemerdy.sebastian.katas.adventofcode._2021.day01

object SonarSweep:

  def depthMeasurementIncreasesCount(depthMeasurements: List[Long]): Long =
    depthMeasurements
      .zip(depthMeasurements.tail)
      .map { case (first, second) => if (second > first) 1 else 0 }
      .sum

  def sumOfDepthMeasurementWindowsIncreasesCount(depthMeasurements: List[Long]): Long =
    depthMeasurementIncreasesCount(
      depthMeasurements
        .zip(depthMeasurements.drop(1))
        .zip(depthMeasurements.drop(2))
        .map { case ((a, b), c) => a + b + c }
    )
