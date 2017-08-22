resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"

addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "2.0")
addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.1.0-M1")
addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.19")
addSbtPlugin("io.spray" % "sbt-revolver" % "0.9.0")
//addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.5.0")
//addSbtPlugin("com.codacy" % "sbt-codacy-coverage" % "1.3.8")