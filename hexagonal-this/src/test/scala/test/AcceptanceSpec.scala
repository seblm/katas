package test

import cats.Id
import cats.data.EitherT
import hexagonal.domain.{PoemRepositoryPort, PoemsLibrary, PoemsLibraryPort}
import monolith.PoetryMonolithApp
import org.scalatest.FlatSpec
import org.scalatest.Matchers._

class AcceptanceSpec extends FlatSpec {

  "Hexagonal" should "print poem" in {
    val infrastructure: PoemRepositoryPort[Id] = new PoemRepositoryPort[Id] {
      override def find(title: String): EitherT[Id, Throwable, Option[String]] = EitherT.rightT(Some("my poem\nis good"))
    }
    val domain: PoemsLibraryPort[Id] = new PoemsLibrary(infrastructure)

    val poem: EitherT[Id, Throwable, String] = domain.printPoem("anything")

    poem.value shouldEqual Right("MY POEM\nIS GOOD")
  }

  it should "print default message" in {
    val infrastructure: PoemRepositoryPort[Id] = new PoemRepositoryPort[Id] {
      override def find(title: String): EitherT[Id, Throwable, Option[String]] = EitherT.rightT(None)
    }
    val domain: PoemsLibraryPort[Id] = new PoemsLibrary(infrastructure)

    val poem: EitherT[Id, Throwable, String] = domain.printPoem("anything")

    poem.value shouldEqual Right("no poem found with title anything")
  }

  "Monolith" should "print poem" in {
    val poem = PoetryMonolithApp.run("Le bateau ivre")

    poem should startWith("COMME JE DESCENDAIS DES FLEUVES IMPASSIBLES,")
    poem should endWith("NI NAGER SOUS LES YEUX HORRIBLES DES PONTONS.")
  }

  it should "print default message" in {
    val poem = PoetryMonolithApp.run("anything")

    poem shouldEqual "no poem found with title anything"
  }

}
