package name.lemerdy.sebastian

import cats.effect.IO

import scala.io.StdIn

object CatsEffectExperiments extends App {

  val program = for {
    _ ← IO { println("Welcome to Scala!  What's your name?") }
    name ← IO { StdIn.readLine }
    _ ← IO { println(s"Well hello, $name!") }
  } yield ()

  program.unsafeRunSync()

  trait Response[T] {
    def onError(t: Throwable): Unit
    def onSuccess(t: T): Unit
  }

  trait Channel {
    def sendBytes(chunk: Array[Byte], handler: Response[Unit]): Unit
    def receiveBytes(handler: Response[Array[Byte]]): Unit
  }

  def send(c: Channel, chunk: Array[Byte]): IO[Unit] = {
    IO async { cb ⇒
      c.sendBytes(chunk, new Response[Unit] {
        override def onError(t: Throwable): Unit = cb(Left(t))
        override def onSuccess(t: Unit): Unit = cb(Right(()))
      })
    }
  }

  def receive(c: Channel): IO[Array[Byte]] = {
    IO async { cb ⇒
      c.receiveBytes(new Response[Array[Byte]] {
        override def onError(t: Throwable): Unit = cb(Left(t))
        override def onSuccess(chunk: Array[Byte]): Unit = cb(Right(chunk))
      })
    }
  }

  val c: Channel = null // pretend this is an actual channel

  for {
    _ <- send(c, "SYN".getBytes)
    response <- receive(c)

    _ <- if (response == "ACK".getBytes)   // pretend == works on Array[Byte]
      IO { println("found the guy!") }
    else
      IO { println("no idea what happened, but it wasn't good") }
  } yield ()

}
