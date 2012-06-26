import sbt._
import Keys._

import com.github.siasia.WebPlugin._
import com.github.siasia.PluginKeys._

object HyperScalaBuild extends Build {
  val sgineVersion = "1.0-SNAPSHOT"

  val sgineReflect = "org.sgine" %% "sgine-reflect" % sgineVersion
  val sgineScene = "org.sgine" %% "sgine-scene" % sgineVersion

  val jettyVersion = "7.4.2.v20110526"
  val jettyServer = "org.eclipse.jetty" % "jetty-server" % jettyVersion % "container;provided"
  val jettyWebapp = "org.eclipse.jetty" % "jetty-webapp" % jettyVersion % "container;provided"
  val jettyServlet = "org.eclipse.jetty" % "jetty-servlet" % jettyVersion % "container;provided"
  val jettyJsp = "org.eclipse.jetty" % "jetty-jsp-2.1" % jettyVersion % "container;provided"
  val glassfishJsp = "org.mortbay.jetty" % "jsp-2.1-glassfish" % "2.1.v20100127" % "container;provided"

  val servletApi = "javax.servlet" % "javax.servlet-api" % "3.0.1"

  val baseSettings = Defaults.defaultSettings ++ Seq(
    version := "1.0",
    organization := "org.hyperscala",
    scalaVersion := "2.9.1",
    libraryDependencies ++= Seq(
      sgineReflect,
      sgineScene
    ),
    scalacOptions ++= Seq("-unchecked", "-deprecation"),
    resolvers ++= Seq("Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/")
  )

  lazy val root = Project("root", file("."), settings = baseSettings)
    .aggregate(core, helloworld, numberguess, todomvc)
  lazy val core = Project("hyperscala-core", file("core"), settings = baseSettings)
    .settings(libraryDependencies += servletApi)
  lazy val helloworld = Project("hyperscala-helloworld", file("examples/helloworld"), settings = baseSettings ++ seq(webSettings: _*))
    .dependsOn(core)
    .settings(port := 8080)
    .settings(libraryDependencies ++= Seq(jettyServer, jettyWebapp, jettyServlet, jettyJsp, glassfishJsp))
  lazy val numberguess = Project("hyperscala-numberguess", file("examples/numberguess"), settings = baseSettings ++ seq(webSettings: _*))
    .dependsOn(core)
    .settings(port := 8080)
    .settings(libraryDependencies ++= Seq(jettyServer, jettyWebapp, jettyServlet, jettyJsp, glassfishJsp))
  lazy val todomvc = Project("hyperscala-todomvc", file("examples/todomvc"), settings = baseSettings ++ seq(webSettings: _*))
    .dependsOn(core)
    .settings(port := 8080)
    .settings(libraryDependencies ++= Seq(jettyServer, jettyWebapp, jettyServlet, jettyJsp, glassfishJsp))
}