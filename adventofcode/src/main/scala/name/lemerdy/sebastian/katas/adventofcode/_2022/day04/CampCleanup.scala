package name.lemerdy.sebastian.katas.adventofcode._2022.day04

import scala.jdk.StreamConverters.*

object CampCleanup:

  def countFullyContainedAssignments(assignments: String): Long =
    toAssignmentPairs(assignments).count(_.isFullyContained)

  def countOverlappedAssignments(assignments: String): Long =
    toAssignmentPairs(assignments).count(_.isOverlapped)

  private def toAssignmentPairs(assignments: String) =
    assignments.lines().toScala(Iterator).map(AssignmentPair.apply)

  case class SectionAssignment(firstIDNumber: Int, secondIDNumber: Int):
    def toIDNumbers: Vector[Int] = Range.apply(firstIDNumber, secondIDNumber + 1).toVector
  object SectionAssignment:
    def apply(content: String): SectionAssignment =
      val Array(first, second) = content.split('-')
      SectionAssignment(first.toInt, second.toInt)

  case class AssignmentPair(first: SectionAssignment, second: SectionAssignment):
    def isFullyContained: Boolean =
      first.firstIDNumber >= second.firstIDNumber && first.secondIDNumber <= second.secondIDNumber ||
        second.firstIDNumber >= first.firstIDNumber && second.secondIDNumber <= first.secondIDNumber
    def isOverlapped: Boolean = first.toIDNumbers.intersect(second.toIDNumbers).nonEmpty
  private object AssignmentPair:
    def apply(line: String): AssignmentPair =
      val Array(first, second) = line.split(',')
      AssignmentPair(SectionAssignment(first), SectionAssignment(second))
