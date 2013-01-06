package org.hyperscala.context

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class Context {
  implicit def thisContext = this
  if (Context.context != null) {
    throw new RuntimeException("There can be only one Context object in a single runtime")
  }
  Context.context = this

  private val local = new ThreadLocal[ContextInstance]

  private[context] def contextInstance = local.get() match {
    case null => throw new OutOfContextException
    case c => c
  }

  /**
   * True if within a context
   */
  def inContext = local.get() != null

  /**
   * Loads a context instance from a Contextual. Wrap is the preferred method to use instead of this to make sure close
   * gets properly invoked upon completion.
   *
   * @param contextual contains a context instance upon instantiation
   */
  def load(contextual: Contextual) = {
    val contextInstance = contextual.contextInstance
    contextInstance.outer = local.get()
    local.set(contextInstance)
  }

  /**
   * Opens a context instance and wraps any current instance to set back at close. It is preferable to use the wrap
   * method instead to verify close occurs at the end of instruction. All calls to this must be followed by a close()
   * invocation within a finally block.
   */
  def open() = {
    val contextInstance = new ContextInstance(local.get())
    local.set(contextInstance)
  }

  /**
   * Closes the current context instance. Must be coupled with an open() invocation. This method should be invoked
   * within a finally block to verify a context instance is always closed after being opened.
   */
  def close() = {
    val contextInstance = local.get()
    local.set(contextInstance.outer)
  }

  /**
   * Provides the functionality of open/close without the concern of having to make sure the instance is closed. The
   * function is invoked between open and close.
   *
   * @param f to invoke within the context
   * @tparam T the value returned by the function
   * @return T
   */
  def wrap[T](f: => T): T = {
    open()
    try {
      f
    } finally {
      close()
    }
  }

  def contextualize[T](contextual: Contextual)(f: => T): T = {
    load(contextual)
    try {
      f
    } finally {
      close()
    }
  }

  /**
   * Gets the value in the current context for name.
   *
   * @param name the key to look up
   * @tparam T the value to return
   * @return T
   */
  def apply[T](name: String) = contextInstance(name).asInstanceOf[T]

  /**
   * Gets the value back in an Option
   *
   * @param name the key to look up
   * @tparam T the value to return
   * @return Option[T]
   */
  def get[T](name: String) = contextInstance.get(name).asInstanceOf[Option[T]]

  /**
   * Gets the value back or returns the default.
   *
   * @param name the key to look up
   * @param default the value to return if the key is not found
   * @tparam T the value to return
   * @return T
   */
  def getOrElse[T](name: String, default: => T) = contextInstance.getOrElse(name, default).asInstanceOf[T]

  /**
   * Updates the value in the context
   *
   * @param name the key
   * @param value the new value to set
   */
  def update(name: String, value: Any) = contextInstance(name) = value

  /**
   * Updates the value in the context
   *
   * @param name the key
   * @param value the new value to set
   * @tparam T the type
   */
  def set[T](name: String, value: T) = contextInstance(name) = value
}

object Context {
  private var context: Context = _

  def apply() = context
}








