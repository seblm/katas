package example

import org.scalatest._

class BoggleSpec extends WordSpec with Matchers {

  "Boggle" should {
    "pass first test" in {
      new Boggle(
        """
          |MPLR
          |DSDA
          |HNEO
          |SHTY
        """.stripMargin).boggle("ARDENT") should be(true)
    }
  }
}
