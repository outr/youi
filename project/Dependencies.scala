import sbt._

object Dependencies {
  val powerScalaVersion = "latest.integration"
  val outrNetVersion = "latest.integration"

  val powerScalaReflect = "org.powerscala" %% "powerscala-reflect" % powerScalaVersion
  val powerScalaHierarchy = "org.powerscala" %% "powerscala-hierarchy" % powerScalaVersion
  val powerScalaProperty = "org.powerscala" %% "powerscala-property" % powerScalaVersion
  val powerScalaJson = "org.powerscala" %% "powerscala-json" % powerScalaVersion

  val webJarsJQuery = "org.webjars" % "jquery" % "1.10.2-1"

  val outrNetCore = "com.outr.net" %% "outrnet-core" % outrNetVersion
  val outrNetServlet = "com.outr.net" %% "outrnet-servlet" % outrNetVersion
  val outrNetJetty = "com.outr.net" %% "outrnet-jetty" % outrNetVersion

  val scalaSwing = "org.scala-lang.modules" %% "scala-swing" % "latest.release"

//  val launcher = "com.outr.launcher" %% "launcher" % "1.0.0-SNAPSHOT"

//  val servlet = "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016"
  val jettyWebapp = "org.eclipse.jetty" % "jetty-webapp" % "latest.release" % "container"

  val jdom = "org.jdom" % "jdom2" % "latest.release"
  val jaxen = "jaxen" % "jaxen" % "latest.release"

  val htmlcleaner = "net.sourceforge.htmlcleaner" % "htmlcleaner" % "latest.release"
  val akkaActors = "com.typesafe.akka" %% "akka-actor" % "latest.release"
  val uaDetector = "net.sf.uadetector" % "uadetector-resources" % "latest.release"

  val githubCore = "org.eclipse.mylyn.github" % "org.eclipse.egit.github.core" % "2.1.5"
  val argonaut = "io.argonaut" %% "argonaut" % "latest.release"

  val scalaTest = "org.scalatest" %% "scalatest" % "latest.release" % "test"
}
