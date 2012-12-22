import sbt._
import Keys._

object HyperScalaBuild extends Build {
  // ~;container:start; container:reload /

  val powerScalaConvert = "org.powerscala" %% "powerscala-convert" % "1.4-SNAPSHOT"
  val powerScalaReflect = "org.powerscala" %% "powerscala-reflect" % "1.4-SNAPSHOT"
  val powerScalaHierarchy = "org.powerscala" %% "powerscala-hierarchy" % "1.4-SNAPSHOT"
  val powerScalaProperty = "org.powerscala" %% "powerscala-property" % "1.4-SNAPSHOT"
  val jdom = "org.jdom" % "jdom" % "2.0.2"

  val htmlcleaner = "net.sourceforge.htmlcleaner" % "htmlcleaner" % "2.2"

  val specs2 = "org.specs2" %% "specs2" % "1.11" % "test"

  val webcommunicator = "com.outr.webcommunicator" %% "webcommunicator" % "1.0-SNAPSHOT"

  val baseSettings = Defaults.defaultSettings ++ Seq(
    version := "0.6-SNAPSHOT",
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
    .aggregate(core, html, javascript, web, ui, generator, examples, site)
  lazy val core = Project("core", file("core"), settings = createSettings("hyperscala-core"))
  lazy val html = Project("html", file("html"), settings = createSettings("hyperscala-html"))
    .dependsOn(core)
  lazy val javascript = Project("javascript", file("javascript"), settings = createSettings("hyperscala-javascript"))
    .dependsOn(html)
  lazy val web = Project("web", file("web"), settings = createSettings("hyperscala-web"))
    .dependsOn(html)
    .settings(libraryDependencies ++= Seq(webcommunicator))
    .settings(libraryDependencies <+= scalaVersion { "org.scala-lang" % "scala-swing" % _ })
  lazy val ui = Project("ui", file("ui"), settings = createSettings("hyperscala-ui"))
    .dependsOn(web)
  lazy val generator = Project("generator", file("generator"), settings = createSettings("hyperscala-generator"))
    .settings(publishArtifact := false)

  // Examples and Site
  lazy val examples = Project("examples", file("examples"), settings = createSettings("hyperscala-examples"))
    .dependsOn(web, ui)
  lazy val site = Project("site", file("site"), settings = createSettings("hyperscala-site"))
    .dependsOn(examples)
}