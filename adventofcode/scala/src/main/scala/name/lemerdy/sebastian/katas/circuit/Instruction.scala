package name.lemerdy.sebastian.katas.circuit

class Instruction(instruction: String, circuit: Circuit) {

  private val instructionMatcher = "^(.+) -> (\\w+)$".r.findAllIn(instruction)
  instructionMatcher.hasNext

  private val signalRegexp = "^(\\d+)$".r
  private val wireRegexp = "^(\\w+)$".r
  private val notSignalRegexp = "^NOT (\\d+)$".r
  private val notWireRegexp = "^NOT (\\w+)$".r
  private val gates = "AND|OR|LSHIFT|RSHIFT"
  private val twoSignalsRegexp = s"^(\\d+) ($gates) (\\d+)$$".r
  private val oneSignalOneWireRegexp = s"^(\\d+) ($gates) (\\w+)$$".r
  private val oneWireOneSignalRegexp = s"^(\\w+) ($gates) (\\d+)$$".r
  private val twoWiresRegexp = s"^(\\w+) ($gates) (\\w+)$$".r

  val wire = Wire(instructionMatcher.group(2))

  val expression = instructionMatcher.group(1) match {
    case signalRegexp(value)      => new SimpleSignal(Signal(value.toInt))
    case wireRegexp(value)        => new WireSignal(Wire(value))
    case notSignalRegexp(signal1) => new NotSignal(Signal(signal1.toInt))
    case notWireRegexp(wire1)     => new NotWire(Wire(wire1))
    case twoSignalsRegexp(left, gate, right) =>
      new TwoSignals(Gate.withName(gate), Signal(left.toInt), Signal(right.toInt))
    case oneSignalOneWireRegexp(left, gate, right) =>
      new OneSignalOneWire(Gate.withName(gate), Signal(left.toInt), Wire(right))
    case oneWireOneSignalRegexp(left, gate, right) =>
      new OneWoreOneSignal(Gate.withName(gate), Wire(left), Signal(right.toInt))
    case twoWiresRegexp(left, gate, right) => new TwoWires(Gate.withName(gate), Wire(left), Wire(right))
    case _                                 => new Error(instruction)
  }

  lazy val signal = expression.run(circuit)

}
