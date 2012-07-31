import sbt._
import Keys._

import com.github.siasia.WebPlugin._
import com.github.siasia.PluginKeys._

object HyperScalaBuild extends Build {
  // ~;container:start; container:reload /

  val powerScalaConvert = "org.powerscala" %% "powerscala-convert" % "1.2-SNAPSHOT"
  val powerScalaReflect = "org.powerscala" %% "powerscala-reflect" % "1.2-SNAPSHOT"
  val powerScalaHierarchy = "org.powerscala" %% "powerscala-hierarchy" % "1.2-SNAPSHOT"
  val powerScalaProperty = "org.powerscala" %% "powerscala-property" % "1.2-SNAPSHOT"
  val jdom = "org.jdom" % "jdom" % "2.0.2"

  val htmlcleaner = "net.sourceforge.htmlcleaner" % "htmlcleaner" % "2.2"

  val specs2 = "org.specs2" %% "specs2" % "1.11" % "test"

  val jettyVersion = "7.4.2.v20110526"
  val jettyServer = "org.eclipse.jetty" % "jetty-server" % jettyVersion % "container;provided"
  val jettyWebapp = "org.eclipse.jetty" % "jetty-webapp" % jettyVersion % "container;provided"
  val jettyServlet = "org.eclipse.jetty" % "jetty-servlet" % jettyVersion % "container;provided"
  val jettyJsp = "org.eclipse.jetty" % "jetty-jsp-2.1" % jettyVersion % "container;provided"
  val glassfishJsp = "org.mortbay.jetty" % "jsp-2.1-glassfish" % "2.1.v20100127" % "container;provided"

  val servletApi = "org.eclipse.jetty" % "jetty-servlet" % jettyVersion

  val baseSettings = Defaults.defaultSettings ++ Seq(
    version := "0.3-SNAPSHOT",
    organization := "org.hyperscala",
    scalaVersion := "2.9.2",
    libraryDependencies ++= Seq(
      powerScalaConvert,
      powerScalaReflect,
      powerScalaHierarchy,
      powerScalaProperty,
      jdom,
      htmlcleaner,
      specs2
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
    .aggregate(core, html, javascript, examples, site)
  lazy val core = Project("core", file("core"), settings = createSettings("hyperscala-core"))
  lazy val html = Project("html", file("html"), settings = createSettings("hyperscala-html"))
    .dependsOn(core)
  lazy val javascript = Project("javascript", file("javascript"), settings = createSettings("hyperscala-javascript"))
    .dependsOn(html)
  lazy val web = Project("web", file("web"), settings = createSettings("hyperscala-web"))
    .dependsOn(html)
    .settings(libraryDependencies += servletApi)
  lazy val generator = Project("generator", file("generator"), settings = createSettings("hyperscala-generator"))
    .settings(publishArtifact := false)

  // Examples and Site
  lazy val examples = Project("examples", file("examples"), settings = createSettings("hyperscala-examples"))
    .dependsOn(web)
  lazy val site = Project("site", file("site"), settings = createSettings("hyperscala-site"))
    .dependsOn(examples)
    .settings(webSettings: _*)
    .settings(port := 8080)
    .settings(libraryDependencies ++= Seq(jettyServer, jettyWebapp, jettyServlet, jettyJsp, glassfishJsp))
}