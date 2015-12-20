package name.lemerdy.sebastian.katas.chop

class Chop {

  def chop(value: Int, array: Array[Int]): Int = {
    chop(value, array.toList, 0, array.length - 1)
  }

  private def chop(value: Int, values: List[Int], begin: Int, end: Int): Int = {
    println(s"chop($value, $values, $begin, $end)")
    values match {
      case Nil => -1
      case head :: Nil if head == value => begin
      case head :: Nil => -1
      case _ =>
        val middleIndex = values.size / 2
        val middle = values(middleIndex)
        val halfSize = (end - begin) / 2
        if (value < middle)
          chop(value, values.take(halfSize), begin, end - halfSize)
        else if (value == middle)
          begin + middleIndex
        else
          chop(value, values.drop(halfSize + 1), begin + halfSize, end)
    }
  }

}
