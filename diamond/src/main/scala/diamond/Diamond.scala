package diamond

import scala.collection.mutable.StringBuilder.newBuilder

case class Diamond(size: Int) {
  def print(): String = {
    val print = newBuilder
    for (y <- size / 2 to 0 by -1) {
      print.append(printLine(y))
    }
    for (y <- 1 to size / 2) {
      print.append(printLine(y))
    }
    print.toString
  }

  private def printLine(y: Int) = {
    val print = newBuilder
    print.append(printExternalSpaces(y))

    print.append("*")

    val centerSpaces = size - 2 * y - 2
    if (centerSpaces > 0) {
      for (x <- 0 until centerSpaces) {
        print.append(" ")
      }
      print.append("*")
    }

    print.append(printExternalSpaces(y))

    print.append("\n")
  }

  private def printExternalSpaces(y: Int) = {
    val print = newBuilder
    for (x <- 0 until y) {
      print.append(" ")
    }
    print.toString
  }
}
