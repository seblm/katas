package insurance.car

import insurance.Cover.{Legal, Provident, Serenity}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*

import java.time.Year

class PremiumCalculatorSpec extends AnyFlatSpec:

  "PremiumCalculator" should "compute Legal premium" in {
    PremiumCalculator.computePremium(Policy(Car(0), Legal, Driver(0, 0))) should be(10.0)
  }

  it should "compute Provident premium" in {
    PremiumCalculator.computePremium(Policy(Car(0), Provident, Driver(0, 0))) should be(20.0)
  }

  it should "compute Serenity premium" in {
    PremiumCalculator.computePremium(Policy(Car(0), Serenity, Driver(0, 0))) should be(30.0)
  }

  it should "compute premium with a low price car" in {
    PremiumCalculator.computePremium(Policy(Car(1_000), Legal, Driver(0, 0))) should be(11.0)
  }

  it should "compute premium with a medium price car" in {
    PremiumCalculator.computePremium(Policy(Car(10_000), Legal, Driver(0, 0))) should be(20.0)
  }

  it should "compute premium with a high price car" in {
    PremiumCalculator.computePremium(Policy(Car(35_000), Legal, Driver(0, 0))) should be(20.0)
  }

  it should "compute premium with a bonus" in {
    PremiumCalculator.computePremium(Policy(Car(0), Legal, Driver(0, 2))) should be(8.0)
  }

  it should "compute premium with max bonus" in {
    PremiumCalculator.computePremium(Policy(Car(0), Legal, Driver(0, 5))) should be(5.0)
  }

  it should "compute premium with bouned bonus" in {
    PremiumCalculator.computePremium(Policy(Car(0), Legal, Driver(0, 6))) should be(5.0)
  }

  it should "compute premium with a penalty" in {
    PremiumCalculator.computePremium(Policy(Car(0), Legal, Driver(1, 0))) should be(12.0)
  }

  it should "compute premium with a greater penalty" in {
    PremiumCalculator.computePremium(Policy(Car(0), Legal, Driver(2, 0))) should be(18.0)
  }

  it should "compute premium with a more greater penalty" in {
    PremiumCalculator.computePremium(Policy(Car(0), Legal, Driver(3, 0))) should be(28.0)
  }

  it should "compute premium with a high penalty" in {
    PremiumCalculator.computePremium(Policy(Car(0), Legal, Driver(7, 0))) should be(108.0)
  }
