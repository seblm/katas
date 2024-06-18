package mowitnow

import cats.syntax.traverse.given

import scala.Function.const
import scala.collection.mutable
import scala.util.matching.Regex

class MowerControl(val input: String, val participants: Map[String, MowerContract]):

  private val initialGarden = parseGarden(input)
  private val gardens = initialGarden.fold(
    error =>
      Console.err.println(s"Invalid input: $error")
      participants.view.mapValues(_ => Garden(0, 0, List.empty)).toMap.to(mutable.Map)
    ,
    garden => participants.view.mapValues(_ => garden).toMap.to(mutable.Map)
  )
  private var index = -1;

  def next(): Map[String, Either[String, Seq[Position]]] =
    index += 1
    val result = mutable.Map[String, Either[String, Seq[Position]]]()
    gardens
      .mapValuesInPlace: (participant, garden) =>
        val nextInstruction = Garden.toString(Garden.take(garden, index))
        val newPositions = parseFinalPositions(participants(participant).computeFinalPositions(nextInstruction))
        result.update(participant, newPositions)
        newPositions.fold(
          error =>
            Console.err.println(s"""Invalid output for "$participant": $error""")
            garden
          ,
          newPositions =>
            garden.copy(mowers =
              garden.mowers
                .zip(newPositions)
                .map: (mower, newPosition) =>
                  mower.copy(initialPosition = newPosition)
            )
        )
    result.toMap

  def parseGarden(input: String): Either[String, Garden] =
    val dimensions: Regex = """(\d+) (\d+)""".r
    val lines = input.split("\n").toList
    for
      (topX, topY) <- lines.head match
        case dimensions(topX, topY) => Right((topX.toInt, topY.toInt))
        case _                      => Left("Invalid dimensions")
      mowers <- lines.tail
        .grouped(2)
        .map: twoLines =>
          for
            firstLine <- twoLines.headOption.toRight("position is empty")
            secondLine = twoLines.applyOrElse(1, const(""))
            mower <- parseMower(firstLine, secondLine)
          yield mower
        .toList
        .sequence
    yield Garden(topX, topY, mowers)

  private def parseMower(position: String, inputInstructions: String): Either[String, Mower] =
    for
      initialPosition <- Position.fromString(position)
      instructions <- inputInstructions.map(Instruction.fromChar).toList.sequence
    yield Mower(initialPosition, instructions)

  private def parseFinalPositions(output: String): Either[String, Seq[Position]] =
    output.split("\n").toList.map(Position.fromString).sequence
