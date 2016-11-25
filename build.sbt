name := "youi"
organization in ThisBuild := "com.outr"
version in ThisBuild := "1.0.0-SNAPSHOT"
scalaVersion in ThisBuild := "2.12.0"
crossScalaVersions in ThisBuild := List("2.12.0", "2.11.8")
sbtVersion in ThisBuild := "0.13.13"
resolvers in ThisBuild += Resolver.sonatypeRepo("releases")
scalacOptions in ThisBuild ++= Seq("-unchecked", "-deprecation")

lazy val root = project.in(file("."))
  .aggregate(coreJS, coreJVM, communicateJS, communicateJVM, domJS, domJVM, server, serverUndertow, ui, exampleJS, exampleJVM)
  .settings(
    resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases",
    publish := {},
    publishLocal := {}
  )

lazy val core = crossProject.in(file("core"))
  .settings(
    name := "core",
    resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases",
    libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value,
    libraryDependencies += "com.outr" %%% "scribe" % "1.2.6",
    libraryDependencies += "com.outr" %%% "props" % "1.0.0",
    libraryDependencies += "org.scalactic" %%% "scalactic" % "3.0.0",
    libraryDependencies += "org.scalatest" %%% "scalatest" % "3.0.0" % "test"
  )
  .jvmSettings(
    libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.4.13"
  )
  .jsSettings(
    libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.1"
  )

lazy val coreJS = core.js
lazy val coreJVM = core.jvm

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

lazy val serverUndertow = project.in(file("serverUndertow"))
  .settings(
    name := "server-undertow",
    libraryDependencies += "io.undertow" % "undertow-core" % "1.4.4.Final",
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.0",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test"
  )
  .dependsOn(server)

lazy val communicate = crossProject.in(file("communicate"))
  .settings(
    name := "communicate",
    libraryDependencies += "com.lihaoyi" %% "upickle" % "0.4.4",
    libraryDependencies += "org.scalactic" %%% "scalactic" % "3.0.0",
    libraryDependencies += "org.scalatest" %%% "scalatest" % "3.0.0" % "test"
  )
  .dependsOn(core)

lazy val communicateJS = communicate.js
lazy val communicateJVM = communicate.jvm.dependsOn(server)

lazy val example = crossProject.in(file("example"))
  .settings(
    name := "server-example"
  )
  .jvmSettings(
    libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value,
    libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.0.6"
  )
  .dependsOn(communicate)

lazy val exampleJS = example.js
lazy val exampleJVM = example.jvm.dependsOn(serverUndertow)

lazy val ui = project.in(file("ui"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "ui"
  )
  .dependsOn(coreJS)