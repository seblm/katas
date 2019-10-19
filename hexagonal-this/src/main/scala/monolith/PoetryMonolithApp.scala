package monolith

import scala.io.Source
import io.circe.generic.auto._
import io.circe.parser.decode

object PoetryMonolithApp extends App {

  val poems = decode[Poems](Source.fromResource("Poetry.json").getLines().mkString("\n"))

  println("Please enter a poem title:")
  val title = Console.in.readLine()

  poems.foreach(_.poems.find(_.title.contains(title))
    .map(_.poem.toUpperCase)
    .fold(println(s"no poem found with title $title"))(println))

}
