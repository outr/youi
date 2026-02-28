package youi

trait Store {
  def get[T](key: String): Option[T]

  def update[T](key: String, value: T): Unit

  def remove(key: String): Unit

  def apply[T](key: String): T = get[T](key).getOrElse(throw new NullPointerException(s"$key not found."))

  def getOrElse[T](key: String, default: => T): T = get[T](key).getOrElse(default)

  def getOrSet[T](key: String, default: => T): T = synchronized {
    get[T](key) match {
      case Some(value) => value
      case None => {
        val value: T = default
        update(key, value)
        value
      }
    }
  }
}

class MapStore(var map: Map[String, Any] = Map.empty) extends Store {
  override def get[T](key: String): Option[T] = map.get(key).asInstanceOf[Option[T]]

  override def update[T](key: String, value: T): Unit = synchronized(map += key -> value)

  override def remove(key: String): Unit = synchronized(map -= key)
}

class ThreadLocalStore extends Store {
  private val threadLocal = new ThreadLocal[Option[Map[String, Any]]] {
    override def initialValue(): Option[Map[String, Any]] = None
  }
  private def map: Map[String, Any] = threadLocal.get().getOrElse(throw new RuntimeException(s"Not in a thread-local context."))
  private def map_=(map: Map[String, Any]): Unit = threadLocal.set(Some(map))

  override def get[T](key: String): Option[T] = map.get(key).asInstanceOf[Option[T]]

  override def update[T](key: String, value: T): Unit = map = map + (key -> value)

  override def remove(key: String): Unit = map = map - key

  def contextualize[R](f: => R): R = {
    threadLocal.set(Some(Map.empty))
    try {
      f
    } finally {
      threadLocal.remove()
    }
  }
}