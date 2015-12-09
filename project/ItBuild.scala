import sbt._
import io.gatling.sbt.GatlingPlugin
import com.typesafe.sbt.packager.archetypes.JavaAppPackaging
import spray.revolver.RevolverPlugin.Revolver

object ItBuild extends Build {
  lazy val root =
    Project(id = "gatling-demo"
      , base = file(".")).settings((Defaults.coreDefaultSettings ++ Defaults.itSettings ++ Revolver.settings): _*)
      .enablePlugins(JavaAppPackaging, GatlingPlugin)
      .configs(IntegrationTest)
}