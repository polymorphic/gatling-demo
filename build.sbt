name := "gatling demo"

organization := "com.microworkflow"

scalaVersion := "2.11.7"

version := "1.0.0"

resolvers ++=
  Seq("spray repo" at "http://repo.spray.io"
    , "Sonata snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
  )

libraryDependencies ++= {
  val akkaVersion = "2.4.1"
  val sprayVersion = "1.3.3"
  Seq("com.typesafe.akka" %% "akka-actor" % akkaVersion
    , "io.spray" %% "spray-can" % sprayVersion
    , "io.spray" %% "spray-routing" % sprayVersion
    , "io.gatling" % "gatling-test-framework" % "2.1.7" % "it" withSources() withJavadoc()
    , "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.1.7" % "it" withSources() withJavadoc()
    , "nl.grons" %% "metrics-scala" % "3.5.2_a2.3"
    , "io.dropwizard.metrics" % "metrics-jvm" % "3.1.2"
    , "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.6.0-1"
  )
}

javaOptions ++= Seq("-Djava.net.preferIPv4Stack=true")

scalacOptions ++= Seq("-unchecked", "-deprecation", "-Xlint", "-Ywarn-dead-code", "-encoding", "UTF-8")