package io.youi.upload

import java.io.File

import io.youi.http.content.{Content, FormDataContent}
import io.youi.http.{HttpConnection, HttpStatus}
import io.youi.net._
import io.youi.server.handler.HttpHandler
import io.youi.stream.IO
import reactify.Channel

import scala.concurrent.Future
import scribe.Execution.global

case class UploadManager(path: Path = path"/upload",
                         directory: File = new File(System.getProperty("java.io.tmpdir")),
                         authenticator: Option[HttpConnection => Boolean] = None) extends HttpHandler {
  val received: Channel[File] = Channel[File]

  private def extensionFromFileName(fileName: String): String = {
    val lastDotIndex = fileName.lastIndexOf('.')
    if (lastDotIndex != -1) {
      fileName.substring(lastDotIndex + 1)
    } else {
      "bin"
    }
  }

  override def handle(connection: HttpConnection): Future[HttpConnection] = if (connection.request.url.path == path) {
    Future {
      val authenticated = authenticator.forall(_(connection))
      if (authenticated) {
        connection.request.content match {
          case Some(content: FormDataContent) => {
            val fileName = content.string("type").value match {
              case "upload" => {
                val fileEntry = content.file("file")
                val ext = extensionFromFileName(fileEntry.fileName)
                val f = File.createTempFile(fileEntry.fileName, s".$ext")
                fileEntry.file.renameTo(f)
                received @= f
                f.getName
              }
              case "mergeSlices" => {
                val fileName = content.string("fileName").value
                val ext = extensionFromFileName(fileName)
                val slices = content.string("slices").value.split(',').toList
                val sources = slices.map(fn => new File(directory, fn))
                val destination = File.createTempFile(fileName, s".$ext")
                IO.merge(sources, destination)
                received @= destination
                destination.getName
              }
            }
            connection.modify { response =>
              response.withContent(Content.string(fileName, ContentType.`text/plain`))
            }
          }
          case None => connection.modify { response =>
            response.withStatus(HttpStatus.NoContent).withContent(Content.string("No content sent", ContentType.`text/plain`))
          }
        }
      } else {
        connection.modify { response =>
          response.withStatus(HttpStatus.Forbidden).withContent(Content.string("Authentication failure", ContentType.`text/plain`))
        }
      }
    }
  } else {
    Future.successful(connection)
  }
}
