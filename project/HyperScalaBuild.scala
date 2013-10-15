import sbt._
import Keys._

import sbtunidoc.Plugin._
import scala.Some
import spray.revolver.RevolverPlugin._

import sbtassembly.Plugin._
import AssemblyKeys._

object HyperScalaBuild extends Build {
  val powerScalaVersion = "1.6.2"
  val powerScalaReflect = "org.powerscala" %% "powerscala-reflect" % powerScalaVersion
  val powerScalaHierarchy = "org.powerscala" %% "powerscala-hierarchy" % powerScalaVersion
  val powerScalaProperty = "org.powerscala" %% "powerscala-property" % powerScalaVersion
  val jdom = "org.jdom" % "jdom2" % "2.0.4"
  val jaxen = "jaxen" % "jaxen" % "1.1.4"

  val htmlcleaner = "net.sourceforge.htmlcleaner" % "htmlcleaner" % "2.2"
  val akkaActors = "com.typesafe.akka" % "akka-actor_2.10" % "2.1.2"
  val uaDetector = "net.sf.uadetector" % "uadetector-resources" % "2013.09"

  val scalaTest = "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test"

  val webcommunicator = "com.outr.webcommunicator" %% "webcommunicator" % "1.0.5"

  val baseSettings = Defaults.defaultSettings ++ Seq(
    version := "0.8.2",
    organization := "org.hyperscala",
    scalaVersion := "2.10.3",
    libraryDependencies ++= Seq(
      powerScalaReflect,
      powerScalaHierarchy,
      powerScalaProperty,
      jdom,
      jaxen,
      htmlcleaner,
      akkaActors,
      scalaTest
    ),
    scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature"),
    resolvers ++= Seq("Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
                      "twitter-repo" at "http://maven.twttr.com",
                      "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"),
    publishTo <<= version {
      (v: String) =>
        val nexus = "https://oss.sonatype.org/"
        if (v.trim.endsWith("SNAPSHOT"))
          Some("snapshots" at nexus + "content/repositories/snapshots")
        else
          Some("releases" at nexus + "service/local/staging/deploy/maven2")
    },
    publishArtifact in Test := false,
    pomExtra := <url>http://hyperscala.org</url>
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
      </developers>
  )

  private def createSettings(_name: String) = baseSettings ++ assemblySettings ++ Seq(name := _name)

  lazy val root = Project("root", file("."), settings = unidocSettings ++ createSettings("hyperscala-root"))
    .settings(publishArtifact in Compile := false, publishArtifact in Test := false)
    .aggregate(core, html, javascript, svg, web, jquery, realtime, ui, generator, examples, hello, numberGuess, site)
  lazy val core = Project("core", file("core"), settings = createSettings("hyperscala-core"))
  lazy val html = Project("html", file("html"), settings = createSettings("hyperscala-html"))
    .dependsOn(core)
  lazy val svg = Project("svg", file("svg"), settings = createSettings("hyperscala-svg"))
    .dependsOn(html)
  lazy val javascript = Project("javascript", file("javascript"), settings = createSettings("hyperscala-javascript"))
    .dependsOn(html)
  lazy val web = Project("web", file("web"), settings = createSettings("hyperscala-web"))
    .dependsOn(html, javascript, svg)
    .settings(libraryDependencies ++= Seq(webcommunicator, uaDetector))
    .settings(libraryDependencies <+= scalaVersion { "org.scala-lang" % "scala-swing" % })
  lazy val jquery = Project("jquery", file("jquery"), settings = createSettings("hyperscala-jquery"))
    .dependsOn(web)
  lazy val realtime = Project("realtime", file("realtime"), settings = createSettings("hyperscala-realtime"))
    .dependsOn(web, jquery)
  lazy val ui = Project("ui", file("ui"), settings = createSettings("hyperscala-ui"))
    .dependsOn(web, realtime, jquery)
  lazy val generator = Project("generator", file("generator"), settings = createSettings("hyperscala-generator"))
    .settings(publishArtifact := false)
  // Examples and Site
  lazy val examples = Project("examples", file("examples"), settings = createSettings("hyperscala-examples"))
    .dependsOn(web, ui)
  lazy val hello = Project("hello", file("hello"), settings = createSettings("hyperscala-hello"))
    .dependsOn(web)
  lazy val numberGuess = Project("numberguess", file("numberguess"), settings = createSettings("hyperscala-numberguess"))
    .dependsOn(ui)
  lazy val site = Project("site", file("site"), settings = createSettings("hyperscala-site") ++ Revolver.settings)
    .settings(jarName in assembly := s"hyperscala-${version.value}.jar", mergeStrategy in assembly <<= (mergeStrategy in assembly) {
      case old => {
        case PathList("META-INF", "jdom-info.xml") => MergeStrategy.first
        case x => old(x)
      }
    }, mainClass in assembly := Some("org.hyperscala.site.HyperscalaSite"))
    .settings(mainClass := Some("org.hyperscala.site.HyperscalaSite"))
    .dependsOn(examples)
}