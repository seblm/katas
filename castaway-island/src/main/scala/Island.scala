import Kind.Desastre

case class Island(resources: Map[Resource, Option[Kind]] = Resource.all.map(resource => resource -> None).toMap)
