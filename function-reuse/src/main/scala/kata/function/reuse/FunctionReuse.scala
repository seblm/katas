package kata.function.reuse

import scala.util.{Failure, Success, Try}

object FunctionReuse:

  trait Functor[F[_]]:
    extension [A, B](f: A => B) def lift: F[A] => F[B]

    extension [A](fa: F[A])
      def map[B](f: A => B): F[B] =
        f.lift.apply(fa)

  trait Apply[F[_]] extends Functor[F]:
    extension [A, B](ff: F[A => B]) def ap: F[A] => F[B]

    extension [A, B, C](f: (A, B) => C)
      def lift2: (F[A], F[B]) => F[C] =
        Function.uncurried(f.curried.lift.andThen(_.ap))

    extension [A, B, C](fab: (F[A], F[B]))
      def map2(f: (A, B) => C): F[C] =
        f.lift2.apply(fab._1, fab._2)

  trait Applicative[F[_]] extends Apply[F]:
    extension [A](a: A) def pure: F[A]

    extension [A](fas: List[F[A]])
      def flip: F[List[A]] = fas match
        case head :: tail => (head, tail.flip).map2(_ :: _)
        case Nil          => Nil.pure

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

  trait OptionAsApply extends OptionAsFunctor with Apply[Option]:
    extension [A, B](ff: Option[A => B])
      override def ap: Option[A] => Option[B] = (ff, _) match
        case (Some(f), Some(value)) => Some(f(value))
        case _                      => None

  trait TryAsApply extends TryAsFunctor with Apply[Try]:
    extension [A, B](ff: Try[A => B])
      override def ap: Try[A] => Try[B] = (ff, _) match
        case (Success(f), Success(value)) => Success(f(value))
        case (Failure(exception), _)      => Failure(exception)
        case (_, Failure(exception))      => Failure(exception)

  trait OptionAsApplicative extends OptionAsApply with Applicative[Option]:
    extension [A](a: A) override def pure: Option[A] = Some(a)

  trait TryAsApplicative extends TryAsApply with Applicative[Try]:
    extension [A](a: A) override def pure: Try[A] = Success(a)

  implicit object OptionInstances extends OptionAsApplicative

  implicit object TryInstances extends TryAsApplicative
