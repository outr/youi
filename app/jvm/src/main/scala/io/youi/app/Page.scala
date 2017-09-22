package io.youi.app

import io.youi.http.{Content, HttpConnection}
import io.youi.server.handler.{CachingManager, HttpProcessor}
import io.youi.server.validation.Validator
import io.youi.stream._

trait Page extends HttpProcessor[Content] {
  protected def application: ServerApplication
  protected def includeApplication: Boolean = true
  protected def cachingManager: CachingManager = CachingManager.Default

  override protected def validators(httpConnection: HttpConnection): List[Validator] = Nil

  protected def allowSelectors: Boolean = true

  protected def deltas(httpConnection: HttpConnection): List[Delta] = Nil

  override protected def process(connection: HttpConnection, content: Content): Unit = {
    application.serveHTML(connection, content, deltas(connection), includeApplication)
  }
}