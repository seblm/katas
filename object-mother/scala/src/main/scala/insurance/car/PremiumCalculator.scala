package insurance.car

import scala.math.{min, pow}
import insurance.Cover

object PremiumCalculator:

  /** The amount of money an insurance company charges in return for providing coverage.
    */
  def computePremium(policy: Policy): Double =
    val premium = policy.cover.fixedCost + min(policy.car.marketValue, 10_000) / 1_000.0
    val penalty = pow(policy.driver.numberOfResponsibilityAccidentsDuringLast5Years, 2.0) / 5.0
    val bonus = if (penalty == 0) -min(policy.driver.numberOfYearsOfYourContract, 5) / 10.0 else 0
    premium + premium * penalty + premium * bonus
