package main

import http.Server

object Main {
  def main(args: Array[String]): Unit = {
    Server.start(8080)  // Lance le serveur Pekko HTTP
  }
}
