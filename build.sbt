import sbtcrossproject.CrossPlugin.autoImport.crossProject
import sbtcrossproject.CrossType

name := "youi"
ThisBuild / organization := "io.youi"
ThisBuild / version := "0.14.0"
ThisBuild / scalaVersion := "2.13.5"
ThisBuild / crossScalaVersions := List("2.13.5", "2.12.12")
ThisBuild / resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)
ThisBuild / scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

ThisBuild / publishTo := sonatypePublishToBundle.value
ThisBuild / sonatypeProfileName := "io.youi"
ThisBuild / publishMavenStyle := true
ThisBuild / licenses := Seq("MIT" -> url("https://github.com/outr/youi/blob/master/LICENSE"))
ThisBuild / sonatypeProjectHosting := Some(xerial.sbt.Sonatype.GitHubHosting("outr", "youi", "matt@outr.com"))
ThisBuild / homepage := Some(url("https://github.com/outr/youi"))
ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/outr/youi"),
    "scm:git@github.com:outr/youi.git"
  )
)
ThisBuild / developers := List(
  Developer(id="darkfrog", name="Matt Hicks", email="matt@matthicks.com", url=url("http://matthicks.com"))
)

ThisBuild / versionScheme := Some("semver-spec")

val fabricVersion = "1.0.3"
val profigVersion = "3.2.1"
val scribeVersion = "3.5.3"
val reactifyVersion = "4.0.4"
val hasherVersion = "1.2.2"

val openTypeVersion = "1.1.0"
val webFontLoaderVersion = "1.6.28_2"
val canvgVersion = "1.4.0_3"

val scalaJSDOM = "1.1.0"
val okHttpVersion = "4.9.1"
val uaDetectorVersion = "2014.10"
val undertowVersion = "2.2.7.Final"
val closureCompilerVersion = "v20210302"
val guavaVersion = "25.1-jre"
val jSoupVersion = "1.13.1"
val scalaXMLVersion = "2.0.0-RC1"
val collectionCompat = "2.4.3"
val scalaTestVersion = "3.2.7"
val scalaCheckVersion = "1.15.3"

ThisBuild / evictionErrorLevel := Level.Info

lazy val root = project.in(file("."))
  .aggregate(
    macrosJS, macrosJVM, coreJS, coreJVM, spatialJS, spatialJVM, stream, dom, clientJS, clientJVM, communicationJS,
    communicationJVM, server, serverUndertow, gui, capacitor, optimizer, appJS, appJVM, exampleJS, exampleJVM
  )
  .settings(
    publish := {},
    publishLocal := {}
  )

lazy val macros = crossProject(JSPlatform, JVMPlatform).in(file("macros"))
  .settings(
    name := "youi-macros",
    description := "Dependency for internal Macro functionality",
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-reflect" % scalaVersion.value,
      "org.scala-lang.modules" %% "scala-collection-compat" % collectionCompat,
      "org.scalatest" %%% "scalatest" % scalaTestVersion % "test"
    )
  )

lazy val macrosJS = macros.js
lazy val macrosJVM = macros.jvm

lazy val core = crossProject(JSPlatform, JVMPlatform).in(file("core"))
  .settings(
    name := "youi-core",
    description := "Core functionality leveraged and shared by most other sub-projects of YouI.",
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-reflect" % scalaVersion.value,
      "com.outr" %%% "fabric-parse" % fabricVersion,
      "com.outr" %%% "profig" % profigVersion,
      "com.outr" %%% "scribe" % scribeVersion,
      "com.outr" %%% "reactify" % reactifyVersion,
      "org.scalatest" %%% "scalatest" % scalaTestVersion % "test"
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

lazy val client = crossProject(JSPlatform, JVMPlatform).in(file("client"))
  .settings(
    name := "youi-client",
    libraryDependencies ++= Seq(
      "org.scalatest" %%% "scalatest" % scalaTestVersion % "test"
    )
  )
  .jvmSettings(
    libraryDependencies ++= Seq(
      "com.squareup.okhttp3" % "okhttp" % okHttpVersion
    )
  )
  .dependsOn(core)

lazy val clientJS = client.js
lazy val clientJVM = client.jvm

lazy val spatial = crossProject(JSPlatform, JVMPlatform).in(file("spatial"))
  .settings(
    name := "youi-spatial",
    libraryDependencies ++= Seq(
      "org.scalatest" %%% "scalatest" % scalaTestVersion % "test",
      "org.scalacheck" %%% "scalacheck" % scalaCheckVersion % "test"
    )
  )
  .dependsOn(core)

lazy val spatialJS = spatial.js
lazy val spatialJVM = spatial.jvm

lazy val stream = project.in(file("stream"))
  .settings(
    name := "youi-stream",
    libraryDependencies ++= Seq(
      "org.scalatest" %%% "scalatest" % scalaTestVersion % "test",
      "org.scalacheck" %%% "scalacheck" % scalaCheckVersion % "test"
    )
  )
  .dependsOn(coreJVM)

lazy val dom = project.in(file("dom"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "youi-dom",
    libraryDependencies ++= Seq(
      "com.outr" %%% "profig" % profigVersion,
      "org.scalatest" %%% "scalatest" % scalaTestVersion % "test"
    ),
    jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv()
  )
  .dependsOn(coreJS)
  .dependsOn(stream % "compile")

lazy val communication = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("communication"))
  .settings(
    name := "youi-communication",
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-reflect" % scalaVersion.value,
      "org.scalatest" %%% "scalatest" % scalaTestVersion % "test"
    )
  )
  .jsSettings(
    test := {}
  )
  .dependsOn(core)

lazy val communicationJS = communication.js
lazy val communicationJVM = communication.jvm

lazy val server = project.in(file("server"))
  .settings(
    name := "youi-server",
    libraryDependencies ++= Seq(
      "net.sf.uadetector" % "uadetector-resources" % uaDetectorVersion,
      "org.scalatest" %% "scalatest" % scalaTestVersion % "test"
    )
  )
  .dependsOn(communicationJVM, stream)

lazy val serverUndertow = project.in(file("serverUndertow"))
  .settings(
    name := "youi-server-undertow",
    fork := true,
    libraryDependencies ++= Seq(
      "io.undertow" % "undertow-core" % undertowVersion,
      "org.scalatest" %% "scalatest" % scalaTestVersion % "test"
    )
  )
  .dependsOn(server, clientJVM % "test->test")

lazy val gui = project.in(file("gui"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "youi-gui",
    libraryDependencies ++= Seq(
      "com.outr" %%% "webfontloader-scala-js" % webFontLoaderVersion,
      "com.outr" %%% "opentype-scala-js" % openTypeVersion,
      "com.outr" %%% "canvg-scala-js" % canvgVersion
    )
  )
  .dependsOn(dom, spatialJS)

lazy val capacitor = project.in(file("capacitor"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "youi-capacitor"
  )

lazy val optimizer = project.in(file("optimizer"))
  .settings(
    name := "youi-optimizer",
    description := "Provides optimization functionality for application development.",
    fork := true,
    libraryDependencies ++= Seq(
      "com.google.javascript" % "closure-compiler" % closureCompilerVersion,
      "com.outr" %% "scribe" % scribeVersion,
      "com.outr" %% "hasher" % hasherVersion
    )
  )
  .dependsOn(stream)

lazy val app = crossProject(JSPlatform, JVMPlatform).in(file("app"))
  .settings(
    name := "youi-app",
    libraryDependencies ++= Seq(
      "org.scalatest" %%% "scalatest" % scalaTestVersion % "test"
    )
  )
  .dependsOn(core, communication)

lazy val appJS = app.js.dependsOn(gui)
lazy val appJVM = app.jvm.dependsOn(server)

lazy val example = crossApplication.in(file("example"))
  .settings(
    name := "youi-example",
    youiVersion := version.value,
    libraryDependencies += "com.outr" %%% "scribe" % scribeVersion,
    jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv()
  )
  .jvmSettings(
    scalaJSUseMainModuleInitializer := true,
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-reflect" % scalaVersion.value,
      "org.scala-lang.modules" %% "scala-xml" % scalaXMLVersion
    )
  )

lazy val exampleJS = example.js.dependsOn(appJS, gui)
lazy val exampleJVM = example.jvm.dependsOn(serverUndertow, appJVM)

lazy val utilities = project.in(file("utilities"))
  .settings(
    name := "youi-utilities",
    fork := true,
    libraryDependencies ++= Seq(
      "org.jsoup" % "jsoup" % jSoupVersion
    )
  )
  .dependsOn(coreJVM)
