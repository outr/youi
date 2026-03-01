import org.scalajs.linker.interface.ESVersion

name := "youi"
ThisBuild / organization := "com.outr"
ThisBuild / version := "1.0.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.8.2"
ThisBuild / scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
  "-source:3.4-migration"
)

ThisBuild / publishTo := sonatypePublishToBundle.value
ThisBuild / sonatypeProfileName := "com.outr"
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
  Developer(id="darkfrog", name="Matt Hicks", email="matt@matthicks.com", url=url("https://matthicks.com"))
)

ThisBuild / versionScheme := Some("semver-spec")

ThisBuild / outputStrategy := Some(StdoutOutput)

val spiceVersion: String = "1.1.0"

val rapidVersion: String = "2.7.1"

val fabricVersion: String = "1.19.0"

val profigVersion: String = "3.4.18"

val scribeVersion: String = "3.17.0"

val reactifyVersion: String = "4.2.0"

val hasherVersion: String = "1.2.3"

val openTypeVersion: String = "1.1.0"

val webFontLoaderVersion: String = "1.6.28_2"

val scalaJSDOMVersion: String = "2.8.1"

val closureCompilerVersion: String = "v20260218"

val scalaTestVersion: String = "3.2.19"

ThisBuild / evictionErrorLevel := Level.Info

lazy val root = project.in(file("."))
  .aggregate(
    coreJS, coreJVM, spatialJS, spatialJVM, dom, ui, capacitor, optimizer,
    example, exampleServer
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
      "com.outr" %%% "spice-core" % spiceVersion,
      "org.typelevel" %%% "fabric-io" % fabricVersion,
      "com.outr" %%% "profig" % profigVersion,
      "com.outr" %%% "scribe" % scribeVersion,
      "com.outr" %%% "reactify" % reactifyVersion,
      "com.outr" %%% "rapid-core" % rapidVersion,
      "org.scalatest" %%% "scalatest" % scalaTestVersion % Test,
      "com.outr" %%% "rapid-test" % rapidVersion % Test
    )
  )
  .jsSettings(
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % scalaJSDOMVersion,
      "com.outr" %%% "spice-client" % spiceVersion
    )
  )

lazy val coreJS = core.js
lazy val coreJVM = core.jvm

lazy val spatial = crossProject(JSPlatform, JVMPlatform).in(file("spatial"))
  .settings(
    name := "youi-spatial",
    libraryDependencies ++= Seq(
      "org.scalatest" %%% "scalatest" % scalaTestVersion % Test,
      "com.outr" %%% "rapid-test" % rapidVersion % Test
    )
  )
  .dependsOn(core)

lazy val spatialJS = spatial.js
lazy val spatialJVM = spatial.jvm

lazy val dom = project.in(file("dom"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "youi-dom",
    libraryDependencies ++= Seq(
      "com.outr" %%% "profig" % profigVersion,
      "com.outr" %% "spice-delta" % spiceVersion,
      "org.scalatest" %%% "scalatest" % scalaTestVersion % Test,
      "com.outr" %%% "rapid-test" % rapidVersion % Test
    ),
    test := {},     // TODO: figure out why this no longer works
    jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv()
  )
  .dependsOn(coreJS)

lazy val ui = project.in(file("ui"))
  .enablePlugins(ScalaJSPlugin)
  .enablePlugins(JSDependenciesPlugin)
  .settings(
    name := "youi-ui",
//    jsDependencies += ProvidedJS / "webfontloader.js",
//    jsDependencies += ProvidedJS / "opentype.min.js",
//    jsDependencies += ProvidedJS / "opentype.min.map",
    packageJSDependencies / skip := false
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
      "com.outr" %% "spice-delta" % spiceVersion,
      "com.outr" %% "hasher" % hasherVersion
    )
  )

lazy val example = project.in(file("example"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "youi-example",
    scalaJSUseMainModuleInitializer := true,
    packageJSDependencies / skip := false,
    scalaJSLinkerConfig ~= { _.withESFeatures(_.withESVersion(ESVersion.ES2018)) }
  )
  .dependsOn(ui)

lazy val exampleServer = project.in(file("example/server"))
  .settings(
    name := "youi-example-server",
    libraryDependencies ++= Seq(
      "com.outr" %% "spice-server-undertow" % spiceVersion
    )
  )

/*
lazy val example = crossApplication.in(file("example"))
  .settings(
    name := "youi-example",
    youiVersion := version.value,
    libraryDependencies += "com.outr" %%% "scribe" % scribeVersion,
    jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv()
  )

lazy val exampleJS = example.js.dependsOn(ui)
lazy val exampleJVM = example.jvm

lazy val utilities = project.in(file("utilities"))
  .settings(
    name := "youi-utilities",
    fork := true,
    libraryDependencies ++= Seq(
      "org.jsoup" % "jsoup" % jSoupVersion
    )
  )
  .dependsOn(coreJVM)
*/
