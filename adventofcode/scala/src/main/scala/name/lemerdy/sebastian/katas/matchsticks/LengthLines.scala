package name.lemerdy.sebastian.katas.matchsticks

import scala.io.Source

class LengthLines(santaWishes: String) {
  private val santaWishList = santaWishes.split('\n').map(new LengthCharacter(_))
  val numberOfCharactersOfStringCode = santaWishList.map(_.numberOfCharactersOfStringCode).sum
  val numberOfCharactersInMemory = santaWishList.map(_.numberOfCharactersInMemory).sum
  val encode = santaWishList.map(_.encode.length).sum
}

object LengthLines {
  def main(args: Array[String]) {
    val lengthLines = new LengthLines(Source.fromInputStream(getClass.getResourceAsStream("input")).mkString)
    println(s"${lengthLines.numberOfCharactersOfStringCode} - ${lengthLines.numberOfCharactersInMemory} = ${lengthLines.numberOfCharactersOfStringCode - lengthLines.numberOfCharactersInMemory}")
    println(s"${lengthLines.encode} - ${lengthLines.numberOfCharactersOfStringCode} = ${lengthLines.encode - lengthLines.numberOfCharactersOfStringCode}")
  }
}