package io.youi.app
import java.io.File

import io.youi.http
import io.youi.http._
import io.youi.net.URLMatcher

trait MultiPage extends Page {
  def pages: List[String]

  def page2Path(page: String): String = s"/$page"
  def page2Resource(page: String): File = new File(page)

  private val paths = pages.map { page =>
    val path = page2Path(page)
    val matcher = http.path.exact(path)
    val resource = page2Resource(page)
    path -> PageEntry(path, matcher, resource)
  }.toMap

  override val matcher: URLMatcher = combined.any(paths.values.map(_.matcher).toSeq: _*)

  override def resource(httpConnection: HttpConnection): File = paths(httpConnection.request.url.path.decoded).resource

  case class PageEntry(path: String, matcher: URLMatcher, resource: File)
}