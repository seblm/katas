package main

import http.Server

object Main {
  def main(args: Array[String]): Unit = {
    Server.start(8080)
  }
}

/*
 * Exemple de requête HTTP POST:*

curl -X POST -H "Content-Type: application/json" -d '{
  "width":6,
  "height":6,
  "mowers":[
    {"position":"1 2 N", "instructions":"GAGAGAGAA"},
    {"position":"3 3 E", "instructions":"AADAADADDA"}
  ]
}' http://localhost:8080/

 * Réponse attendue:*
{"finalPositions":["1 3 N","5 1 E"]}
 */
