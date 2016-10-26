name := "youi"
organization in ThisBuild := "com.outr"
version in ThisBuild := "1.0.0-SNAPSHOT"
scalaVersion in ThisBuild := "2.11.8"
sbtVersion in ThisBuild := "0.13.11"

lazy val root = project.in(file("."))
  .aggregate(coreJS, coreJVM, communicateJS, communicateJVM, domJS, domJVM, server, ui)
  .settings(
    publish := {},
    publishLocal := {}
  )

lazy val core = crossProject.in(file("core"))
  .settings(
    name := "core",
    libraryDependencies += "com.outr.scribe" %%% "scribe" % "1.2.5",
    libraryDependencies += "org.scalactic" %%% "scalactic" % "3.0.0",
    libraryDependencies += "org.scalatest" %%% "scalatest" % "3.0.0" % "test"
  )

lazy val coreJS = core.js
lazy val coreJVM = core.jvm

lazy val communicate = crossProject.in(file("communicate"))
  .settings(
    name := "communicate"
  )
  .dependsOn(core)

lazy val communicateJS = core.js
lazy val communicateJVM = core.jvm

lazy val dom = crossProject.in(file("dom"))
  .settings(
    name := "dom"
  )
  .dependsOn(core)

lazy val domJS = dom.js
lazy val domJVM = dom.jvm

lazy val server = project.in(file("server"))
  .settings(
    name := "server"
  )
  .dependsOn(coreJVM)

lazy val ui = project.in(file("ui"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "ui"
  )
  .dependsOn(coreJS)