package name.lemerdy.sebastian.datamunging

import java.nio.file.Paths

import org.scalatest.{FlatSpec, Matchers}

class SmallestSpreadSpec extends FlatSpec with Matchers {

  "SmallestSpread" should "compute smallest spread" in {
    SmallestSpread.computeSmallestSpread(Paths.get("src", "main", "resources", "weather.dat").toFile) should be("14")
  }

}
