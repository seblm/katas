package acceptance

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, PrintStream, StringWriter}

import hexagonal.domain.{PoemRepositoryPort, PoemsLibrary, PoemsLibraryPort}
import monolith.PoetryMonolithApp
import org.scalatest.FlatSpec
import org.scalatest.Matchers._
import sun.net.www.content.text.PlainTextInputStream

class AcceptanceSpec extends FlatSpec {

  "Hexagonal" should "print poem" in {
    val infrastructure: PoemRepositoryPort = new PoemRepositoryPort {
      override def find(title: String): Option[String] = Some("my poem\nis good")
    }
    val domain: PoemsLibraryPort = new PoemsLibrary(infrastructure)

    val poem: String = domain.printPoem("anything")

    poem shouldEqual "MY POEM\nIS GOOD"
  }

  "Hexagonal" should "print default message" in {
    val infrastructure: PoemRepositoryPort = new PoemRepositoryPort {
      override def find(title: String): Option[String] = None
    }
    val domain: PoemsLibraryPort = new PoemsLibrary(infrastructure)

    val poem: String = domain.printPoem("anything")

    poem shouldEqual "no poem found with title anything"
  }

  "Monolith" should "print poem" in {
    val oldIn = System.in
    System.setIn(new ByteArrayInputStream("anything\n".getBytes()))
    val oldOut = System.out
    val output = new ByteArrayOutputStream()
    System.setOut(new PrintStream(output))

    PoetryMonolithApp.main(Array.empty)

    output.toString shouldEqual "MY POEM\nIS GOOD"
    System.setIn(oldIn)
    System.setOut(oldOut)
  }

}
