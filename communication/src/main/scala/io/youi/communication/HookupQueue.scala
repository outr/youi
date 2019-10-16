package io.youi.communication

import java.util.concurrent.{ConcurrentHashMap, ConcurrentLinkedQueue}
import java.util.concurrent.atomic.AtomicLong

import io.circe.Json
import reactify.{Val, Var}

import scala.concurrent.{Future, Promise}

class HookupQueue {
  private var disposed = false
  private val idGenerator = new AtomicLong(0L)
  private val queue = new ConcurrentLinkedQueue[HookupRequest]
  private val running = new ConcurrentHashMap[Long, HookupRequest]

  private val _hasNext: Var[Boolean] = Var(false)
  private val _hasRunning: Var[Boolean] = Var(false)

  val hasNext: Val[Boolean] = _hasNext
  val hasRunning: Val[Boolean] = _hasRunning

  def enqueue(json: Json): Future[Json] = if (disposed) {
    throw new RuntimeException("Queue is disposed")
  } else {
    val promise = Promise[Json]
    queue.add(HookupRequest(idGenerator.incrementAndGet(), json, promise))
    _hasNext @= true
    promise.future
  }
  def next(): Option[HookupRequest] = {
    val o = Option(queue.poll())
    o.foreach { r =>
      running.put(r.id, r)
      _hasRunning @= true
    }
    _hasNext @= !queue.isEmpty
    o
  }

  def success(id: Long, json: Json): Boolean = running(id) match {
    case Some(request) => {
      request.success(json)
      true
    }
    case None => false
  }

  def failure(id: Long, throwable: Throwable): Boolean = running(id) match {
    case Some(request) => {
      request.failure(throwable)
      true
    }
    case None => false
  }

  private def running(id: Long): Option[HookupRequest] = Option(running.get(id)) match {
    case s: Some[HookupRequest] => {
      running.remove(id)
      _hasRunning @= !running.isEmpty
      s
    }
    case None => None
  }

  def clear(): Unit = {
    val t = new RuntimeException("Queue disposed")
    def recurse(): Unit = next() match {
      case None => // Done
      case Some(request) => {
        request.failure(t)
        recurse()
      }
    }
    recurse()
    running.forEachValue(1000L, (request: HookupRequest) => {
      request.failure(t)
    })
    running.clear()
    _hasNext @= false
    _hasRunning @= false
  }

  def dispose(): Unit = {
    disposed = true
    clear()
  }
}