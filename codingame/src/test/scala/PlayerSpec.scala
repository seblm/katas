import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PlayerSpec extends AnyFlatSpec with Matchers {

  "Player" should "play" in {
    Player.main(Array.empty[String])
  }

}
