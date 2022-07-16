package io.youi.server.handler

import cats.effect.IO

import java.net.{URL, URLEncoder}
import java.nio.file.{Path, Paths}
import io.youi.http.content.Content
import io.youi.http.{HttpConnection, HttpStatus}
import io.youi.net.ContentType
import io.youi.stream.Selector._
import io.youi.stream._
import io.youi.stream.delta.Delta

case class ProxyCache(directory: Path = Paths.get(System.getProperty("java.io.tmpdir"))) extends HttpHandler {
  override def handle(connection: HttpConnection): IO[HttpConnection] = connection.request.url.param("url") match {
    case Some(cacheURL) => {
      val url = new URL(cacheURL)
      val fileName = URLEncoder.encode(cacheURL, "UTF-8")
      val file = directory.resolve(fileName).toAbsolutePath.toFile
      if (!file.exists()) {
        scribe.debug(s"Downloading $cacheURL...")
        val tmp = directory.resolve(s"$fileName.tmp").toAbsolutePath.toFile
        Stream.apply(url, tmp)
        Stream.apply(tmp, file)
        if (!tmp.delete()) {
          tmp.deleteOnExit()
        }
      } else {
        scribe.debug(s"$cacheURL already exists")
      }

      IO(connection.modify(_.withContent(Content.file(file)).withStatus(HttpStatus.OK)))
    }
    case None => IO {
      connection.modify(_.withStatus(HttpStatus.BadRequest).withContent(Content.string("Must include `url` as GET parameter", ContentType.`text/plain`)))
    }
  }
}

object ProxyCache {
  def delta(cachePath: String): Delta = Delta.Process(
    selector = ByMultiple(ByTag("script"), ByTag("img"), ByTag("link")),
    replace = true,
    onlyOpenTag = true,
    processor = (tag, content) => {
      def fixContent(attributeName: String): String = {
        val value = tag.attributes.getOrElse(attributeName, "")
        if (value.toLowerCase.startsWith("http")) {
          content.replace(value, s"/cache?url=${URLEncoder.encode(value, "UTF-8")}")
        } else {
          content
        }
      }
      tag.tagName.toLowerCase match {
        case "script" | "img" => fixContent("src")
        case "link" => fixContent("href")
      }
    }
  )
}