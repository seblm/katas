import org.scalatest.{FlatSpec, Matchers}

class PlayerSpec extends FlatSpec with Matchers {

  "Player" should "play" in {
    Player.main(Array.empty[String])
  }

}
