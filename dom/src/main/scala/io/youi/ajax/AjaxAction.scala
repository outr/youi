package io.youi.ajax

import reactify._
import org.scalajs.dom.XMLHttpRequest

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class AjaxAction(request: AjaxRequest) {
  lazy val future: Future[XMLHttpRequest] = request.promise.future
  private[ajax] val _state = Var[ActionState](ActionState.New)
  def state: State[ActionState] = _state
  def loaded: State[Double] = request.loaded
  def total: State[Double] = request.total
  def percentage: State[Int] = request.percentage
  def cancelled: State[Boolean] = request.cancelled

  private[ajax] def start(manager: AjaxManager): Unit = {
    if (!cancelled()) {
      _state := ActionState.Running
      future.onComplete { result =>
        _state := ActionState.Finished
        manager.remove(this)
      }
      request.send()
    } else {
      manager.remove(this)
    }
  }

  // TODO: dequeue if not already running
  def cancel(): Unit = request.cancel()     // TODO: does cancel fire onComplete with a failure?
}