package mowitnow.mitchel.andriatsilavo

case class Lawn(width: Int, height: Int) {
  def moveForward(lawnmower: Lawnmower): Either[String, Lawnmower] = {
    val newPosition = lawnmower.position.move(lawnmower.orientation)

    if (isValidPosition(newPosition)) {
      Right(lawnmower.copy(position = newPosition))
    } else {
      Left(s"Cannot move outside the lawn: ${newPosition.x}, ${newPosition.y}")
    }
  }

  def isValidPosition(position: Position): Boolean = {
    position.x >= 0 && position.x <= width && position.y >= 0 && position.y <= height
  }
}
