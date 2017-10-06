name := "youi"
organization in ThisBuild := "io.youi"
version in ThisBuild := "0.8.0-SNAPSHOT"
scalaVersion in ThisBuild := "2.12.3"
crossScalaVersions in ThisBuild := List("2.12.3", "2.11.11")
resolvers in ThisBuild += Resolver.sonatypeRepo("releases")
resolvers in ThisBuild += Resolver.sonatypeRepo("snapshots")
scalacOptions in ThisBuild ++= Seq("-unchecked", "-deprecation")

val profigVersion = "1.1.2"
val scribeVersion = "1.4.5"
val powerScalaVersion = "2.0.5"
val reactifyVersion = "2.2.0"
val hasherVersion = "1.2.1"
val canvgVersion = "1.4.0_1"
val openTypeVersion = "0.7.3"
val picaVersion = "3.0.5"

val akkaVersion = "2.5.4"
val scalaJSDOM = "0.9.3"
val httpAsyncClientVersion = "4.1.3"
val httpMimeVersion = "4.5.3"
val circeVersion = "0.8.0"
val uaDetectorVersion = "2014.10"
val undertowVersion = "1.4.20.Final"
val closureCompilerVersion = "v20170806"
val jSoupVersion = "1.10.3"
val scalaXMLVersion = "1.0.6"
val scalacticVersion = "3.0.4"
val scalaTestVersion = "3.0.4"
val scalaCheckVersion = "1.13.5"

lazy val root = project.in(file("."))
  .aggregate(
    macrosJS, macrosJVM, coreJS, coreJVM, spatialJS, spatialJVM, stream, communicationJS, communicationJVM, dom, client,
    server, serverUndertow, uiJS, uiJVM, hypertext, optimizer, appJS, appJVM, templateJS,
    templateJVM, exampleJS, exampleJVM
  )
  .settings(
    resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases",
    publish := {},
    publishLocal := {}
  )

lazy val macros = crossProject.in(file("macros"))
  .settings(
    name := "youi-macros",
    description := "Dependency for internal Macro functionality",
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-reflect" % scalaVersion.value,
      "org.scalactic" %%% "scalactic" % scalacticVersion,
      "org.scalatest" %%% "scalatest" % scalaTestVersion % "test"
    )
  )

lazy val macrosJS = macros.js
lazy val macrosJVM = macros.jvm

lazy val core = crossProject.in(file("core"))
  .settings(
    name := "youi-core",
    description := "Core functionality leveraged and shared by most other sub-projects of YouI.",
    resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases",
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-reflect" % scalaVersion.value,
      "com.outr" %%% "profig" % profigVersion,
      "com.outr" %%% "scribe" % scribeVersion,
      "com.outr" %%% "reactify" % reactifyVersion,
      "org.scalactic" %%% "scalactic" % scalacticVersion,
      "org.scalatest" %%% "scalatest" % scalaTestVersion % "test"
    ),
    libraryDependencies ++= Seq(
      "io.circe" %%% "circe-core",
      "io.circe" %%% "circe-generic",
      "io.circe" %%% "circe-parser"
    ).map(_ % circeVersion)
  )
  .jvmSettings(
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor" % akkaVersion,
      "org.powerscala" %% "powerscala-io" % powerScalaVersion
    )
  )
  .jsSettings(
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % scalaJSDOM
    )
  )
  .dependsOn(macros)

lazy val coreJS = core.js
lazy val coreJVM = core.jvm

lazy val spatial = crossProject.in(file("spatial"))
  .settings(
    name := "youi-spatial",
    libraryDependencies ++= Seq(
      "org.scalactic" %%% "scalactic" % scalacticVersion,
      "org.scalatest" %%% "scalatest" % scalaTestVersion % "test",
      "org.scalacheck" %%% "scalacheck" % scalaCheckVersion % "test"
    )
  )
  .jsSettings(
    jsDependencies += RuntimeDOM
  )
  .dependsOn(core)

lazy val spatialJS = spatial.js
lazy val spatialJVM = spatial.jvm

lazy val stream = project.in(file("stream"))
  .settings(
    name := "youi-stream",
    libraryDependencies ++= Seq(
      "org.powerscala" %% "powerscala-io" % powerScalaVersion
    )
  )
  .dependsOn(coreJVM)

lazy val dom = project.in(file("dom"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "youi-dom",
    libraryDependencies += "com.outr" %% "profig" % profigVersion,
    jsDependencies += RuntimeDOM
  )
  .dependsOn(coreJS)
  .dependsOn(stream % "compile")

lazy val client = project.in(file("client"))
  .settings(
    name := "youi-client",
    libraryDependencies ++= Seq(
      "org.apache.httpcomponents" % "httpasyncclient" % httpAsyncClientVersion,
      "org.apache.httpcomponents" % "httpmime" % httpMimeVersion,
      "org.powerscala" %% "powerscala-io" % powerScalaVersion,
      "org.scalactic" %%% "scalactic" % scalacticVersion,
      "org.scalatest" %%% "scalatest" % scalaTestVersion % "test"
    )
  )
  .dependsOn(coreJVM)

lazy val server = project.in(file("server"))
  .settings(
    name := "youi-server",
    libraryDependencies ++= Seq(
      "net.sf.uadetector" % "uadetector-resources" % uaDetectorVersion,
      "org.scalactic" %% "scalactic" % scalacticVersion,
      "org.scalatest" %% "scalatest" % scalaTestVersion % "test"
    )
  )
  .dependsOn(coreJVM, stream)

lazy val serverUndertow = project.in(file("serverUndertow"))
  .settings(
    name := "youi-server-undertow",
    libraryDependencies ++= Seq(
      "io.undertow" % "undertow-core" % undertowVersion,
      "org.scalactic" %% "scalactic" % scalacticVersion,
      "org.scalatest" %% "scalatest" % scalaTestVersion % "test"
    )
  )
  .dependsOn(server)

lazy val communication = crossProject.in(file("communication"))
  .settings(
    name := "youi-communication",
    libraryDependencies ++= Seq(
      "org.scalactic" %%% "scalactic" % scalacticVersion,
      "org.scalatest" %%% "scalatest" % scalaTestVersion % "test"
    )
  )
  .dependsOn(core)

lazy val communicationJS = communication.js
lazy val communicationJVM = communication.jvm.dependsOn(server)

//lazy val canvas = crossProject.in(file("canvas"))
//  .settings(
//    name := "youi-canvas"
//  )
//  .jsSettings(
//    libraryDependencies ++= Seq(
//      "com.outr" %%% "canvg-scala-js" % canvgVersion,
//      "com.outr" %%% "opentype-scala-js" % openTypeVersion,
//      "com.outr" %%% "pica-scala-js" % picaVersion
//    ),
//    jsDependencies += RuntimeDOM
//  )
//  .dependsOn(spatial)
//
//lazy val canvasJS = canvas.js.dependsOn(dom)
//lazy val canvasJVM = canvas.jvm

lazy val ui = crossProject.in(file("ui"))
  .settings(
    name := "youi-ui"
  )
  .jsSettings(
    libraryDependencies ++= Seq(
      "com.outr" %%% "canvg-scala-js" % canvgVersion,
      "com.outr" %%% "opentype-scala-js" % openTypeVersion,
      "com.outr" %%% "pica-scala-js" % picaVersion
    ),
    jsDependencies += RuntimeDOM
  )
  .dependsOn(spatial)

lazy val uiJS = ui.js.dependsOn(dom)
lazy val uiJVM = ui.jvm

lazy val hypertext = project.in(file("hypertext"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "youi-hypertext",
    jsDependencies += RuntimeDOM
  )
  .dependsOn(uiJS)

lazy val optimizer = project.in(file("optimizer"))
  .settings(
    name := "youi-optimizer",
    description := "Provides optimization functionality for application development.",
    fork := true,
    libraryDependencies ++= Seq(
      "com.google.javascript" % "closure-compiler" % closureCompilerVersion,
      "org.powerscala" %% "powerscala-io" % powerScalaVersion,
      "com.outr" %% "scribe" % scribeVersion,
      "com.outr" %% "hasher" % hasherVersion
    )
  )
  .dependsOn(stream)

lazy val app = crossProject.in(file("app"))
  .settings(
    name := "youi-app",
    libraryDependencies ++= Seq(
      "org.scalactic" %%% "scalactic" % scalacticVersion,
      "org.scalatest" %%% "scalatest" % scalaTestVersion % "test"
    )
  )
  .dependsOn(core, communication, ui)

lazy val appJS = app.js.dependsOn(hypertext)
lazy val appJVM = app.jvm

lazy val template = crossProject.in(file("template"))
  .settings(
    name := "youi-template"
  )
  .jsSettings(
    artifactPath in (Compile, fastOptJS) := (resourceManaged in Compile).value / "application.js",
    artifactPath in (Compile, fullOptJS) := (resourceManaged in Compile).value / "application.js",
    crossTarget in fastOptJS := baseDirectory.value / ".." / "jvm" / "src" / "main" / "resources" / "app",
    crossTarget in fullOptJS := baseDirectory.value / ".." / "jvm" / "src" / "main" / "resources" / "app",
    crossTarget in packageJSDependencies := baseDirectory.value / ".." / "jvm" / "src" / "main" / "resources" / "app",
    skip in packageJSDependencies := false
  )
  .jvmSettings(
    fork := true,
    libraryDependencies ++= Seq(
      "org.powerscala" %% "powerscala-io" % powerScalaVersion
    ),
    assemblyJarName in assembly := "youi-template.jar"
  )
  .dependsOn(app)

lazy val templateJS = template.js
lazy val templateJVM = template.jvm.dependsOn(serverUndertow, optimizer)

lazy val example = crossProject.in(file("example"))
  .settings(
    name := "youi-example"
  )
  .jsSettings(
    crossTarget in fastOptJS := baseDirectory.value / ".." / "jvm" / "src" / "main" / "resources" / "app",
    crossTarget in fullOptJS := baseDirectory.value / ".." / "jvm" / "src" / "main" / "resources" / "app",
    crossTarget in packageJSDependencies := baseDirectory.value / ".." / "jvm" / "src" / "main" / "resources" / "app",
    crossTarget in packageMinifiedJSDependencies := baseDirectory.value / ".." / "jvm" / "src" / "main" / "resources" / "app",
    skip in packageJSDependencies := false
  )
  .jvmSettings(
    scalaJSUseMainModuleInitializer := true,
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-reflect" % scalaVersion.value,
      "org.scala-lang.modules" %% "scala-xml" % scalaXMLVersion
    )
  )
  .dependsOn(app)

lazy val exampleJS = example.js
lazy val exampleJVM = example.jvm.dependsOn(serverUndertow)

lazy val utilities = project.in(file("utilities"))
  .settings(
    name := "youi-utilities",
    libraryDependencies ++= Seq(
      "org.jsoup" % "jsoup" % jSoupVersion,
      "org.powerscala" %% "powerscala-io" % powerScalaVersion
    )
  )
  .dependsOn(coreJVM)