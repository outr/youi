package io.youi.app

import java.io.File

import io.youi.http.HttpConnection
import io.youi.net.URLMatcher
import io.youi.stream._

trait Page {
  def matchers: List[URLMatcher]
  def resource: File
  def scalaJSConfig: Option[ScalaJSConfig]

  def allowSelectors: Boolean = true
  def deltas(httpConnection: HttpConnection): List[Delta] = scalaJSConfig.map { config =>
    val script =
      s"""
         |<script type="application/javascript" src="${config.path}"></script>
         |<script type="application/javascript">
         |    ${config.function}();
         |</script>
       """.stripMargin
    List(Delta.InsertLastChild(ByTag("body"), script))
  }.getOrElse(Nil)
}

case class ScalaJSConfig(path: String, function: String = "main")