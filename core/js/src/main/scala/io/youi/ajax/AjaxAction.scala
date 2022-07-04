package io.youi.ajax

import cats.effect.IO
import org.scalajs.dom.XMLHttpRequest
import reactify._

import cats.effect.unsafe.implicits.global

class AjaxAction(request: AjaxRequest) {
  lazy val io: IO[Either[Throwable, XMLHttpRequest]] = request.deferred.get
  private[ajax] val _state = Var[ActionState](ActionState.New)
  def state: Val[ActionState] = _state
  def loaded: Val[Double] = request.loaded
  def total: Val[Double] = request.total
  def percentage: Val[Int] = request.percentage
  def cancelled: Val[Boolean] = request.cancelled

  private[ajax] def start(manager: AjaxManager): Unit = {
    if (!cancelled()) {
      _state @= ActionState.Running
      io.flatMap { _ =>
        _state @= ActionState.Finished
        manager.remove(this)
        IO.unit
      }.unsafeRunAndForget()
      request.send()
    } else {
      manager.remove(this)
    }
  }

  // TODO: dequeue if not already running
  def cancel(): Unit = request.cancel()     // TODO: does cancel fire onComplete with a failure?
}