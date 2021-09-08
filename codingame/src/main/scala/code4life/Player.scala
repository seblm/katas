package code4life

import scala.io.StdIn.{readInt, readLine}

sealed abstract class RobotIdentifier(val id: Int)

case object Me extends RobotIdentifier(0)

case object Dr extends RobotIdentifier(1)

case class PlayerState(
    robot: RobotIdentifier,
    target: String,
    eta: Int,
    score: Int,
    storagea: Int,
    storageb: Int,
    storagec: Int,
    storaged: Int,
    storagee: Int
)

sealed abstract class SampleType

case object UnDiagnosed extends SampleType

case object Diagnosed extends SampleType

object PlayerState {

  def apply(robot: RobotIdentifier, input: Array[String]): PlayerState = {
    val Array(
      target,
      eta,
      score,
      storagea,
      storageb,
      storagec,
      storaged,
      storagee,
      expertisea,
      expertiseb,
      expertisec,
      expertised,
      expertisee
    ) = input
    PlayerState(
      robot,
      target,
      eta.toInt,
      score.toInt,
      storagea.toInt,
      storageb.toInt,
      storagec.toInt,
      storaged.toInt,
      storagee.toInt
    )
  }

}

case class Sample(
    id: Int,
    rank: Int,
    `type`: SampleType,
    carriedBy: Option[RobotIdentifier],
    health: Int,
    costA: Int,
    costB: Int,
    costC: Int,
    costD: Int,
    costE: Int
)

object Sample {

  private def sampleType(health: String): SampleType = health match {
    case "-1" => UnDiagnosed
    case _    => Diagnosed
  }

  def apply(input: Array[String]): Sample = {
    val Array(_sampleid, _carriedby, _rank, expertisegain, _health, _costa, _costb, _costc, _costd, _coste) = input
    val rank = _rank.toInt
    val maybeCarrier = _carriedby.toInt match {
      case 0 => Some(Me)
      case 1 => Some(Dr)
      case _ => None
    }
    Sample(
      _sampleid.toInt,
      rank,
      sampleType(_health),
      maybeCarrier,
      _health.toInt,
      _costa.toInt,
      _costb.toInt,
      _costc.toInt,
      _costd.toInt,
      _coste.toInt
    )
  }

}

trait Lab {

  def projectcount: Int

  def unknownabcde: Array[Int]

  def me: PlayerState

  def dr: PlayerState

  def availables: Array[Int]

  def samples: Seq[Sample]

  def debug(message: Any): Unit = Console.err.println(Option(message).getOrElse("null"))

  def command(command: String): Unit = println(command)

}

class ConsoleLab extends Lab {

  override def projectcount: Int = readInt

  override def unknownabcde: Array[Int] = for (i <- readLine.split(" ")) yield i.toInt

  override def me: PlayerState = PlayerState(Me, readLine.split(" "))

  override def dr: PlayerState = PlayerState(Dr, readLine.split(" "))

  override def availables: Array[Int] = for (i <- readLine.split(" ")) yield i.toInt

  override def samples: Seq[Sample] = {
    val samplecount = readInt
    val samples = Range(0, samplecount).map(_ => Sample(readLine.split(" ")))
    debug(samples.mkString("\n"))
    samples
  }

}

@main def player() =

  val lab: Lab = new ConsoleLab()

  val states: List[String] = List(
    "GOTO SAMPLES",
    "GET undiagnosed sample 1",
    "GET undiagnosed sample 2",
    "GET undiagnosed sample 3",
    "GOTO DIAGNOSIS",
    "DIAGNOSE sample",
    "UPLOAD diagnosed sample",
    "GET diagnosed sample",
    "GOTO MOLECULES",
    "GET all molecules",
    "PUT best sample"
  )

  var currentState = 0

  def nextState(command: String): String = {
    currentState += 1
    if (currentState == states.length) {
      currentState = 0
    }
    command
  }

  var currentSample = Option.empty[Sample]

  val projectcount = lab.projectcount

  for (i <- 0 until projectcount) {
    val Array(a, b, c, d, e) = lab.unknownabcde
  }

  // game loop
  while (true) {
    val me = lab.me; lab.debug(me)
    val dr = lab.dr; lab.debug(dr)
    lab.availables
    val samples = lab.samples

    val nextCommand = states(currentState) match {
      case "GET undiagnosed sample 1" =>
        nextState("CONNECT 1")
      case "GET undiagnosed sample 2" =>
        nextState("CONNECT 2")
      case "GET undiagnosed sample 3" =>
        nextState("CONNECT 3")
      case "GOTO DIAGNOSIS" =>
        currentSample = samples.find(_.carriedBy.contains(Me))
        nextState("GOTO DIAGNOSIS")
      case "DIAGNOSE sample" =>
        nextState(s"CONNECT ${currentSample.map(_.id).getOrElse("")}")
      case "UPLOAD diagnosed sample" =>
        val command = s"CONNECT ${currentSample.map(_.id).getOrElse("")}"
        currentSample = None
        nextState(command)
      case "GET diagnosed sample" =>
        val bestSample = samples.filter(_.carriedBy.isEmpty).filter(_.`type` == Diagnosed).sortBy(_.health).reverse.head
        currentSample = Some(bestSample)
        nextState(s"CONNECT ${currentSample.map(_.id).getOrElse("")}")
      case "GET all molecules" =>
        if (me.storagea < currentSample.get.costA) s"CONNECT A"
        else if (me.storageb < currentSample.get.costB) s"CONNECT B"
        else if (me.storagec < currentSample.get.costC) s"CONNECT C"
        else if (me.storaged < currentSample.get.costD) s"CONNECT D"
        else if (me.storagee < currentSample.get.costE) s"CONNECT E"
        else nextState("GOTO LABORATORY")
      case "PUT best sample" =>
        val command = s"CONNECT ${currentSample.get.id}"
        currentSample = None
        nextState(command)
      case goto =>
        nextState(goto)
    }

    lab.command(nextCommand)
  }
