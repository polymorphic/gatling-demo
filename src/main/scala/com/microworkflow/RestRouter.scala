package com.microworkflow

import akka.actor.{Actor, ActorLogging}
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import spray.routing.{ExceptionHandler, HttpService, Route, RoutingSettings}
import spray.util.LoggingContext

import scala.util.Random

class RestRouter extends Actor with HttpService with ActorLogging {
  val mapper = new ObjectMapper()
  mapper.registerModule(DefaultScalaModule)

  override implicit def actorRefFactory = context

  implicit val routingSettings = RoutingSettings.default(actorRefFactory)

  implicit def myExceptionHandler(implicit log: LoggingContext) = ExceptionHandler.default

  override def receive: Receive = runRoute(route)

  val route: Route = {
    path("generate") {
      get {
        complete(System.nanoTime().toString)
      }
    } ~
    path("convert") {
      post {
        entity(as[String]) { s ⇒
          completeThisRequest(s)
        }
      }
    } ~
    path("ping") {
      get {
        complete("pong")
      }
    }
  }

  private[this] def completeThisRequest(s: String): Route = {
    requestContext ⇒ {
      requestContext.complete(s.toUpperCase)
    }
  }

}
