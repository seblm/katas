package name.lemerdy.sebastian.changemachine

import scala.annotation.tailrec

object ChangeMachine:

  def change(currencySpec: String)(amount: Int): String =
    val noteTypesArray = currencySpec.split(" ").map(_.toInt)

    @tailrec def reduce(currentAmount: Int, alreadyChanged: Map[Int, Int]): Map[Int, Int] =
      if currentAmount == 0 then alreadyChanged
      else
        val selectedNoteType = noteTypesArray.filter(_ <= currentAmount).last
        reduce(
          currentAmount - selectedNoteType,
          alreadyChanged.updated(selectedNoteType, alreadyChanged.getOrElse(selectedNoteType, 0) + 1)
        )

    val amountByNoteTypes = reduce(amount, Map.empty)

    amountByNoteTypes.toSeq
      .sortBy(_._1)
      .reverse
      .map:
        case (noteType, number) =>
          s"${number}x$noteType"
      .mkString(" ")
