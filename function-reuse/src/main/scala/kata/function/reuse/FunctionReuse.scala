package kata.function.reuse

import scala.util.{Failure, Success, Try}

object FunctionReuse:

  trait Functor[F[_]]:
    extension [A, B](f: A => B) def lift: F[A] => F[B]

    extension [A](fa: F[A])
      def map[B](f: A => B): F[B] =
        f.lift.apply(fa)

  trait OptionAsFunctor extends Functor[Option]:
    extension [A, B](f: A => B)
      override def lift: Option[A] => Option[B] =
        case Some(value) => Some(f(value))
        case None        => None

  trait TryAsFunctor extends Functor[Try]:
    extension [A, B](f: A => B)
      override def lift: Try[A] => Try[B] =
        case Success(value)     => Success(f(value))
        case Failure(exception) => Failure(exception)

  implicit object OptionInstances extends OptionAsFunctor

  implicit object TryInstances extends TryAsFunctor
