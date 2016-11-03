name := "youi"
organization in ThisBuild := "com.outr"
version in ThisBuild := "1.0.0-SNAPSHOT"
scalaVersion in ThisBuild := "2.11.8"
sbtVersion in ThisBuild := "0.13.11"

lazy val root = project.in(file("."))
  .aggregate(coreJS, coreJVM, communicateJS, communicateJVM, domJS, domJVM, server, serverUndertow, ui)
  .settings(
    resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases",
    publish := {},
    publishLocal := {}
  )

lazy val core = crossProject.in(file("core"))
  .settings(
    name := "core",
    resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases",
    libraryDependencies += "com.outr.scribe" %%% "scribe" % "1.2.5",
    libraryDependencies += "com.outr" %%% "metarx" % "0.1.8-cyclical",
    libraryDependencies += "org.scalactic" %%% "scalactic" % "3.0.0",
    libraryDependencies += "org.scalatest" %%% "scalatest" % "3.0.0" % "test"
  )

lazy val coreJS = core.js
lazy val coreJVM = core.jvm

lazy val communicate = crossProject.in(file("communicate"))
  .settings(
    name := "communicate",
    libraryDependencies += "com.lihaoyi" %% "upickle" % "0.4.3",
    libraryDependencies += "org.scalactic" %%% "scalactic" % "3.0.0",
    libraryDependencies += "org.scalatest" %%% "scalatest" % "3.0.0" % "test"
  )
  .dependsOn(core)

lazy val communicateJS = communicate.js
lazy val communicateJVM = communicate.jvm

lazy val dom = crossProject.in(file("dom"))
  .settings(
    name := "dom"
  )
  .dependsOn(core)

lazy val domJS = dom.js
lazy val domJVM = dom.jvm

lazy val server = project.in(file("server"))
  .settings(
    name := "server",
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.0",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test"
  )
  .dependsOn(coreJVM)

lazy val serverUndertow = project.in(file("server-undertow"))
  .settings(
    name := "server-undertow",
    libraryDependencies += "io.undertow" % "undertow-core" % "1.4.4.Final",
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.0",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test"
  )
  .dependsOn(server)

lazy val ui = project.in(file("ui"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "ui"
  )
  .dependsOn(coreJS)