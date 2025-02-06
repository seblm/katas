package http

import org.apache.pekko.actor.ActorSystem
import org.apache.pekko.http.scaladsl.Http
import org.apache.pekko.http.scaladsl.server.Directives._
import org.apache.pekko.stream.Materializer

import scala.io.StdIn
import scala.concurrent.ExecutionContextExecutor

object Server {
  def start(port: Int): Unit = {
    implicit val system: ActorSystem = ActorSystem("mower-system")
    implicit val materializer: Materializer = Materializer(system)
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher

    // Définition des routes HTTP
    val route =
      pathEndOrSingleSlash {
        post {
          entity(as[String]) { body =>
            complete(MowerHandler.handleRequest(body))
          }
        }
      }

    // Lancement du serveur
    val bindingFuture = Http().newServerAt("0.0.0.0", port).bind(route)

    println(s"Serveur Pekko HTTP lancé sur http://localhost:$port/")

    StdIn.readLine() // Bloque jusqu'à ce que l'utilisateur appuie sur ENTER

    bindingFuture
      .flatMap(_.unbind()) // Arrête le serveur
      .onComplete(_ => system.terminate()) // Termine le système d'acteurs
  }
}
