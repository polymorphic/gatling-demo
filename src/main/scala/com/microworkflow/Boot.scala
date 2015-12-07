package com.microworkflow

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.io.IO
import spray.can.Http

object Boot {
  implicit val actorSystem = ActorSystem()

  def main(args: Array[String]) {
    val router: ActorRef = actorSystem.actorOf(Props[RestRouter])
    val port = sys.env.get("PORT").map(_.toInt).getOrElse(8080)
    IO(Http) ! Http.Bind(router, interface = "0.0.0.0", port = port)
  }
}

