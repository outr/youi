import sbt._
import Keys._

import com.github.siasia.WebPlugin._
import com.github.siasia.PluginKeys._

object HyperScalaBuild extends Build {
  val sgineVersion = "1.0-SNAPSHOT"

  val sgineReflect = "org.sgine" %% "sgine-reflect" % sgineVersion
  val sgineScene = "org.sgine" %% "sgine-scene" % sgineVersion

  val htmlcleaner = "net.sourceforge.htmlcleaner" % "htmlcleaner" % "2.2"

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
      sgineScene,
      htmlcleaner
    ),
    scalacOptions ++= Seq("-unchecked", "-deprecation"),
    resolvers ++= Seq("Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"),
    publishTo <<= version {
      (v: String) =>
        val nexus = "https://oss.sonatype.org/"
        if (v.trim.endsWith("SNAPSHOT"))
          Some("snapshots" at nexus + "content/repositories/snapshots")
        else
          Some("releases" at nexus + "service/local/staging/deploy/maven2")
    },
    publishArtifact in Test := false,
    pomExtra := (
      <url>http://hyperscala.org</url>
        <licenses>
          <license>
            <name>BSD-style</name>
            <url>http://www.opensource.org/licenses/bsd-license.php</url>
            <distribution>repo</distribution>
          </license>
        </licenses>
        <scm>
          <developerConnection>scm:https://github.com/darkfrog26/hyperscala.git</developerConnection>
          <connection>scm:https://github.com/darkfrog26/hyperscala.git</connection>
          <url>https://github.com/darkfrog26/hyperscala</url>
        </scm>
        <developers>
          <developer>
            <id>darkfrog</id>
            <name>Matt Hicks</name>
            <url>http://matthicks.com</url>
          </developer>
        </developers>)
  )

  private def createSettings(_name: String) = baseSettings ++ Seq(name := _name)

  lazy val root = Project("root", file("."), settings = createSettings("hyperscala-root"))
    .settings(publishArtifact in Compile := false, publishArtifact in Test := false)
    .aggregate(core, helloworld, numberguess, todomvc)
  lazy val core = Project("core", file("core"), settings = createSettings("hyperscala-core"))
    .settings(libraryDependencies += servletApi)
  lazy val basic = Project("basic", file("examples/basic"), settings = createSettings("hyperscala-basic"))
    .dependsOn(core)
    .settings(webSettings: _*)
    .settings(port := 8080)
    .settings(libraryDependencies ++= Seq(jettyServer, jettyWebapp, jettyServlet, jettyJsp, glassfishJsp))
  lazy val helloworld = Project("helloworld", file("examples/helloworld"), settings = createSettings("hyperscala-helloworld"))
    .dependsOn(core)
    .settings(webSettings: _*)
    .settings(port := 8080)
    .settings(libraryDependencies ++= Seq(jettyServer, jettyWebapp, jettyServlet, jettyJsp, glassfishJsp))
  lazy val numberguess = Project("numberguess", file("examples/numberguess"), settings = createSettings("hyperscala-numberguess"))
    .dependsOn(core)
    .settings(webSettings: _*)
    .settings(port := 8080)
    .settings(libraryDependencies ++= Seq(jettyServer, jettyWebapp, jettyServlet, jettyJsp, glassfishJsp))
  lazy val todomvc = Project("todomvc", file("examples/todomvc"), settings = createSettings("hyperscala-todomvc"))
    .dependsOn(core)
    .settings(webSettings: _*)
    .settings(port := 8080)
    .settings(libraryDependencies ++= Seq(jettyServer, jettyWebapp, jettyServlet, jettyJsp, glassfishJsp))
}