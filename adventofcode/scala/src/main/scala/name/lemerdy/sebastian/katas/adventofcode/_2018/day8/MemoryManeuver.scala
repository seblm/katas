package name.lemerdy.sebastian.katas.adventofcode._2018.day8

object MemoryManeuver {

  def sumMetadataEntries(treeDescription: String): Long =
    sumMetadataEntries(treeDescription.split(" ").map(_.toInt).toList)._1

  def sumMetadataEntries(treeDescription: List[Int]): (Long, List[Int]) = treeDescription match {
    case numberOfChild :: numberOfMetadata :: rest =>
      val (sumChildren, restChildren) = consumeChildren(numberOfChild, rest)
      val (sum, restMetadata) = consumeMetadata(numberOfMetadata, restChildren)
      (sumChildren + sum, restMetadata)
    case _ => (-1, List.empty)
  }

  def consumeChildren(numberOfChildren: Int, tree: List[Int]): (Long, List[Int]) = numberOfChildren match {
    case 0 => (0, tree)
    case _ =>
      val (sum1, rest1) = sumMetadataEntries(tree)
      val (sum, rest) = consumeChildren(numberOfChildren - 1, rest1)
      (sum1 + sum, rest)
  }

  def consumeMetadata(num: Long, tree: List[Int]): (Long, List[Int]) = (num, tree) match {
    case (0, _) => (0, tree)
    case (_, metadata :: rest) =>
      val (sum, rest1) = consumeMetadata(num - 1, rest)
      (metadata + sum, rest1)
    case _ => (-1, List.empty)
  }

}
