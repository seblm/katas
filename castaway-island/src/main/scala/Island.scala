import Kind.Desastre

case class Island(resources: Map[Resource, Vector[Card]] = Resource.all.map(resource => resource -> Vector.empty).toMap)
