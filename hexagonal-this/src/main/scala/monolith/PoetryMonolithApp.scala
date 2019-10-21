package monolith

import io.circe.generic.auto._
import io.circe.parser.decode

import scala.io.Source

object PoetryMonolithApp extends App {

  def run(title: String): String = {
    val poem = for {
      poems <- decode[Poems](Source.fromResource("Poetry.json").getLines().mkString("\n"))
      poem <- poems.poems.find(_.title.contains(title)).map(_.poem.toUpperCase).toRight(new Error("fail"))
    } yield poem

    poem.fold(_ => s"no poem found with title $title", identity)
  }

  println("Please enter a poem title:")
  println(run(Console.in.readLine()))

}
