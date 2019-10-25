package hexagonal.application

import cats.effect.{ExitCode, IO, IOApp}
import cats.syntax.functor._
import hexagonal.domain.{PoemRepositoryPort, PoemsLibrary, PoemsLibraryPort}
import hexagonal.infrastructure.PoemRepositoryAdapter

object PoetryHexagonalApp extends IOApp {

  private val infrastructure: PoemRepositoryPort[IO] = new PoemRepositoryAdapter
  private val domain: PoemsLibraryPort[IO] = new PoemsLibrary(infrastructure)

  override def run(args: List[String]): IO[ExitCode] = {
    val program = for {
      _ <- IO(println("Please enter a poem title:"))
      title <- IO(Console.in.readLine())
      poem <- domain.printPoem(title)
      _ <- IO(println(poem))
    } yield ExitCode.Success

    program.handleErrorWith(e => IO(println(e.getMessage)).as(ExitCode.Error))
  }

}
