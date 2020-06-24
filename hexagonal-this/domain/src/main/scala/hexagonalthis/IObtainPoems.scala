package hexagonalthis

trait IObtainPoems[F[_]] {
  def getAPoem(): F[String]
}
