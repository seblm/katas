package name.lemerdy.sebastian.katas.adventofcode._2018.day1

import scala.annotation.tailrec

object ChronalCalibration {

  def resultingFrequency(frequencyChanges: Iterator[String]): BigInt = frequencyChanges.map(BigInt.apply).sum

  def firstFrequencyReachedTwice(frequencyChanges: Iterator[String]): BigInt = {
    val (original, copy) = frequencyChanges.map(BigInt.apply).duplicate
    firstFrequencyReachedTwice(original)(copy.toSeq)
  }

  @tailrec
  def firstFrequencyReachedTwice(frequencyChanges: Iterator[BigInt])(currentFrequencyChanges: Seq[BigInt],
                                                                     sum: BigInt = 0,
                                                                     frequenciesReached: Set[BigInt] = Set.empty): BigInt =
    currentFrequencyChanges match {
      case Nil ⇒ firstFrequencyReachedTwice(frequencyChanges)(frequencyChanges.duplicate._2.toSeq, sum, frequenciesReached)
      case frequencyChange #:: tail ⇒
        val newFrequency = sum + frequencyChange
        if (frequenciesReached.contains(newFrequency))
          newFrequency
        else
          firstFrequencyReachedTwice(frequencyChanges)(tail, newFrequency, frequenciesReached + newFrequency)
    }

}
