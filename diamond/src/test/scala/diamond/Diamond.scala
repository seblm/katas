package diamond

case class Diamond(size: Int) {
  def print(): String = {
    val print = new StringBuilder
    for (y <- size / 2 to 0 by -1) {
      printLine(print, y)
    }
    for (y <- 1 to size / 2) {
      printLine(print, y)
    }
    print.toString
  }

  private def printLine(print: StringBuilder, y: Int) = {
    printExternalSpaces(print, y)

    print.append("*")

    val centerSpaces = size - 2 * y - 2
    if (centerSpaces > 0) {
      for (x <- 0 until centerSpaces) {
        print.append(" ")
      }
      print.append("*")
    }

    printExternalSpaces(print, y)

    print.append("\n")
  }

  private def printExternalSpaces(print: StringBuilder, y: Int) = {
    for (x <- 0 until y) {
      print.append(" ")
    }
  }
}
