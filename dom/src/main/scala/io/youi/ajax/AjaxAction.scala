package io.youi.ajax

import com.outr.reactify.{StateChannel, Var}
import org.scalajs.dom.XMLHttpRequest

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class AjaxAction(request: AjaxRequest) {
  lazy val future: Future[XMLHttpRequest] = request.promise.future
  private[ajax] val _state = Var[ActionState](ActionState.New)
  def state: StateChannel[ActionState] = _state
  def loaded: StateChannel[Int] = request.loaded
  def total: StateChannel[Int] = request.total
  def percentage: StateChannel[Int] = request.percentage
  def cancelled: StateChannel[Boolean] = request.cancelled

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