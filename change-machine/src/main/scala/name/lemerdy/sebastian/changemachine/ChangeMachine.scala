package name.lemerdy.sebastian.changemachine

import scala.annotation.tailrec

object ChangeMachine {

  def change(currencySpec: String)(amount: Int): String = {
    val noteTypesArray = currencySpec.split(" ").map(Integer.parseInt)

    @tailrec def reduce(currentAmount: Int,
                        alreadyChanged: Map[Int, Int]): Map[Int, Int] = {
      if (currentAmount == 0) {
        alreadyChanged
      } else {
        val selectedNoteType = noteTypesArray.filter(_ <= currentAmount).last
        reduce(
          currentAmount - selectedNoteType,
          alreadyChanged.updated(
            selectedNoteType,
            alreadyChanged.getOrElse(selectedNoteType, 0) + 1
          )
        )
      }
    }

    val result = reduce(amount, Map.empty)

    result
      .map {
        case (noteType, number) =>
          s"${number}x$noteType"
      }
      .toSeq
      .reverse
      .mkString(" ")
  }

}
