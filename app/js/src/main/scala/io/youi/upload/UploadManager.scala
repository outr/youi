package io.youi.upload

import io.youi.History
import io.youi.ajax.AjaxManager
import io.youi.http.Headers
import io.youi.net._
import org.scalajs.dom.raw.{File, FormData}
import reactify.{Val, Var}

import scala.concurrent.Future
import scala.util.{Failure, Success}
import scribe.Execution.global

case class UploadManager(url: URL = History.url.withPath(path"/upload"),
                         chunkSize: Long = 50L * 1000L * 1000L,
                         timeout: Int = 0,
                         headers: Headers = Headers.empty,
                         withCredentials: Boolean = true,
                         ajaxManager: AjaxManager = new AjaxManager(1)) {
  def upload(file: File, headers: Headers = Headers.empty): Uploading = {
    val headersMap = headers.map.map {
      case (key, values) => key -> values.head
    }
    val progress = Var[Long](0)
    val percentage = Val(progress / file.size)
    val future = uploadSlice(
      file = file,
      offset = 0L,
      total = file.size.toLong,
      progress = progress,
      headers = headersMap
    )
    Uploading(progress, percentage, future)
  }

  private def uploadSlice(file: File,
                          offset: Long,
                          total: Long,
                          progress: Var[Long],
                          headers: Map[String, String],
                          slices: Set[String] = Set.empty): Future[String] = {
    val start = offset
    val end = math.min(total, offset + chunkSize)
    val sliced = file.slice(start, end)
    val sliceName = file.name
    val action = ajaxManager.enqueue(
      url = url,
      data = Some(new FormData {
        append("type", "upload")
        append("file", sliced, sliceName)
      }),
      headers = headers
    )
    val reaction = action.loaded.attach(d => progress @= math.min(offset + d.toLong, total))
    action.future.transformWith { t =>
      action.loaded.reactions -= reaction
      t match {
        case Success(request) => {
          val partName = request.response.toString
          if (end == total) {
            if (offset == 0L) {   // Only one slice, no need to merge
              Future.successful(partName)
            } else {
              mergeSlices(file.name, headers, slices + partName)
            }
          } else {
            uploadSlice(file, end, total, progress, headers, slices + partName)
          }
        }
        case Failure(exception) => Future.failed(exception)
      }
    }
  }

  private def mergeSlices(fileName: String, headers: Map[String, String], slices: Set[String]): Future[String] = {
    val action = ajaxManager.enqueue(
      url = url,
      data = Some(new FormData {
        append("type", "mergeSlices")
        append("fileName", fileName)
        append("slices", slices.mkString(","))
      }),
      headers = headers
    )
    action.future.map(_.response.toString)
  }
}