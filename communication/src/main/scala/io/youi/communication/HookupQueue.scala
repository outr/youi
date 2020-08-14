package io.youi.communication

import reactify.{Val, Var}

import scala.concurrent.{Future, Promise}

class HookupQueue {
  private var disposed = false
  private val queue = new SafeQueue[HookupRequest]
  private val running = new SafeMap[Long, HookupRequest]

  private val _hasNext: Var[Boolean] = Var(false)
  private val _hasRunning: Var[Boolean] = Var(false)

  val hasNext: Val[Boolean] = _hasNext
  val hasRunning: Val[Boolean] = _hasRunning

  def enqueue(message: Message): Future[Message] = if (disposed) {
    throw new RuntimeException("Queue is disposed")
  } else {
    val promise = Promise[Message]()
    queue.add(HookupRequest(message, promise))
    _hasNext @= true
    promise.future
  }
  def next(): Option[HookupRequest] = {
    val o = queue.poll()
    o.foreach { r =>
      if (r.isRunning) {
        running.put(r.request.id, r)
        _hasRunning @= true
      }
    }
    _hasNext @= !queue.isEmpty
    o
  }

  def success(message: Message): Boolean = running(message.id) match {
    case Some(request) => {
      request.success(message)
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

  private def running(id: Long): Option[HookupRequest] = running.get(id) match {
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
    running.values.foreach(_.failure(t))
    running.clear()
    _hasNext @= false
    _hasRunning @= false
  }

  def dispose(): Unit = {
    disposed = true
    clear()
  }

  class SafeQueue[T] {
    private var queue = List.empty[T]

    def add(t: T): Unit = synchronized {
      queue = queue ::: List(t)
    }

    def isEmpty: Boolean = queue.isEmpty

    def poll(): Option[T] = synchronized {
      val head = queue.headOption
      if (head.nonEmpty) {
        queue = queue.tail
      }
      head
    }
  }

  class SafeMap[K, V] {
    private var map = Map.empty[K, V]

    def values: Iterable[V] = map.values

    def isEmpty: Boolean = map.isEmpty

    def get(key: K): Option[V] = map.get(key)

    def put(key: K, value: V): Unit = synchronized(map += key -> value)

    def remove(key: K): Unit = synchronized(map -= key)

    def clear(): Unit = synchronized {
      map = Map.empty[K, V]
    }
  }
}