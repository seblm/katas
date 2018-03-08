package name.lemerdy.sebastian

import scala.collection.mutable

class Dependencies(elems: mutable.ListBuffer[Elem] = mutable.ListBuffer.empty[Elem]) {

  def addDirect(elem: Char, direct: Char*): Dependencies = {
    val (elem1, i) = elems.zipWithIndex.find(_._1.id == elem).getOrElse((Elem(elem), -1))
    val direct1 = direct.map(c => elems.find(_.id == c).getOrElse(Elem(c)))
    val newElem1 = Elem(elem, (elem1.dependencies ++ direct1).distinct)
    i match {
      case -1 => elems.append(newElem1)
      case _ => elems.update(i, newElem1)
    }
    this
  }

  def dependenciesFor(elem: Char): Seq[Char] = {
    println(elems)
    elems.find(_.id == elem).map(_.dependencies.map(_.id)).getOrElse(Seq.empty[Char])
  }

}

case class Elem(id: Char, dependencies: List[Elem] = List.empty[Elem])
