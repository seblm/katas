package mowitnow

import scala.util.Try

class MowerControl(val input: String, val participants: Map[String, MowerContract]):

  def next(): Map[String, Either[String, Seq[Position]]]

  // constantes topX, topY, participants
  //            mower : const initialPosition, const instructions,
  //                    mutable currentInstructionsLength, Position ou erreur ou pas encore démarré
  
  // 5 5
  // 1 2 N
  // G

  // 1 2 W
  
  // 5 5
  // 1 2 N
  // GA

  // 0 2 W  
  
  """5 5
    |1 2 N
    |GAGAGAGAA
    |3 3 E
    |AADAADADDA""".stripMargin