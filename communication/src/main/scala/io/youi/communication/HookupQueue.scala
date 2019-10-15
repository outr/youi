package io.youi.communication

import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.atomic.AtomicLong

import io.circe.Json

import scala.concurrent.{Future, Promise}

class HookupQueue {
  private var disposed = false
  private val idGenerator = new AtomicLong(0L)
  private val queue = new ConcurrentLinkedQueue[HookupRequest]

  def enqueue(json: Json): Future[Json] = if (disposed) {
    throw new RuntimeException("Queue is disposed")
  } else {
    val promise = Promise[Json]
    queue.add(HookupRequest(idGenerator.incrementAndGet(), json, promise))
    promise.future
  }
  def next(): Option[HookupRequest] = Option(queue.poll())
  def hasNext: Boolean = !queue.isEmpty

  def dispose(): Unit = {
    disposed = true
    val t = new RuntimeException("Queue disposed")
    def recurse(): Unit = next() match {
      case None => // Done
      case Some(request) => {
        request.failure(t)
        recurse()
      }
    }
    recurse()
  }
}