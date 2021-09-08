package code4life

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PlayerSpec extends AnyFlatSpec with Matchers:

  "Player" should "play" in
    withClue("please provide a test") {
      // player()
      true should be(true)
    }
