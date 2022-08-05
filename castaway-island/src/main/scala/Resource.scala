sealed trait Resource

object Resource:

  case object Eau extends Resource
  case object Potager extends Resource
  case object Betail extends Resource
  case object Feu extends Resource
  case object Bois extends Resource
  case object Cabane extends Resource

  val all: Vector[Resource] = Vector(Eau, Potager, Betail, Feu, Bois, Cabane)
