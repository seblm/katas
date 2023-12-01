package name.lemerdy.sebastian.katas.adventofcode._2023.day01

import org.scalatest.TryValues.given
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

import scala.io.Source
import scala.util.Using

class TrebuchetSpec extends AnyFlatSpec:

  "Trebuchet" should "get calibration value" in:
    val calibrationDocument = """1abc2
                                |pqr3stu8vwx
                                |a1b2c3d4e5f
                                |treb7uchet""".stripMargin

    val calibration = Trebuchet.calibrate(calibrationDocument)

    calibration should be(142)

  private lazy val input =
    Using(Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2023/day01/input.txt"))(
      _.mkString
    ).success.value

  it should "get calibration value with real example" in:
    Trebuchet.calibrate(input) should be(55_386)

  it should "get calibration value with digit as letters" in:
    val calibrationDocument = """two1nine
                                |eightwothree
                                |abcone2threexyz
                                |xtwone3four
                                |4nineeightseven2
                                |zoneight234
                                |7pqrstsixteen""".stripMargin
    val calibration = Trebuchet.calibrateWithDigitAsLetters(calibrationDocument)

    calibration should be(281)

  it should "get calibration value with digit as letters with real example" in:
    Trebuchet.calibrateWithDigitAsLetters(input) should be(54_824)
