package insurance.car

import insurance.Cover

case class Policy(car: Car, cover: Cover, driver: Driver):

  def toJson(): String =
    s"""{
       |  "car": {
       |    "marketValue": ${car.marketValue}
       |  },
       |  "cover": {
       |    "fixedCost": ${cover.fixedCost}
       |  },
       |  "driver": {
       |    "numberOfResponsibilityAccidentsDuringLast5Years": ${driver.numberOfResponsibilityAccidentsDuringLast5Years},
       |    "numberOfYearsOfYourContract": ${driver.numberOfYearsOfYourContract},
       |  }
       |}""".stripMargin
