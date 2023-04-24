package name.lemerdy.sebastian.katas.adventofcode._2016.day1

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class NoTimeForTaxiCabSpec extends AnyFlatSpec with Matchers {

  "no time for taxi cab" should "compute simple distance" in {
    new NoTimeForTaxiCab("R2, L3").distance() shouldBe 5
  }

  it should "compute square distance" in {
    new NoTimeForTaxiCab("R2, R2, R2").distance() shouldBe 2
  }

  it should "compute far distance" in {
    new NoTimeForTaxiCab("R5, L5, R5, R3").distance() shouldBe 12
  }

}
