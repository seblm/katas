package name.lemerdy.sebastian.katas.adventofcode._2018.day8

object MemoryManeuverDay2 {

  def sumMetadataEntries(treeDescription: String): Int =
    sumMetadataEntries(treeDescription.split(" ").map(_.toInt).toList)._1

  def sumMetadataEntries(treeDescription: List[Int]): (Int, List[Int]) = treeDescription match {
    case numberOfChild :: numberOfMetadata :: rest ⇒
      val (children, restChildren) = consumeChildren(numberOfChild, rest)
      val (sum, restMetadata) = consumeMetadata(children)(numberOfMetadata, restChildren)
      (sum, restMetadata)
  }

  def consumeChildren(numberOfChildren: Int, tree: List[Int]): (List[Int], List[Int]) = {
    println(s"consumeChildren($numberOfChildren, $tree)")
    numberOfChildren match {
      case 0 ⇒ (Nil, tree)
      case _ ⇒
        val (sum1, rest1) = sumMetadataEntries(tree)
        val (children, rest) = consumeChildren(numberOfChildren - 1, rest1)
        (sum1 :: children, rest)
    }
  }

  def consumeMetadata(children: List[Int])(num: Int, tree: List[Int]): (Int, List[Int]) = {
    println(s"consumeMetadata($children)($num, $tree)")
    (num, tree) match {
      case (0, _) ⇒ (0, tree)
      case (_, metadataIndex :: rest) ⇒
        val (sum, rest1) = consumeMetadata(children)(num - 1, rest)
        (children.lift(metadataIndex - 1).getOrElse(0) + sum, rest1)
    }
  }

}
