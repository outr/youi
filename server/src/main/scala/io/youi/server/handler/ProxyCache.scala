package io.youi.server.handler
import java.net.{URL, URLEncoder}
import java.nio.file.{Path, Paths}

import io.youi.http.{Content, HttpConnection, HttpStatus}
import io.youi.net.ContentType
import org.powerscala.io._

case class ProxyCache(directory: Path = Paths.get(System.getProperty("java.io.tmpdir"))) extends HttpHandler {
  override def handle(connection: HttpConnection): Unit = connection.request.url.param("url") match {
    case Some(cacheURL) => {
      val url = new URL(cacheURL)
      val fileName = URLEncoder.encode(cacheURL, "UTF-8")
      val file = directory.resolve(fileName).toAbsolutePath.toFile
      if (!file.exists()) {
        scribe.info(s"Downloading $cacheURL...")
        val tmp = directory.resolve(s"$fileName.tmp").toAbsolutePath.toFile
        IO.stream(url, tmp)
        IO.stream(tmp, file)
        if (!tmp.delete()) {
          tmp.deleteOnExit()
        }
      } else {
        scribe.info(s"$cacheURL already exists")
      }

      connection.update(_.withContent(Content.file(file)).withStatus(HttpStatus.OK))
    }
    case None => connection.update(_.withStatus(HttpStatus.BadRequest).withContent(Content.string("Must include `url` as GET parameter", ContentType.`text/plain`)))
  }
}
