import sbtcrossproject.CrossPlugin.autoImport.crossProject
import sbtcrossproject.CrossType

name := "youi"
ThisBuild / organization := "io.youi"
ThisBuild / version := "0.15.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.8"
ThisBuild / crossScalaVersions := List("2.13.8", "2.12.16")
ThisBuild / scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

ThisBuild / publishTo := sonatypePublishToBundle.value
ThisBuild / sonatypeProfileName := "io.youi"
//ThisBuild / publishMavenStyle := true
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

val fabricVersion: String = "1.3.0"

val profigVersion: String = "3.4.1"

val scribeVersion: String = "3.10.6"

val reactifyVersion: String = "4.0.8"

val hasherVersion: String = "1.2.2"

val openTypeVersion: String = "1.1.0"

val webFontLoaderVersion: String = "1.6.28_2"

val canvgVersion: String = "1.4.0_3"

val scalaJSDOMVersion: String = "2.3.0"

val okHttpVersion: String = "4.10.0"

val uaDetectorVersion: String = "2014.10"

val undertowVersion: String = "2.2.19.Final"

val closureCompilerVersion: String = "v20220803"

val guavaVersion: String = "25.1-jre"

val jSoupVersion: String = "1.13.1"

val scalaXMLVersion: String = "2.0.1"

val collectionCompatVersion: String = "2.4.3"

val catsVersion: String = "3.3.14"

val fs2Version: String = "3.2.12"

val scalaTestVersion: String = "3.2.13"

val catsEffectTestVersion: String = "1.4.0"

ThisBuild / evictionErrorLevel := Level.Info

lazy val root = project.in(file("."))
  .aggregate(
    coreJS, coreJVM, spatialJS, spatialJVM, stream, dom, clientJS, clientJVM, communicationJS,
    communicationJVM, server, serverUndertow, gui, capacitor, optimizer, appJS, appJVM
  )
  .settings(
    publish := {},
    publishLocal := {}
  )

lazy val core = crossProject(JSPlatform, JVMPlatform).in(file("core"))
  .settings(
    name := "youi-core",
    description := "Core functionality leveraged and shared by most other sub-projects of YouI.",
    libraryDependencies ++= Seq(
      "com.outr" %%% "fabric-parse" % fabricVersion,
      "com.outr" %%% "profig" % profigVersion,
      "com.outr" %%% "scribe" % scribeVersion,
      "com.outr" %%% "reactify" % reactifyVersion,
      "org.typelevel" %%% "cats-effect" % catsVersion,
      "co.fs2" %% "fs2-core" % fs2Version,
      "org.scalatest" %%% "scalatest" % scalaTestVersion % Test,
      "org.typelevel" %%% "cats-effect-testing-scalatest" % catsEffectTestVersion % Test
    )
  )
  .jsSettings(
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % scalaJSDOMVersion
    )
  )

lazy val coreJS = core.js
lazy val coreJVM = core.jvm

lazy val client = crossProject(JSPlatform, JVMPlatform).in(file("client"))
  .settings(
    name := "youi-client",
    libraryDependencies ++= Seq(
      "org.scalatest" %%% "scalatest" % scalaTestVersion % Test,
      "org.typelevel" %%% "cats-effect-testing-scalatest" % catsEffectTestVersion % Test
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
      "org.scalatest" %%% "scalatest" % scalaTestVersion % Test,
      "org.typelevel" %%% "cats-effect-testing-scalatest" % catsEffectTestVersion % Test
    )
  )
  .dependsOn(core)

lazy val spatialJS = spatial.js
lazy val spatialJVM = spatial.jvm

lazy val stream = project.in(file("stream"))
  .settings(
    name := "youi-stream",
    libraryDependencies ++= Seq(
      "org.scalatest" %%% "scalatest" % scalaTestVersion % Test,
      "org.typelevel" %%% "cats-effect-testing-scalatest" % catsEffectTestVersion % Test
    )
  )
  .dependsOn(coreJVM)

lazy val dom = project.in(file("dom"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "youi-dom",
    libraryDependencies ++= Seq(
      "com.outr" %%% "profig" % profigVersion,
      "org.scalatest" %%% "scalatest" % scalaTestVersion % Test,
      "org.typelevel" %%% "cats-effect-testing-scalatest" % catsEffectTestVersion % Test
    ),
    test := {},     // TODO: figure out why this no longer works
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
      "org.scalatest" %%% "scalatest" % scalaTestVersion % Test,
      "org.typelevel" %%% "cats-effect-testing-scalatest" % catsEffectTestVersion % Test
    )
  )
  .dependsOn(core)

lazy val communicationJS = communication.js
lazy val communicationJVM = communication.jvm

lazy val server = project.in(file("server"))
  .settings(
    name := "youi-server",
    libraryDependencies ++= Seq(
      "net.sf.uadetector" % "uadetector-resources" % uaDetectorVersion,
      "org.typelevel" %% "cats-effect" % catsVersion,
      "org.scalatest" %% "scalatest" % scalaTestVersion % Test,
      "org.typelevel" %% "cats-effect-testing-scalatest" % catsEffectTestVersion % Test
    )
  )
  .dependsOn(communicationJVM, stream)

lazy val serverUndertow = project.in(file("serverUndertow"))
  .settings(
    name := "youi-server-undertow",
    fork := true,
    libraryDependencies ++= Seq(
      "io.undertow" % "undertow-core" % undertowVersion,
      "org.scalatest" %%% "scalatest" % scalaTestVersion % Test,
      "org.typelevel" %%% "cats-effect-testing-scalatest" % catsEffectTestVersion % Test
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
      "org.scalatest" %%% "scalatest" % scalaTestVersion % Test,
      "org.typelevel" %%% "cats-effect-testing-scalatest" % catsEffectTestVersion % Test
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
