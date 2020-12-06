package name.lemerdy.sebastian.katas.adventofcode._2020

object DeclarationFormsChecker {

  def anyone: String => Int = eachGroupAndSum(countAnyone)

  def everyone: String => Int = eachGroupAndSum(countEveryone)

  private def countAnyone(groupAnswers: String): Int = groupAnswers.distinct.count(_ != '\n')

  private def countEveryone(groupAnswers: String): Int = {
    val personCount = groupAnswers.count(_ == '\n') + 1
    groupAnswers.filter(_ != '\n').groupBy(identity).count(_._2.length == personCount)
  }

  private def eachGroupAndSum(operation: String => Int)(allAnswers: String): Int =
    allAnswers.split("\n\n").map(operation).sum

}
