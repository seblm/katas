package name.lemerdy.sebastian.katas.adventofcode._2018.day25

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

import scala.io.Source

class FourDimensionalAdventureSpec extends AnyFlatSpec {

  "FourDimensionalAdventure" should "count constellations" in {
    FourDimensionalAdventure.count(""" 0,0,0,0
        | 3,0,0,0
        | 0,3,0,0
        | 0,0,3,0
        | 0,0,0,3
        | 0,0,0,6
        | 9,0,0,0
        |12,0,0,0""".stripMargin) should be(2)
  }

  it should "count constellations second example" in {
    FourDimensionalAdventure.count("""-1,2,2,0
        |0,0,2,-2
        |0,0,0,-2
        |-1,2,0,0
        |-2,-2,-2,2
        |3,0,2,-1
        |-1,3,2,2
        |-1,0,-1,0
        |0,2,1,-2
        |3,0,0,0""".stripMargin) should be(4)
  }

  it should "count constellations third example" in {
    FourDimensionalAdventure.count("""1,-1,0,1
        |2,0,-1,0
        |3,2,-1,0
        |0,0,3,1
        |0,0,-1,-1
        |2,3,-2,0
        |-2,2,0,0
        |2,-2,0,-1
        |1,-1,0,-1
        |3,2,0,2""".stripMargin) should be(3)
  }

  it should "count constellations fourth example" in {
    FourDimensionalAdventure.count("""1,-1,-1,-2
        |-2,-2,0,1
        |0,2,1,3
        |-2,3,-2,1
        |0,2,3,-2
        |-1,-1,1,-2
        |0,-2,-1,0
        |-2,2,3,-1
        |1,2,2,0
        |-1,-2,0,-2""".stripMargin) should be(8)
  }

  it should "count constellations real example" in {
    val setOfCoordinates =
      Source.fromResource("name/lemerdy/sebastian/katas/adventofcode/_2018/day25/dataset.txt").mkString

    FourDimensionalAdventure.count(setOfCoordinates) should be(331)
  }

}
