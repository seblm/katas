package mowitnow

import cats.syntax.traverse.given

import scala.collection.mutable
import scala.util.matching.Regex

class MowerControl(val input: String, val participants: Map[String, MowerContract]):

  private val initialGarden = parseGarden(input)
  private val gardens = initialGarden.fold(
    _ => participants.view.mapValues(_ => Garden(0, 0, List.empty)).to(mutable.Map),
    garden => participants.view.mapValues(_ => garden).toMap.to(mutable.Map)
  )

  def next(): Map[String, Either[String, Seq[Position]]] =
    val result = mutable.Map[String, Either[String, Seq[Position]]]()
    gardens
      .mapValuesInPlace: (participant, garden) =>
        val onlyFirstInstruction = garden.copy(mowers = garden.mowers.map: mower =>
          mower.copy(instructions = mower.instructions.headOption.toList))
        val mowerContract = participants(participant)
        val newPositions = mowerContract
          .computeFinalPositions(Garden.toString(onlyFirstInstruction))
          .split("\n")
          .toList
          .map(Position.fromString)
          .sequence
        result.update(participant, newPositions)
        val newGarden = newPositions.fold(
          _ => garden,
          newPositions =>
            garden.copy(mowers =
              garden.mowers
                .zip(newPositions)
                .map: (mower, newPosition) =>
                  mower.copy(initialPosition = newPosition)
            )
        )
        newGarden.copy(mowers = newGarden.mowers.map(mower => mower.copy(instructions = mower.instructions.drop(1))))
    result.toMap

  def parseGarden(input: String): Either[String, Garden] =
    val dimensions: Regex = """(\d+) (\d+)""".r
    val lines = input.split("\n").toList
    for
      (topX, topY) <- lines.head match
        case dimensions(topX, topY) => Right((topX.toInt, topY.toInt))
        case _                      => Left("Invalid dimensions")
      mowers <- lines.tail.grouped(2).map(twoLines => parseMower(twoLines.head, twoLines.last)).toList.sequence
    yield Garden(topX, topY, mowers)

  private def parseMower(position: String, inputInstructions: String): Either[String, Mower] =
    for
      initialPosition <- Position.fromString(position)
      instructions <- inputInstructions.map(Instruction.fromChar).toList.sequence
    yield Mower(initialPosition, instructions)
