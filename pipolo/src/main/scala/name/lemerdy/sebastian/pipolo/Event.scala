package name.lemerdy.sebastian.pipolo

sealed trait Event

case object NextPlayer extends Event

case object GameOver extends Event

case object GameIsAlreadyOver extends Event
