package name.lemerdy.sebastian

import java.util.UUID

case class Cat(id: UUID, name: String)

object Cat:

  def names(cats: Seq[Cat]): Seq[String] = cats.map(_.name)
