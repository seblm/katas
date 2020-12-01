package name.lemerdy.sebastian.katas.circuit

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

class CircuitSpec extends AnyFlatSpec {

  "signal" should "be provided to wire" in {
    val circuit = new Circuit("" +
      "123 -> x\n" +
      "456 -> y")

    val (x, y) = (circuit.signal(Wire("x")), circuit.signal(Wire("y")))

    x shouldBe Signal(123)
    y shouldBe Signal(456)
  }

  "wire" should "be provided to wire" in {
    val circuit = new Circuit("" +
      "123 -> lx\n" +
      "lx -> a")

    val a = circuit.signal(Wire("a"))

    a shouldBe Signal(123)
  }

  "AND gate" should "provide correct signal to wire" in {
    val circuit = new Circuit("123 AND 456 -> d")

    val d = circuit.signal(Wire("d"))

    d shouldBe Signal(72)
  }

  it should "reference another wire" in {
    val circuit = new Circuit("" +
      "123 -> x\n" +
      "x AND 456 -> d")

    val d = circuit.signal(Wire("d"))

    d shouldBe Signal(72)
  }

  "OR gate" should "provide correct signal to wire" in {
    val circuit = new Circuit("123 OR 456 -> e")

    val e = circuit.signal(Wire("e"))

    e shouldBe Signal(507)
  }

  it should "reference another two other wires" in {
    val circuit = new Circuit("" +
      "123 -> x\n" +
      "456 -> y\n" +
      "x OR y -> e")

    val d = circuit.signal(Wire("e"))

    d shouldBe Signal(507)
  }

  "LSHIFT gate" should "provide correct signal to wire" in {
    val circuit = new Circuit("123 LSHIFT 2 -> f")

    val f = circuit.signal(Wire("f"))

    f shouldBe Signal(492)
  }

  it should "reference another wire" in {
    val circuit = new Circuit("" +
      "123 -> x\n" +
      "x LSHIFT 2 -> f")

    val f = circuit.signal(Wire("f"))

    f shouldBe Signal(492)
  }

  "RSHIFT gate" should "provide correct signal to wire" in {
    val circuit = new Circuit("456 RSHIFT 2 -> g")

    val g = circuit.signal(Wire("g"))

    g shouldBe Signal(114)
  }

  "NOT gate" should "provide correct signal to wire" in {
    val circuit = new Circuit("NOT 123 -> h")

    val g = circuit.signal(Wire("h"))

    g shouldBe Signal(65412)
  }

}
