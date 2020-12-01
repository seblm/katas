package name.lemerdy.sebastian.katas.matchsticks

import scala.io.Source

class LengthLines(santaWishes: String) {
  private val santaWishList = santaWishes.split('\n').map(new LengthCharacter(_))
  val numberOfCharactersOfStringCode: Int = santaWishList.map(_.numberOfCharactersOfStringCode).sum
  val numberOfCharactersInMemory: Int = santaWishList.map(_.numberOfCharactersInMemory).sum
  val encode: Int = santaWishList.map(_.encode.length).sum
}

object LengthLines {
  def main(args: Array[String]): Unit = {
    val lengthLines = new LengthLines(Source.fromInputStream(getClass.getResourceAsStream("input")).mkString)
    println(s"${lengthLines.numberOfCharactersOfStringCode} - ${lengthLines.numberOfCharactersInMemory} = ${lengthLines.numberOfCharactersOfStringCode - lengthLines.numberOfCharactersInMemory}")
    println(s"${lengthLines.encode} - ${lengthLines.numberOfCharactersOfStringCode} = ${lengthLines.encode - lengthLines.numberOfCharactersOfStringCode}")
  }
}