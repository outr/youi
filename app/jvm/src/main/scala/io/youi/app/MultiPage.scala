package io.youi.app
import java.io.File

import io.youi.http
import io.youi.http._
import io.youi.http.content.Content
import io.youi.net.URLMatcher

trait MultiPage extends MatcherPage {
  def pages: List[String]

  def page2Path(page: String): String = s"/$page"
  def page2Resource(page: String): File = new File(page)

  private val paths = pages.map { page =>
    val path = page2Path(page)
    val matcher = http.path.exact(path)
    val resource = page2Resource(page)
    path -> PageEntry(path, matcher, resource)
  }.toMap

  override protected val matcher: URLMatcher = combined.any(paths.values.map(_.matcher).toSeq: _*)

  override protected def resource(httpConnection: HttpConnection): Option[Content] = paths.get(httpConnection.request.url.path.decoded).map(_.resource)

  case class PageEntry(path: String, matcher: URLMatcher, resource: File)
}