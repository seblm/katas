package name.lemerdy.sebastian.katas.adventofcode._2016.day1

class NoTimeForTaxiCab(path: String) {

  private val leftMovementDetector = "L(\\d+)".r
  private val rightMovementDetector = "R(\\d+)".r

  def distance(): Int = distance("N", 0, 0, path.split(",").map(movement => movement.trim()).toList)

  private def distance(direction: String, x: Int, y: Int, moves: List[String]): Int = moves match {
    case Nil => x.abs + y.abs
    case leftMovementDetector(blocks) :: tail =>
      val newDirection = left(direction)
      distance(newDirection, newX(newDirection, blocks.toInt, x), newY(newDirection, blocks.toInt, y), tail)
    case rightMovementDetector(blocks) :: tail =>
      val newDirection = right(direction)
      distance(newDirection, newX(newDirection, blocks.toInt, x), newY(newDirection, blocks.toInt, y), tail)
  }

  private def left(direction: String) = direction match {
    case "N" => "W"
    case "E" => "N"
    case "W" => "S"
    case "S" => "E"
  }

  private def right(direction: String) = direction match {
    case "N" => "E"
    case "E" => "S"
    case "W" => "N"
    case "S" => "W"
  }

  private def newX(direction: String, blocks: Int, x: Int) = direction match {
    case "E" => x + blocks
    case "W" => x - blocks
    case _ => x
  }

  private def newY(direction: String, blocks: Int, y: Int) = direction match {
    case "N" => y + blocks
    case "S" => y - blocks
    case _ => y
  }

}

object NoTimeForTaxiCab {
  def main(args: Array[String]): Unit = {
    println(new NoTimeForTaxiCab("" +
      "L1, L3, L5, L3, R1, L4, L5, R1, R3, L5, R1, L3, L2, L3, R2, R2, L3, L3, R1, L2, R1, L3, L2, R4, R2, L5, R4," +
      "L5, R4, L2, R3, L2, R4, R1, L5, L4, R1, L2, R3, R1, R2, L4, R1, L2, R3, L2, L3, R5, L192, R4, L5, R4, L1, R4," +
      "L4, R2, L5, R45, L2, L5, R4, R5, L3, R5, R77, R2, R5, L5, R1, R4, L4, L4, R2, L4, L1, R191, R1, L1, L2, L2," +
      "L4, L3, R1, L3, R1, R5, R3, L1, L4, L2, L3, L1, L1, R5, L4, R1, L3, R1, L2, R1, R4, R5, L4, L2, R4, R5, L1," +
      "L2, R3, L4, R2, R2, R3, L2, L3, L5, R3, R1, L4, L3, R4, R2, R2, R2, R1, L4, R4, R1, R2, R1, L2, L2, R4, L1," +
      "L2, R3, L3, L5, L4, R4, L3, L1, L5, L3, L5, R5, L5, L4, L2, R1, L2, L4, L2, L4, L1, R4, R4, R5, R1, L4, R2," +
      "L4, L2, L4, R2, L4, L1, L2, R1, R4, R3, R2, R2, R5, L1, L2"
    ).distance())
  }
}
