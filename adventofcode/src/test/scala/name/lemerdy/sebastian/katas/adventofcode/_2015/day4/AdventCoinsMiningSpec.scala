package name.lemerdy.sebastian.katas.adventofcode._2015.day4

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

class AdventCoinsMiningSpec extends AnyFlatSpec:

  "Santa" should "mine AdventCoins" in {
    new AdventCoinsMining().mine("abcdef") shouldBe 609043
  }
