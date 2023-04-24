package name.lemerdy.sebastian.katas.md5

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

class AdventCoinsMiningSpec extends AnyFlatSpec {

  "Santa" should "mine AdventCoins" in {
    new AdventCoinsMining().mine("abcdef") shouldBe 609043
  }

}
