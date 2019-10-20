package io.youi.communication

import scala.concurrent.Promise

case class HookupRequest(request: Message, promise: Promise[Message]) {
  def success(response: Message): Unit = if (!promise.isCompleted) promise.success(response)
  def failure(throwable: Throwable): Unit = if (!promise.isCompleted) promise.failure(throwable)
}