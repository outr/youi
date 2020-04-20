package io.youi.app

import java.io.File

import io.youi.http.content.{StringContent, URLContent}
import io.youi.http.{HttpConnection, HttpRequest, HttpStatus}
import io.youi.net.{Path, URL}
import io.youi.stream._
import scribe.Execution.global

import scala.concurrent.Future

class OfflineGenerator(application: ServerApplication,
                       outputDirectory: File,
                       baseURL: URL = URL("http://localhost")) {
  def generate(paths: List[Path]): Future[Unit] = paths.headOption match {
    case Some(path) => {
      scribe.info(s"*********** PROCESSING: $path ********************")
      application.initialize().flatMap { _ =>
        application.handle(HttpConnection(application, HttpRequest(url = baseURL.withPath(path)))).flatMap { connection =>
          if (connection.response.status == HttpStatus.OK) {
            connection.response.content match {
              case Some(content) => {
                val file = new File(outputDirectory, path.encoded.substring(1))
                file.getParentFile.mkdirs()
                scribe.info(s"Writing $path to ${file.getAbsolutePath}..")
                content match {
                  case c: StringContent => IO.stream(c.value, file)
                  case c: URLContent => IO.stream(c.url, file)
                  case _ => throw new RuntimeException(s"Unsupported Content-Type: $content")
                }
              }
              case None => scribe.warn(s"No content returned for $path")
            }

            generate(paths.tail)
          } else {
            throw new RuntimeException(s"Bad status for: $path - ${connection.response.status}, Content: ${connection.response.content}")
          }
        }
      }
    }
    case None => {
      scribe.info("Finished processing offline generation")
      Future.successful(())
    }
  }
}
