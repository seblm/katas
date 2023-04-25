package name.lemerdy.sebastian.katas.adventofcode._2015.day7

import name.lemerdy.sebastian.katas.adventofcode._2015.day7.Gate.Gate

trait Expression {
  def run(circuit: Circuit): Signal
}

class SimpleSignal(signal: Signal) extends Expression {
  override def run(circuit: Circuit): Signal = signal
}

class WireSignal(wire: Wire) extends Expression {
  override def run(circuit: Circuit): Signal = circuit.signal(wire)
}

class TwoSignals(gate: Gate, left: Signal, right: Signal) extends Expression {
  override def run(circuit: Circuit): Signal = Gate.run(gate, left, right)
}

class OneSignalOneWire(gate: Gate, left: Signal, right: Wire) extends Expression {
  override def run(circuit: Circuit): Signal = Gate.run(gate, left, circuit.signal(right))
}

class OneWoreOneSignal(gate: Gate, left: Wire, right: Signal) extends Expression {
  override def run(circuit: Circuit): Signal = Gate.run(gate, circuit.signal(left), right)
}

class TwoWires(gate: Gate, left: Wire, right: Wire) extends Expression {
  override def run(circuit: Circuit): Signal = Gate.run(gate, circuit.signal(left), circuit.signal(right))
}

class NotWire(wire: Wire) extends Expression {
  override def run(circuit: Circuit): Signal = Signal(65535 - circuit.signal(wire).value)
}

class NotSignal(signal: Signal) extends Expression {
  override def run(circuit: Circuit): Signal = Signal(65535 - signal.value)
}

class Error(cause: String) extends Expression {
  override def run(circuit: Circuit): Signal = throw new IllegalArgumentException(cause)
}
