sonatypeProfileName := "io.youi"
publishMavenStyle := true
licenses := Seq("APL2" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))
homepage := Some(url("https://github.com/outr/youi"))
scmInfo := Some(
  ScmInfo(
    url("https://github.com/outr/youi"),
    "scm:git@github.com:outr/youi.git"
  )
)
developers := List(
  Developer(id="darkfrog", name="Matt Hicks", email="matt@outr.com", url=url("http://matthicks.com"))
)