package hexagonalthis

trait IRequestVerses[F[_]] {

  def giveMeSomePoetry(): F[String]

}
