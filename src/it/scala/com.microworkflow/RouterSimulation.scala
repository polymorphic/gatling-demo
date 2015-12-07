package com.microworkflow

/**
 * Created by dam on 12/5/15.
 */
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

class RouterSimulation extends Simulation {

  def makeProtocolBuilder = http
    .baseURL("http://localhost:8080")

  private val getReponseKey = "getResponse"

  val getRequestBuilder = http("get request")
    .get("/generate")
    .check(status.is(session ⇒ 200), bodyString.saveAs(getReponseKey))

  val postRequestBuider =  http("post request")
    .post("/convert")
    .body(StringBody(session ⇒ { s"time was: ${session(getReponseKey).validate[String].get}"}))
    .check(status.is(200))

    def makeScenarioBuilder: ScenarioBuilder =
      scenario("sample scenario")
        .exec(getRequestBuilder)
        .exec(postRequestBuider)

    setUp(makeScenarioBuilder.inject(atOnceUsers(1))).protocols(makeProtocolBuilder)
}
