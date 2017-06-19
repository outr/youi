package io.youi.ajax

import io.youi.net.URL
import org.scalajs.dom.XMLHttpRequest
import org.scalajs.dom.raw.FormData
import scribe.Logging

import scala.collection.immutable.Queue
import scala.concurrent.Future

class AjaxManager(val maxConcurrent: Int) extends Logging {
  private var queue = Queue.empty[AjaxAction]
  private var running = Set.empty[AjaxAction]

  def enqueue(url: URL,
              data: Option[FormData] = None,
              timeout: Int = 0,
              headers: Map[String, String] = Map.empty,
              withCredentials: Boolean = true,
              responseType: String = ""): AjaxAction = {
    val request = new AjaxRequest(url, data, timeout, headers, withCredentials, responseType)
    val action = new AjaxAction(request)
    enqueue(action)
    action
  }

  def enqueue(action: AjaxAction): Future[XMLHttpRequest] = {
    queue = queue.enqueue(action)
    action._state := ActionState.Enqueued
    checkQueue()
    action.future
  }

  def checkQueue(): Unit = if (running.size < maxConcurrent && queue.nonEmpty) {
    val (action, updated) = queue.dequeue
    queue = updated
    running += action
    action.start(this)
  }

  private[ajax] def remove(action: AjaxAction): Unit = {
    running -= action
    checkQueue()
  }
}