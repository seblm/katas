package mowitnow

import scala.collection.mutable

class RecordedMowerContract(finalPositions: Seq[String] = Seq.empty) extends MowerContract:

  private val inputs = new mutable.ListBuffer[String]
  private var currentFinalPositions = -1

  override def computeFinalPositions(input: String): String =
    inputs.append(input)
    currentFinalPositions += 1
    if finalPositions.isEmpty then "" else finalPositions(currentFinalPositions % finalPositions.length)

  def getInputs: List[String] = inputs.toList
