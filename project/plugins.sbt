addSbtPlugin("org.portable-scala" % "sbt-scalajs-crossproject" % "1.0.0")
addSbtPlugin("org.scala-js"       % "sbt-scalajs" % "1.5.1")

addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "3.9.7")
addSbtPlugin("com.jsuereth" % "sbt-pgp" % "2.1.1")
addSbtPlugin("io.spray" % "sbt-revolver" % "0.9.1")
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.7.0")

addSbtPlugin("io.youi" % "youi-plugin" % "1.2.0")

libraryDependencies += "org.scala-js" %% "scalajs-env-jsdom-nodejs" % "1.1.0"