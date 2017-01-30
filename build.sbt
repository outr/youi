name := "youi"
organization in ThisBuild := "io.youi"
version in ThisBuild := "0.2.0-SNAPSHOT"
scalaVersion in ThisBuild := "2.12.1"
crossScalaVersions in ThisBuild := List("2.12.1", "2.11.8")
sbtVersion in ThisBuild := "0.13.13"
resolvers in ThisBuild += Resolver.sonatypeRepo("releases")
scalacOptions in ThisBuild ++= Seq("-unchecked", "-deprecation")

lazy val root = project.in(file("."))
  .aggregate(coreJS, coreJVM, stream, communicationJS, communicationJVM, dom, server, serverUndertow, ui, appJS, appJVM, exampleJS, exampleJVM)
  .settings(
    resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases",
    publish := {},
    publishLocal := {}
  )

lazy val core = crossProject.in(file("core"))
  .settings(
    name := "youi-core",
    resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases",
    libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value,
    libraryDependencies += "com.outr" %%% "scribe" % "1.3.2",
    libraryDependencies += "com.outr" %%% "reactify" % "1.3.7",
    libraryDependencies += "org.scalactic" %%% "scalactic" % "3.0.1",
    libraryDependencies += "org.scalatest" %%% "scalatest" % "3.0.1" % "test"
  )
  .jvmSettings(
    libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.4.16"
  )
  .jsSettings(
    libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.1"
  )

lazy val coreJS = core.js
lazy val coreJVM = core.jvm

lazy val stream = project.in(file("stream"))
  .settings(
    name := "youi-stream"
  )

lazy val dom = project.in(file("dom"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "youi-dom"
  )
  .dependsOn(coreJS)
  .dependsOn(stream % "compile")

lazy val server = project.in(file("server"))
  .settings(
    name := "youi-server",
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"
  )
  .dependsOn(coreJVM)

lazy val serverUndertow = project.in(file("serverUndertow"))
  .settings(
    name := "youi-server-undertow",
    libraryDependencies += "io.undertow" % "undertow-core" % "1.4.8.Final",
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"
  )
  .dependsOn(server)

lazy val communication = crossProject.in(file("communication"))
  .settings(
    name := "youi-communication",
    libraryDependencies += "com.lihaoyi" %%% "upickle" % "0.4.4",
    libraryDependencies += "org.scalactic" %%% "scalactic" % "3.0.1",
    libraryDependencies += "org.scalatest" %%% "scalatest" % "3.0.1" % "test"
  )
  .dependsOn(core)

lazy val communicationJS = communication.js
lazy val communicationJVM = communication.jvm.dependsOn(server)

lazy val app = crossProject.in(file("app"))
  .settings(
    name := "youi-app"
  )
  .dependsOn(core, communication)

lazy val appJS = app.js.dependsOn(dom)
lazy val appJVM = app.jvm.dependsOn(stream, server)

lazy val ui = project.in(file("ui"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "youi-ui"
  )
  .dependsOn(coreJS, dom)

lazy val example = crossProject.in(file("example"))
  .settings(
    name := "youi-server-example"
  )
  .jsSettings(
    crossTarget in fastOptJS := baseDirectory.value / ".." / "jvm" / "src" / "main" / "resources" / "app",
    crossTarget in fullOptJS := baseDirectory.value / ".." / "jvm" / "src" / "main" / "resources" / "app"
  )
  .jvmSettings(
    libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value,
    libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.0.6"
  )
  .dependsOn(communication)

lazy val exampleJS = example.js.dependsOn(dom, ui)
lazy val exampleJVM = example.jvm.dependsOn(serverUndertow)