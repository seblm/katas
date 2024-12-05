package name.lemerdy.sebastian.katas.adventofcode._2024.day05

object PrintQueue:

  private case class Ordering(before: Int, after: Int)
  private case class PrintQueueData(pageOrderingRules: Seq[Ordering] = Seq.empty, queues: Seq[Seq[Int]] = Seq.empty)
  private case class ParsedResult(
      printQueueData: PrintQueueData,
      aftersByBefore: Map[Int, Seq[Int]],
      beforesByAfter: Map[Int, Seq[Int]]
  )

  private val pageOrdering = """(\d{2})\|(\d{2})""".r

  private def parse(input: String): ParsedResult =
    val data = input.linesIterator.foldLeft(PrintQueueData()): (data, line) =>
      line match
        case pageOrdering(before, after) =>
          data.copy(pageOrderingRules = data.pageOrderingRules :+ Ordering(before.toInt, after.toInt))
        case ""    => data
        case queue => data.copy(queues = data.queues :+ queue.split(",").map(_.toInt))
    val aftersByBefore = data.pageOrderingRules
      .groupBy(_.before)
      .map:
        case (before, afters) =>
          before -> afters.map(_.after)
    val beforesByAfter = data.pageOrderingRules
      .groupBy(_.after)
      .map:
        case (after, befores) =>
          after -> befores.map(_.before)
    ParsedResult(data, aftersByBefore, beforesByAfter)

  def checkOrder(input: String): Long =
    val result = parse(input)
    result.printQueueData.queues
      .filter(checkOrder(result.aftersByBefore, result.beforesByAfter))
      .map(getMiddlePageNumber)
      .sum

  private def getMiddlePageNumber(pageNumbers: Seq[Int]): Int =
    pageNumbers.zipWithIndex.find(_._2 == pageNumbers.length / 2).map(_._1).getOrElse(0)

  private def checkOrder(aftersByBefore: Map[Int, Seq[Int]], beforesByAfter: Map[Int, Seq[Int]])(
      queue: Seq[Int]
  ): Boolean =
    queue.tails
      .map(_.toList)
      .map:
        case before :: afters =>
          afters
            .map(after => checkOrder(aftersByBefore, before, after) && checkOrder(beforesByAfter, after, before))
            .forall(identity)
        case other => true
      .forall(identity)

  private def checkOrder(graph: Map[Int, Seq[Int]], key: Int, value: Int): Boolean =
    graph.get(key).forall(afters => afters.contains(value))

  def fixOrder(input: String): Long =
    val result = parse(input)
    result.printQueueData.queues
      .filterNot(checkOrder(result.aftersByBefore, result.beforesByAfter))
      .map(fixOrder(result.beforesByAfter))
      .map(getMiddlePageNumber)
      .sum

  private def fixOrder(beforesByAfter: Map[Int, Seq[Int]])(queue: Seq[Int]): Seq[Int] =
    queue
      .map: pageNumber =>
        val befores = beforesByAfter.getOrElse(pageNumber, Seq.empty).filter(queue.contains)
        befores.length -> pageNumber
      .sortBy(_._1)
      .map(_._2)
