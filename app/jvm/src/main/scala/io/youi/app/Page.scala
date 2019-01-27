package io.youi.app

import io.youi.http.HttpConnection
import io.youi.http.content.Content
import io.youi.server.handler.{CachingManager, HttpProcessor}
import io.youi.server.validation.Validator
import io.youi.stream._

import scala.concurrent.Future

trait Page extends HttpProcessor[Content] {
  protected def application: ServerApplication
  protected def includeApplication: Boolean = true
  protected def cachingManager: CachingManager = CachingManager.Default

  override protected def validators(httpConnection: HttpConnection): List[Validator] = Nil

  protected def allowSelectors: Boolean = true

  protected def deltas(httpConnection: HttpConnection): List[Delta] = Nil

  override protected def process(connection: HttpConnection, content: Content): Future[HttpConnection] = {
    application.serveHTML(connection, content, deltas(connection), includeApplication)
  }
}