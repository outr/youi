package io.youi.ajax

import com.outr.reactify.{StateChannel, Var}
import org.scalajs.dom
import org.scalajs.dom._
import org.scalajs.dom.ext.AjaxException

import scala.concurrent.{Future, Promise}

class AjaxRequest(url: String,
                 data: FormData,
                 timeout: Int = 0,
                 headers: Map[String, String] = Map.empty,
                 withCredentials: Boolean = true,
                 responseType: String = "") {
  val req = new dom.XMLHttpRequest()
  val promise = Promise[dom.XMLHttpRequest]()
  val loaded: StateChannel[Int] = Var(0)
  val total: StateChannel[Int] = Var(0)
  val percentage: StateChannel[Int] = Var(0)
  val cancelled: StateChannel[Boolean] = Var(false)

  req.onreadystatechange = { (e: dom.Event) =>
    if (req.readyState.toInt == 4) {
      if ((req.status >= 200 && req.status < 300) || req.status == 304) {
        promise.success(req)
      } else {
        promise.failure(AjaxException(req))
      }
    }
  }
  req.upload.addEventListener("progress", (evt: ProgressEvent) => {
    total.asInstanceOf[Var[Int]] := evt.total
    loaded.asInstanceOf[Var[Int]] := evt.loaded
    val p = math.round(math.floor((evt.loaded.toDouble / evt.total.toDouble) * 100)).toInt
    percentage.asInstanceOf[Var[Int]] := p
  })
  req.open("POST", url)
  req.responseType = responseType
  req.timeout = timeout
  req.withCredentials = withCredentials
  headers.foreach(x => req.setRequestHeader(x._1, x._2))

  def send(): Future[dom.XMLHttpRequest] = {
    if (data == null) {
      req.send()
    } else {
      req.send(data)
    }
    promise.future
  }

  def cancel(): Unit = if (percentage.get != 100) {
    req.abort()
    cancelled.asInstanceOf[Var[Boolean]] := true
  }
}