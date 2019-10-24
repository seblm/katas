package hexagonal.application

import cats.data.EitherT
import cats.effect.{ExitCode, IO, IOApp}
import hexagonal.domain.{PoemRepositoryPort, PoemsLibrary, PoemsLibraryPort}
import hexagonal.infrastructure.PoemRepository

object PoetryHexagonalApp extends IOApp {

  private val infrastructure: PoemRepositoryPort[IO] = new PoemRepository
  private val domain: PoemsLibraryPort[IO] = new PoemsLibrary(infrastructure)

  override def run(args: List[String]): IO[ExitCode] = {
    val result: EitherT[IO, Throwable, ExitCode] = for {
      _ <- EitherT.right(IO(println("Please enter a poem title:")))
      title <- EitherT.right(IO(Console.in.readLine()))
      poem <- domain.printPoem(title)
      _ <- EitherT.right(IO(println(poem)))
    } yield ExitCode.Success

    result.getOrElse(ExitCode.Error)
  }

}
