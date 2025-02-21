package services

import models._
import org.scalatestplus.play._
import services._

class MowerServiceSpec extends PlaySpec {

  "MowerService" should {

    "move the mower correctly first test" in {
      val lawn = Lawn(6, 6)
      val mower = Mower(Position(1, 2, 'N'), "GAGAGAGAA")

      val finalPosition = MowerService.moveMower(mower, lawn)
      s"${finalPosition.x} ${finalPosition.y} ${finalPosition.orientation}" mustBe "1 3 N"
    }

    "move the mower correctly second test" in {
      val lawn = Lawn(6, 6)
      val mower = Mower(Position(3, 3, 'E'), "AADAADADDA")

      val finalPosition = MowerService.moveMower(mower, lawn)
      s"${finalPosition.x} ${finalPosition.y} ${finalPosition.orientation}" mustBe "5 1 E"
    }
  }
}