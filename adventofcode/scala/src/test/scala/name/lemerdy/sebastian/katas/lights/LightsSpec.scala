package name.lemerdy.sebastian.katas.lights

import org.scalatest.{FlatSpec, Matchers}

class LightsSpec extends FlatSpec with Matchers {

  "lights" should "be all turn on" in {
    new Lights()
      .turnOn(0, 0, 999, 999)
      .count() shouldBe 1000000
  }

  it should "be toggled" in {
    new Lights()
      .turnOn(0, 0, 999, 999)
      .toggle(0, 0, 999, 0)
      .count() shouldBe 1000000 - 1000
  }

  it should "be turned off" in {
    new Lights()
      .turnOn(0, 0, 999, 999)
      .turnOff(499, 499, 500, 500)
      .count() shouldBe 1000000 - 4
  }

  it should "parse input" in {
    new Lights()
      .input("turn on 0,0 through 999,999")
      .input("toggle 0,0 through 999,0")
      .input("turn off 499,499 through 500,500")
      .count() shouldBe 1000000 - 1000 - 4
  }
  
  "brightness lights" should "increase brigthness by 1" in {
    new Lights()
      .input("turn on 0,0 through 0,0")
      .totalBrightness() shouldBe 1
  }

}
