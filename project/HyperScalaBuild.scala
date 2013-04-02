import sbt._
import Keys._

import spray.revolver.RevolverPlugin._

import sbtassembly.Plugin._
import AssemblyKeys._

object HyperScalaBuild extends Build {
  val powerScalaVersion = "1.5.2-SNAPSHOT"
  val powerScalaConvert = "org.powerscala" %% "powerscala-convert" % powerScalaVersion
  val powerScalaReflect = "org.powerscala" %% "powerscala-reflect" % powerScalaVersion
  val powerScalaHierarchy = "org.powerscala" %% "powerscala-hierarchy" % powerScalaVersion
  val powerScalaProperty = "org.powerscala" %% "powerscala-property" % powerScalaVersion
  val jdom = "org.jdom" % "jdom" % "2.0.2"

  val htmlcleaner = "net.sourceforge.htmlcleaner" % "htmlcleaner" % "2.2"
  val akkaActors = "com.typesafe.akka" % "akka-actor_2.10" % "2.1.2"

  val specs2 = "org.specs2" %% "specs2" % "1.11" % "test"

  val webcommunicator = "com.outr.webcommunicator" %% "webcommunicator" % "1.0.3-SNAPSHOT"

  val baseSettings = Defaults.defaultSettings ++ Seq(
    version := "0.7.2-SNAPSHOT",
    organization := "org.hyperscala",
    scalaVersion := "2.10.1",
    libraryDependencies ++= Seq(
      powerScalaConvert,
      powerScalaReflect,
      powerScalaHierarchy,
      powerScalaProperty,
      jdom,
      htmlcleaner,
      akkaActors,
      specs2
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

  private def createSettings(_name: String) = baseSettings ++ assemblySettings ++ Revolver.settings ++ Seq(name := _name)

  lazy val root = Project("root", file("."), settings = createSettings("hyperscala-root"))
    .settings(publishArtifact in Compile := false, publishArtifact in Test := false)
    .aggregate(core, html, javascript, svg, web, jquery, realtime, ui, generator, examples, site)
  lazy val core = Project("core", file("core"), settings = createSettings("hyperscala-core"))
  lazy val html = Project("html", file("html"), settings = createSettings("hyperscala-html"))
    .dependsOn(core)
  lazy val svg = Project("svg", file("svg"), settings = createSettings("hyperscala-svg"))
    .dependsOn(html)
  lazy val javascript = Project("javascript", file("javascript"), settings = createSettings("hyperscala-javascript"))
    .dependsOn(html)
  lazy val web = Project("web", file("web"), settings = createSettings("hyperscala-web"))
    .dependsOn(html, svg)
    .settings(libraryDependencies ++= Seq(webcommunicator))
    .settings(libraryDependencies <+= scalaVersion { "org.scala-lang" % "scala-swing" % _ })
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
  lazy val site = Project("site", file("site"), settings = createSettings("hyperscala-site"))
    .settings(jarName in assembly <<= version {
      (v: String) => "hyperscala-%s.jar".format(v)
    }, mainClass in assembly := Some("org.hyperscala.site.HyperscalaSite"))
    .settings(mainClass := Some("org.hyperscala.site.HyperscalaSite"))
    .dependsOn(examples)
}