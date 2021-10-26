import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

class MainSpec extends AnyFlatSpec:

  "msg" should "tell where code was compiled onto" in {
    msg should be("I was compiled by Scala 3. :)")
  }
