import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

import org.scalatest._

import scala.util.Random

class SolutionSpec extends FlatSpec with Matchers {

  "Solution" should "Test de l'exemple" in {
    val in = new ByteArrayInputStream(
      """50
        |3
        |RSLJ97 134 1447409503
        |RSLJ97 185 1447413099
        |RSLJ97 201 1447420298""".stripMargin.getBytes)
    val out = new ByteArrayOutputStream()

    Console.withIn(in) {
      Console.withOut(out) {
        Solution.main(Array.empty)
      }
    }

    out.toString should be(
      """RSLJ97 185""".stripMargin)
  }

  "Solution" should "Pas d'excès de vitessse" in {
    val in = new ByteArrayInputStream(
      """60
        |4
        |RSWJ98 152 1447416000
        |RSWJ98 199 1447419600
        |RSWJ98 247 1447423200
        |RSWJ98 295 1447426800""".stripMargin.getBytes)
    val out = new ByteArrayOutputStream()

    Console.withIn(in) {
      Console.withOut(out) {
        Solution.main(Array.empty)
      }
    }

    out.toString should be(
      """OK""".stripMargin)
  }

  "Solution" should "Plusieurs voitures en excès de vitesse" in {
    val in = new ByteArrayInputStream(
      """90
        |9
        |PAZD54 50 1447413071
        |PAZD54 150 1447416671
        |PAZD54 250 1447420211
        |DJSS87 50 1447408801
        |DJSS87 150 1447417501
        |DJSS87 250 1447421101
        |LSKD97 50 1447417436
        |LSKD97 150 1447424636
        |LSKD97 250 1447431836""".stripMargin.getBytes)
    val out = new ByteArrayOutputStream()

    Console.withIn(in) {
      Console.withOut(out) {
        Solution.main(Array.empty)
      }
    }

    out.toString should be(
      """PAZD54 150
        |PAZD54 250
        |DJSS87 250""".stripMargin)
  }

  "Solution" should "Vitesse limite" in {
    val in = new ByteArrayInputStream(
      """100
        |6
        |SKRD94 75 1447407932
        |SKRD94 175 1447411532
        |SKRD94 275 1447415132
        |ZBZJ42 75 1447418732
        |ZBZJ42 175 1447422333
        |ZBZJ42 275 1447425932""".stripMargin.getBytes)
    val out = new ByteArrayOutputStream()

    Console.withIn(in) {
      Console.withOut(out) {
        Solution.main(Array.empty)
      }
    }

    out.toString should be(
      """ZBZJ42 275""".stripMargin)
  }

  "Solution" should "find bugs" in {
    val maxSpeed = Random.nextInt(91) + 10
    println(maxSpeed)
    val cars = (1 to Random.nextInt(25)).map(_ => (0 to 3).map(_ => (Random.nextInt(26) + 'A'.toInt).toChar).mkString + (Random.nextInt(10) + '0'.toInt).toChar + (Random.nextInt(10) + '0'.toInt).toChar)
    val distances = (0 to Random.nextInt(3) + 1).map(_ => Random.nextInt(1000)+1).sorted
    print(cars.flatMap(car => distances.map(distance => s"$car $distance timestamp")).mkString("\n"))

    val in = new ByteArrayInputStream(
      """100
        |6
        |SKRD94 75 1447407932
        |SKRD94 175 1447411532
        |SKRD94 275 1447415132
        |ZBZJ42 75 1447418732
        |ZBZJ42 175 1447422333
        |ZBZJ42 275 1447425932""".stripMargin.getBytes)
    val out = new ByteArrayOutputStream()

    Console.withIn(in) {
      Console.withOut(out) {
        Solution.main(Array.empty)
      }
    }

    out.toString should be(
      """ZBZJ42 275""".stripMargin)
  }

}
