package name.lemerdy.sebastian.katas.adventofcode._2018.day1

import org.scalatest.{FlatSpec, Matchers}

import scala.io.Source

class ChronalCalibrationSpec extends FlatSpec with Matchers {

  "ChronalCalibration" should "compute the resulting frequency" in {
    val frequencyChanges: Iterator[String] = Iterator("+1", "-2", "+3", "+1")

    val resultingFrequency: BigInt = ChronalCalibration.resultingFrequency(frequencyChanges)

    resultingFrequency should be(BigInt(3))
  }

  it should "compute the resulting frequency for dataset.txt" in {
    val frequencyChanges: Iterator[String] = Source.fromResource(dataset).getLines()

    val resultingFrequency: BigInt = ChronalCalibration.resultingFrequency(frequencyChanges)

    resultingFrequency should be(BigInt(578))
  }

  it should "compute first frequency reached twice" in {
    val frequencyChanges: Iterator[String] = Iterator("+1", "-2", "+3", "+1")

    val resultingFrequency: BigInt = ChronalCalibration.firstFrequencyReachedTwice(frequencyChanges)

    resultingFrequency should be(BigInt(2))
  }

  it should "compute first frequency reached twice for dataset.txt" in {
    val frequencyChanges: Iterator[String] = Source.fromResource(dataset).getLines()

    val resultingFrequency: BigInt = ChronalCalibration.firstFrequencyReachedTwice(frequencyChanges)

    resultingFrequency should be(BigInt(82516))
  }

  private lazy val dataset = "name/lemerdy/sebastian/katas/adventofcode/_2018/day1/dataset.txt"

}
