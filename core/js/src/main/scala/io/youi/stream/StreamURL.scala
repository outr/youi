package io.youi.stream

import rapid.Task
import org.scalajs.dom.FormData
import spice.ajax.AjaxRequest
import spice.http.HttpMethod
import spice.net.URL

import scala.util.{Failure, Success}

object StreamURL {
  def stream(url: URL,
             method: HttpMethod = HttpMethod.Post,
             data: Option[FormData] = None,
             timeout: Int = 0,
             headers: Map[String, String] = Map.empty,
             withCredentials: Boolean = true,
             responseType: String = ""): Task[String] = {
    val request = new AjaxRequest(url, method, data, timeout, headers + ("streaming" -> "true"), withCredentials, responseType)
    request.send().map {
      case Failure(throwable) => throw throwable
      case Success(req) => req.responseText
    }
  }
}
