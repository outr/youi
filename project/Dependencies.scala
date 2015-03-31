import sbt._

object Dependencies {
  val powerScalaVersion = "1.6.9-SNAPSHOT"
  val outrNetVersion = "1.1.5-SNAPSHOT"

  val powerScalaReflect = "org.powerscala" %% "powerscala-reflect" % powerScalaVersion
  val powerScalaHierarchy = "org.powerscala" %% "powerscala-hierarchy" % powerScalaVersion
  val powerScalaProperty = "org.powerscala" %% "powerscala-property" % powerScalaVersion
  val powerScalaJson = "org.powerscala" %% "powerscala-json" % powerScalaVersion

  val outrNetCore = "com.outr.net" %% "outrnet-core" % outrNetVersion
  val outrNetServlet = "com.outr.net" %% "outrnet-servlet" % outrNetVersion
  val outrNetService = "com.outr.net" %% "outrnet-service" % outrNetVersion
  val outrNetCommunicate = "com.outr.net" %% "outrnet-communicate" % outrNetVersion
  val outrNetJetty = "com.outr.net" %% "outrnet-jetty" % outrNetVersion

  val commonsCodec = "commons-codec" % "commons-codec" % "1.10"

  val jdom = "org.jdom" % "jdom2" % "2.0.6"
  val jaxen = "jaxen" % "jaxen" % "1.1.6"

  val htmlcleaner = "net.sourceforge.htmlcleaner" % "htmlcleaner" % "2.10"
  val akkaActors = "com.typesafe.akka" %% "akka-actor" % "2.3.9"
  val uaDetector = "net.sf.uadetector" % "uadetector-resources" % "2014.10"

  val githubCore = "org.eclipse.mylyn.github" % "org.eclipse.egit.github.core" % "2.1.5"

  val scalaTest = "org.scalatest" %% "scalatest" % "2.2.4" % "test"
}
