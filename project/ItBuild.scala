import sbt._
import io.gatling.sbt.GatlingPlugin
import com.typesafe.sbt.packager.archetypes.JavaAppPackaging

object ItBuild extends Build {
  lazy val root =
    Project(id = "gatling-demo"
      , base = file("."))
      .enablePlugins(JavaAppPackaging, GatlingPlugin)
      .configs(IntegrationTest)
      .settings(Defaults.itSettings)
}