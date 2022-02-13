addSbtPlugin("org.portable-scala" % "sbt-scalajs-crossproject" % "1.1.0")
addSbtPlugin("org.scala-js" % "sbt-scalajs" % "1.8.0")

addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "3.9.11")
addSbtPlugin("com.github.sbt" % "sbt-pgp" % "2.1.2")
addSbtPlugin("io.spray" % "sbt-revolver" % "0.9.1")
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.9.2")

addSbtPlugin("io.youi" % "youi-plugin" % "1.2.0")

libraryDependencies += "org.scala-js" %% "scalajs-env-jsdom-nodejs" % "1.1.0"