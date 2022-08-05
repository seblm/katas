sealed abstract class Kind(val count: Int)

object Kind:

  case object Desastre extends Kind(3)
  case object ResourceKind extends Kind(4)
  case object Riposte extends Kind(5)

  val all: Vector[Kind] = Vector(Desastre, ResourceKind, Riposte)
