package io.youi.app

import io.youi.http.{Content, HttpConnection}

trait TemplatePage extends MatcherPage {
  def templateFor(path: String): Option[Content]

  def partFor(path: String): Option[Content]

  override protected def resource(httpConnection: HttpConnection): Option[Content] = {
    val path = httpConnection.request.url.path.decoded
    if (httpConnection.request.url.param("part").contains("true")) {
      partFor(path)
    } else {
      templateFor(path)
    }
  }
}
