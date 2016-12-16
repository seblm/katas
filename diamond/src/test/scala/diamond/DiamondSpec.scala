package diamond

import org.scalatest.{FlatSpec, Matchers}

class DiamondSpec extends FlatSpec with Matchers {

  "Diamond" should "print" in {
    Diamond(5).print() shouldBe "" +
      "  *  \n" +
      " * * \n" +
      "*   *\n" +
      " * * \n" +
      "  *  \n"
  }

}
