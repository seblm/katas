package name.lemerdy.sebastian.katas.md5

import org.scalatest.{FlatSpec, Matchers}

class AdventCoinsMiningSpec extends FlatSpec with Matchers {

  "Santa" should "mine AdventCoins" in {
    new AdventCoinsMining().mine("abcdef") shouldBe 609043
  }

}
