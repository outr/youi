package io.youi.ajax

import cats.effect.{Deferred, IO}
import io.youi.http.HttpMethod
import io.youi.net.URL
import org.scalajs.dom
import org.scalajs.dom._
import reactify._

import scala.scalajs.js
import scala.scalajs.js.|
import scala.util.{Failure, Success, Try}

class AjaxRequest(url: URL,
                  method: HttpMethod = HttpMethod.Post,
                  data: Option[FormData | String] = None,
                  timeout: Int = 0,
                  headers: Map[String, String] = Map.empty,
                  withCredentials: Boolean = true,
                  responseType: String = "") {
  val req = new dom.XMLHttpRequest()
  val deferred: Deferred[IO, Try[XMLHttpRequest]] = Deferred.unsafe[IO, Try[XMLHttpRequest]]
  val loaded: Val[Double] = Var(0.0)
  val total: Val[Double] = Var(0.0)
  val percentage: Val[Int] = Var(0)
  val cancelled: Val[Boolean] = Var(false)

  req.onreadystatechange = { _: dom.Event =>
    if (req.readyState == 4) {
      if ((req.status >= 200 && req.status < 300) || req.status == 304) {
        deferred.complete(Success(req))
      } else {
        deferred.complete(Failure(new RuntimeException(s"AjaxRequest failed: ${req.readyState}")))
      }
    }
  }
  req.upload.addEventListener("progress", (evt: ProgressEvent) => {
    total.asInstanceOf[Var[Double]] @= evt.total
    loaded.asInstanceOf[Var[Double]] @= evt.loaded
    val p = math.round(math.floor((evt.loaded / evt.total) * 100)).toInt
    percentage.asInstanceOf[Var[Int]] @= p
  })
  req.open(method.value, url.toString)
  req.responseType = responseType
  req.timeout = timeout
  req.withCredentials = withCredentials
  headers.foreach(x => req.setRequestHeader(x._1, x._2))

  def send(): IO[Try[XMLHttpRequest]] = {
    data match {
      case Some(formData) => req.send(formData.asInstanceOf[js.Any])
      case None => req.send()
    }
    deferred.get
  }

  def cancel(): Unit = if (percentage.get != 100) {
    req.abort()
    cancelled.asInstanceOf[Var[Boolean]] @= true
  }
}