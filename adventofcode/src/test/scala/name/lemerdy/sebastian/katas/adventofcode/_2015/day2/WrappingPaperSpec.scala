package name.lemerdy.sebastian.katas.adventofcode._2015.day2

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

class WrappingPaperSpec extends AnyFlatSpec:

  "WrappingPaper" should "compute square feet" in {
    new WrappingPaper().computeDimension(2, 3, 4) shouldBe 58
  }

  it should "compute square feet for another present" in {
    new WrappingPaper().computeDimension(1, 1, 10) shouldBe 43
  }

  it should "compute by parsing a string" in {
    new WrappingPaper().computeDimensions("2x3x4") shouldBe 58
  }

  it should "compute by parsing two strings" in {
    new WrappingPaper().computeDimensions("2x3x4\n1x1x10") shouldBe 101
  }

  it should "compute ribbon" in {
    new WrappingPaper().computeRibbon("2x3x4") shouldBe 34
    new WrappingPaper().computeRibbon("1x1x10") shouldBe 14
  }
