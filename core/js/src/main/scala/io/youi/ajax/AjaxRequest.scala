package io.youi.ajax

import io.youi.net.URL
import org.scalajs.dom
import org.scalajs.dom._
import org.scalajs.dom.ext.AjaxException
import reactify._

import scala.concurrent.{Future, Promise}
import scala.scalajs.js
import scala.scalajs.js.|

class AjaxRequest(url: URL,
                  data: Option[FormData | String] = None,
                  timeout: Int = 0,
                  headers: Map[String, String] = Map.empty,
                  withCredentials: Boolean = true,
                  responseType: String = "") {
  val req = new dom.XMLHttpRequest()
  val promise: Promise[XMLHttpRequest] = Promise[dom.XMLHttpRequest]()
  val loaded: State[Double] = Var(0.0)
  val total: State[Double] = Var(0.0)
  val percentage: State[Int] = Var(0)
  val cancelled: State[Boolean] = Var(false)

  req.onreadystatechange = { _: dom.Event =>
    if (req.readyState == 4) {
      if ((req.status >= 200 && req.status < 300) || req.status == 304) {
        promise.success(req)
      } else {
        promise.failure(AjaxException(req))
      }
    }
  }
  req.upload.addEventListener("progress", (evt: ProgressEvent) => {
    total.asInstanceOf[Var[Double]] := evt.total
    loaded.asInstanceOf[Var[Double]] := evt.loaded
    val p = math.round(math.floor((evt.loaded / evt.total) * 100)).toInt
    percentage.asInstanceOf[Var[Int]] := p
  })
  req.open("POST", url.toString)
  req.responseType = responseType
  req.timeout = timeout
  req.withCredentials = withCredentials
  headers.foreach(x => req.setRequestHeader(x._1, x._2))

  def send(): Future[dom.XMLHttpRequest] = {
    data match {
      case Some(formData) => req.send(formData.asInstanceOf[js.Any])
      case None => req.send()
    }
    promise.future
  }

  def cancel(): Unit = if (percentage.get != 100) {
    req.abort()
    cancelled.asInstanceOf[Var[Boolean]] := true
  }
}