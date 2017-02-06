package io.youi.app
import java.io.File

import io.youi.http.HttpConnection

trait TemplatePage extends MatcherPage {
  def templateFor(path: String): Option[File]

  def partFor(path: String): Option[File]

  override protected def resource(httpConnection: HttpConnection): Option[File] = {
    val path = httpConnection.request.url.path.decoded
    if (httpConnection.request.url.param("part").contains("true")) {
      partFor(path)
    } else {
      templateFor(path)
    }
  }
}
