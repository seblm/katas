# Notes

## Student 1

1. ✅ Il y a des tests unitaires, ils sont bien écrits.
2. ❌ Certains cas de test ne sont pas couverts :
   1. des instructions invalides ;
   2. des directions invalides.
3. ✅ L’idée d’utiliser le type alias `Position = (Int, Int, Char)` est intéressante.
4. ✅ Utilisation du fold left pour dépiler les instructions est bien appropriée.
5. ❌ On aurait pu utiliser des enums plutôt que des `Char` pour les commandes et les directions. 
6. ❌ Quand on démarre le serveur, on s'aperçoit que la librairie de log n'est pas bien initialisée :
   ```
   SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
   SLF4J: Defaulting to no-operation (NOP) logger implementation
   SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
   ```
   Puisqu'il existe un fichier de configuration `src/main/resources/logback.xml`, il faut ajouter la dépendance à `logback-classic` :
   ```sbt
   libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.5.16" % Runtime
   ```
   Voici la sortie qu'on obtient alors :
   ```
   [io-compute-8] INFO  o.h.b.c.n.NIO1SocketServerGroup - Service bound to address /[0:0:0:0:0:0:0:0]:8080 
   [io-compute-0] INFO  o.h.b.s.BlazeServerBuilder -
    _   _   _        _ _
   | |_| |_| |_ _ __| | | ___
   | ' \  _|  _| '_ \_  _(_-<
   |_||_\__|\__| .__/ |_|/__/
               |_|
   [io-compute-0] INFO  o.h.b.s.BlazeServerBuilder - http4s v0.23.29 on blaze v0.23.17 started at http://[::]:8080/
   [blaze-acceptor-0-0] INFO  o.h.b.c.ServerChannel - Closing NIO1 channel /[0:0:0:0:0:0:0:0]:8080
   [io-compute-5] INFO  o.h.b.c.n.SelectorLoop - Shutting down SelectorLoop blaze-selector-0
   [io-compute-5] INFO  o.h.b.c.n.SelectorLoop - Shutting down SelectorLoop blaze-selector-1
   [io-compute-5] INFO  o.h.b.c.n.SelectorLoop - Shutting down SelectorLoop blaze-selector-2
   [io-compute-5] INFO  o.h.b.c.n.SelectorLoop - Shutting down SelectorLoop blaze-selector-3
   [io-compute-5] INFO  o.h.b.c.n.SelectorLoop - Shutting down SelectorLoop blaze-selector-4
   [io-compute-5] INFO  o.h.b.c.n.SelectorLoop - Shutting down SelectorLoop blaze-selector-5
   [io-compute-5] INFO  o.h.b.c.n.SelectorLoop - Shutting down SelectorLoop blaze-selector-6
   [io-compute-5] INFO  o.h.b.c.n.SelectorLoop - Shutting down SelectorLoop blaze-selector-7
   [io-compute-5] INFO  o.h.b.c.n.SelectorLoop - Shutting down SelectorLoop blaze-selector-8
   [io-compute-5] INFO  o.h.b.c.n.SelectorLoop - Shutting down SelectorLoop blaze-selector-9
   [io-compute-5] INFO  o.h.b.c.n.SelectorLoop - Shutting down SelectorLoop blaze-selector-10
   [io-compute-5] INFO  o.h.b.c.n.SelectorLoop - Shutting down SelectorLoop blaze-selector-11
   [io-compute-5] INFO  o.h.b.c.n.SelectorLoop - Shutting down SelectorLoop blaze-selector-12
   [io-compute-5] INFO  o.h.b.c.n.SelectorLoop - Shutting down SelectorLoop blaze-acceptor-0-0
   ```
7. ❌ Quand on envoie ce body :
   ```json
   {"width":6,"height":6,"mowers":[{"position":"ONE 1 NORTH","instructions":"GAGAGAGAA"}]}
   ```
   On obtient une erreur 500 avec une erreur non gérée :
   ```
   [io-compute-5] ERROR o.h.s.service-errors - Error servicing request: POST / from ::1 
   java.lang.NumberFormatException: For input string: "ONE"
     at java.base/java.lang.NumberFormatException.forInputString(NumberFormatException.java:67)
     at java.base/java.lang.Integer.parseInt(Integer.java:668)
     at java.base/java.lang.Integer.parseInt(Integer.java:786)
     at scala.collection.StringOps$.toInt$extension(StringOps.scala:908)
     at routes.MowerRoutes$.$anonfun$1(MowerRoutes.scala:21)
     at routes.MowerRoutes$.routes$MowerRoutes$$anon$1$$_$applyOrElse$$anonfun$1(MowerRoutes.scala:22)
     at run$ @ Main$.run(Main.scala:6)
   ```
   L'appel à `toInt` lance une exception non gérée.
8. ❌ Quand on envoie ce body :
   ```json
   {"width":6,"height":6,"mowers":[{"position":"1 1","instructions":"GAGAGAGAA"}]}
   ```
   On obtient une erreur 500 avec une erreur non gérée :
   ```
   [io-compute-7] ERROR o.h.s.service-errors - Error servicing request: POST / from ::1 
   scala.MatchError: [Ljava.lang.String;@55a113ca (of class [Ljava.lang.String;)
     at routes.MowerRoutes$.$anonfun$1(MowerRoutes.scala:20)
     at scala.collection.immutable.List.map(List.scala:246)
     at routes.MowerRoutes$.routes$MowerRoutes$$anon$1$$_$applyOrElse$$anonfun$1(MowerRoutes.scala:22)
   ```
   L'appel au déconstructeur d'`Array` échoue car `split` ne retourne pas un tableau de trois éléménts. 
9. ✅ Les erreurs de body malformés sont bien gérées par la librairie http.
10. ✅ L'intégration du service statique avec les routes est bien réalisé.
11. ❌ On aurait pu éviter d'utiliser le builder déprécié en remplaçant :
    ```scala 3
    import org.http4s.server.blaze.BlazeServerBuilder
    ```
    par
    ```scala 3
    import org.http4s.blaze.server.BlazeServerBuilder
    ```
12. ✅ Utilisation de l'auto dérivation de circe pour la sérialisation et la désérialisation des case classes.

## Student 2

1. ❌ Aucun test automatisé.
2. ✅ Toutes les erreurs de parsing sont bien traitées en http error response 400
3. ❌ Quand on démarre le serveur, on s'aperçoit que la librairie de log n'est pas bien initialisée :
   ```
   SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
   SLF4J: Defaulting to no-operation (NOP) logger implementation
   SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
   ```
   Puisqu'il existe un fichier de configuration `src/main/resources/logback.xml`, il faut ajouter la dépendance à `logback-classic` :
   ```sbt
   libraryDependencies += "org.slf4j" % "slf4j-simple" % "2.0.16" % Runtime
   ```
   Voici la sortie qu'on obtient alors :
   ```
   [io-compute-8] INFO org.http4s.ember.server.EmberServerBuilderCompanionPlatform - Ember-Server service bound to address: [::]:8080
   ```
4. ❌ Utilisation de `println` plutôt qu'un log
5. ✅ L'usage de la librairie de serialization/deserialization Json _circe_ est bien maîtrisée et bien utilisée.
5. ✅ Le serveur http est bien instancié, simple et efficace.
6. ✅ Utilisation de `foldLeft` pour itérer sur les tondeuses et interpréter les instructions
7. ✅ Bonne utilisation du pattern matching
8. ✅/❌ Il y a une décomposition inutile `val Position(x, y, direction) = mower.position` mais ça permet du code concis plus bas donc
   pourquoi pas.

## Student 3

1. ✅ Un test unitaire qui vérifie la logique interne
2. ❌ Les erreurs de parsing retournent un response code 200, avec des messages json malformés. Exemple :
  ```http request
  POST http://localhost:8080
  Content-Type: application/json
  
  {"width":6,"height":6}
  ```
  ```json
  {
    "error": "JSONObject["
    mowers
    "] not found."
  }
  ```
  Cela est dû au fait que `MowerHandler` n'utilise pas la libraire Json pour créer la réponse d'erreur.
3. ✅ L'intégration du serveur web Pekko est propre
4. ✅ Le découpage dans les packages et les object est bien approprié
5. ❌ L'usage de la librairie Java org.json:json est du code impératif avec des boucles for, du code mutable et des structures de données
   mutables.
6. ✅ La logique est bien implémentée, la modélisation dans des _case class_ est appropriée, usage de `map` et `foldLeft`.
7. ❌ On aurait pu simplifier le _trait scellé_ `Orientation` en une _enum_.

## Student 4

1. ✅ Toutes les erreurs de parsing sont bien gérées en response code 400 avec des messages d'erreur provenant de la librairie de parsing
   `play-json`.
2. ✅ Il y a deux tests d'acceptance bien écrits.
3. ✅ L'intégration de PlayFramework et de `play-json` est bien réalisée.
4. ✅ L'algo est bien implémenté avec `map` et `foldLeft`.
5. ✅ Usage original et simple d'une `Map` qui permet de tourner à gauche et à droite.
6. ❌ On aurait pu utiliser une _enum_ pour l'orientation au lieu de rester en `Char`.

## Student 5

1. ✅ Certaines erreurs de parsing sont bien gérées en response code 400.
2. ❌ Lorsqu'on envoie cet input :
   ```json
   {"width":6,"height":6,"mowers":[{"position":"ONE 1 NORTH","instruction":"GAGAGAGAA"}]}
   ```
   On obtient une erreur 500 non gérée :
   ```
   NumberFormatException: For input string: "ONE"
   ```
   Ceci est dû à l'appel non protégé à `toInt`.
3. ❌ Lorsqu'on envoie cet input :
   ```json
   {"width":6,"height":6,"mowers":[{"position":"1 1","instruction":"GAGAGAGAA"}]}
   ```
   On obtient une erreur 500 non gérée :
   ```
   MatchError: [Ljava.lang.String;@5956c070 (of class [Ljava.lang.String;)
   ```
   Elle est dûe à une déconstruction en un tableau de trois valeurs et trois seulement d'un retour de l'appel de la méthode non protégée à
   `split(" ")`.
4. ✅ Il y a des tests unitaires.
5. ❌ Le test unitaire ne compile pas. Voici l'erreur :
   ```
   test/MowersController.scala:29:31: value index is not a member of controllers.MowersController
         val result = controller.index().apply(FakeRequest(POST, "/").withJsonBody(jsonRequest))
   ```
   Il faut suivre
   [cette documentation](https://www.playframework.com/documentation/3.0.x/ScalaTestingWithScalaTest#Unit-Testing-EssentialAction) et
   modifier le test comme ceci :
   ```diff
     package controllers
   
   + import org.apache.pekko.stream.Materializer
     import org.scalatestplus.play._
   + import org.scalatestplus.play.guice.GuiceOneAppPerSuite
     import play.api.test._
     import play.api.test.Helpers._
     import play.api.mvc._
     import play.api.libs.json.Json
   
   - class MowersControllerSpec extends PlaySpec {
   + class MowersControllerSpec extends PlaySpec with GuiceOneAppPerSuite {
   + 
   + implicit lazy val materializer: Materializer   = app.materializer
   + implicit lazy val Action: DefaultActionBuilder = app.injector.instanceOf(classOf[DefaultActionBuilder])
   
     "MowersController" should {
       "respond with OK status" in {
         val controller = new MowersController(stubControllerComponents())
   
         val jsonRequest = Json.parse("""
           {
             "width": 5,
             "height": 5,
             "mowers": []
           }
         """)
   
   -     val result = controller.index().apply(FakeRequest(POST, "/").withJsonBody(jsonRequest))
   +     val result = call(controller.process(), FakeRequest(POST, "/").withJsonBody(jsonRequest))
   
         status(result) mustBe OK
       }
     }
   }
   ```
   On obtient ainsi un test OK:
   ```
   $ testOnly controllers.MowersControllerSpec
   [info] MowersControllerSpec:
   2025-02-23 18:51:00 INFO  p.a.http.HttpErrorHandlerExceptions  Registering exception handler: guice-provision-exception-handler
   [info] MowersController
   [info] - should respond with OK status
   [info] Run completed in 3 seconds, 135 milliseconds.
   [info] Total number of tests run: 1
   [info] Suites: completed 1, aborted 0
   [info] Tests: succeeded 1, failed 0, canceled 0, ignored 0, pending 0
   [info] All tests passed.
   [success] Total time: 5 s, completed 23 févr. 2025, 18:51:02
   ```
6. ✅ Utilisation de `foldLeft` pour exécuter les instructions.
7. ✅ Utilisation d'une énumération pour représenter l'orientation.
8. ✅ La rotation à gauche et à droite est maligne.
9. ✅ Bon usage de la libraire de sérialisation/désérialisation _play-json_.
