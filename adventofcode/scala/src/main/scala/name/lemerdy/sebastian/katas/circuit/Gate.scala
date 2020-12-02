package name.lemerdy.sebastian.katas.circuit

object Gate extends Enumeration {

  type Gate = Value

  val AND, OR, LSHIFT, RSHIFT = Value

  def run(gate: Gate, left: Signal, right: Signal): Signal = gate match {
    case AND    => new Signal(left.value & right.value)
    case OR     => new Signal(left.value | right.value)
    case LSHIFT => new Signal(left.value << right.value)
    case RSHIFT => new Signal(left.value >> right.value)
  }

}
