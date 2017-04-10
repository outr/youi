name := "youi"
organization in ThisBuild := "io.youi"
version in ThisBuild := "0.3.0-SNAPSHOT"
scalaVersion in ThisBuild := "2.12.1"
crossScalaVersions in ThisBuild := List("2.12.1", "2.11.8")
sbtVersion in ThisBuild := "0.13.13"
resolvers in ThisBuild += Resolver.sonatypeRepo("releases")
resolvers in ThisBuild += Resolver.sonatypeRepo("snapshots")
scalacOptions in ThisBuild ++= Seq("-unchecked", "-deprecation")

val pixiJsVersion = "4.4.4-SNAPSHOT"
val scribeVersion = "1.4.2"
val powerScalaVersion = "2.0.5"
val reactifyVersion = "1.4.8"
val akkaVersion = "2.4.17"
val scalaJSDOM = "0.9.1"
val httpAsyncClientVersion = "4.1.3"
val circeVersion = "0.7.0"
val uaDetectorVersion = "2014.10"
val undertowVersion = "1.4.12.Final"
val uPickleVersion = "0.4.4"

lazy val root = project.in(file("."))
  .aggregate(
    coreJS, coreJVM, stream, communicationJS, communicationJVM, dom, client, server, serverUndertow, ui, optimizer,
    appJS, appJVM, templateJS, templateJVM, exampleJS, exampleJVM
  )
  .settings(
    resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases",
    publish := {},
    publishLocal := {}
  )

lazy val core = crossProject.in(file("core"))
  .settings(
    name := "youi-core",
    description := "Core functionality leveraged and shared by most other sub-projects of YouI.",
    resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases",
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-reflect" % scalaVersion.value,
      "com.outr" %%% "scribe" % scribeVersion,
      "com.outr" %%% "reactify" % reactifyVersion,
      "org.scalactic" %%% "scalactic" % "3.0.1",
      "org.scalatest" %%% "scalatest" % "3.0.1" % "test"
    )
  )
  .jvmSettings(
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor" % akkaVersion
    )
  )
  .jsSettings(
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % scalaJSDOM
    )
  )

lazy val coreJS = core.js
lazy val coreJVM = core.jvm

lazy val stream = project.in(file("stream"))
  .settings(
    name := "youi-stream",
    libraryDependencies ++= Seq(
      "org.powerscala" %% "powerscala-io" % powerScalaVersion
    )
  )

lazy val dom = project.in(file("dom"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "youi-dom"
  )
  .dependsOn(coreJS)
  .dependsOn(stream % "compile")

lazy val client = project.in(file("client"))
  .settings(
    name := "youi-client",
    libraryDependencies ++= Seq(
      "org.apache.httpcomponents" % "httpasyncclient" % httpAsyncClientVersion,
      "org.powerscala" %% "powerscala-io" % powerScalaVersion
    ),
    libraryDependencies ++= Seq(
      "io.circe" %% "circe-core",
      "io.circe" %% "circe-generic",
      "io.circe" %% "circe-parser"
    ).map(_ % circeVersion)
  )
  .dependsOn(coreJVM)

lazy val server = project.in(file("server"))
  .settings(
    name := "youi-server",
    libraryDependencies ++= Seq(
      "net.sf.uadetector" % "uadetector-resources" % uaDetectorVersion,
      "org.scalactic" %% "scalactic" % "3.0.1",
      "org.scalatest" %% "scalatest" % "3.0.1" % "test"
    )
  )
  .dependsOn(coreJVM, stream)

lazy val serverUndertow = project.in(file("serverUndertow"))
  .settings(
    name := "youi-server-undertow",
    libraryDependencies ++= Seq(
      "io.undertow" % "undertow-core" % undertowVersion,
      "org.scalactic" %% "scalactic" % "3.0.1",
      "org.scalatest" %% "scalatest" % "3.0.1" % "test"
    )
  )
  .dependsOn(server)

lazy val communication = crossProject.in(file("communication"))
  .settings(
    name := "youi-communication",
    libraryDependencies ++= Seq(
      "com.lihaoyi" %%% "upickle" % uPickleVersion,
      "org.scalactic" %%% "scalactic" % "3.0.1",
      "org.scalatest" %%% "scalatest" % "3.0.1" % "test"
    )
  )
  .dependsOn(core)

lazy val communicationJS = communication.js
lazy val communicationJVM = communication.jvm.dependsOn(server)

lazy val ui = project.in(file("ui"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "youi-ui",
    libraryDependencies ++= Seq(
      "com.outr" %%% "scalajs-pixijs" % pixiJsVersion
    )
  )
  .dependsOn(coreJS, dom)

lazy val optimizer = project.in(file("optimizer"))
  .settings(
    name := "youi-optimizer",
    description := "Provides optimization functionality for application development.",
    fork := true,
    libraryDependencies ++= Seq(
      "com.google.javascript" % "closure-compiler" % "v20170218",
      "org.powerscala" %% "powerscala-io" % powerScalaVersion,
      "com.outr" %% "scribe" % scribeVersion,
      "com.outr" %% "hasher" % "1.2.1"
    )
  )
  .dependsOn(stream)

lazy val app = crossProject.in(file("app"))
  .settings(
    name := "youi-app"
  )
  .dependsOn(core, communication)

lazy val appJS = app.js.dependsOn(ui)
lazy val appJVM = app.jvm

lazy val template = crossProject.in(file("template"))
  .settings(
    name := "youi-template"
  )
  .jsSettings(
    crossTarget in fastOptJS := baseDirectory.value / ".." / "jvm" / "src" / "main" / "resources" / "app",
    crossTarget in fullOptJS := baseDirectory.value / ".." / "jvm" / "src" / "main" / "resources" / "app"
  )
  .jvmSettings(
    fork := true,
    libraryDependencies ++= Seq(
      "org.powerscala" %% "powerscala-io" % powerScalaVersion
    ),
    assemblyJarName in assembly := "youi-template.jar"
  )
  .dependsOn(app)

lazy val templateJS = template.js.dependsOn(ui)
lazy val templateJVM = template.jvm.dependsOn(serverUndertow, optimizer)

lazy val example = crossProject.in(file("example"))
  .settings(
    name := "youi-server-example"
  )
  .jsSettings(
    crossTarget in fastOptJS := baseDirectory.value / ".." / "jvm" / "src" / "main" / "resources" / "app",
    crossTarget in fullOptJS := baseDirectory.value / ".." / "jvm" / "src" / "main" / "resources" / "app"
  )
  .jvmSettings(
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-reflect" % scalaVersion.value,
      "org.scala-lang.modules" %% "scala-xml" % "1.0.6"
    )
  )
  .dependsOn(app, template)

lazy val exampleJS = example.js.dependsOn(ui)
lazy val exampleJVM = example.jvm.dependsOn(serverUndertow)