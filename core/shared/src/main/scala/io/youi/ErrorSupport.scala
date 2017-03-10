package io.youi

import reactify.Channel

trait ErrorSupport {
  def error(t: Throwable): Unit = ErrorSupport.error := t

  def errorSupport[R](f: => R): R = try {
    f
  } catch {
    case t: Throwable => {
      error(t)
      throw t
    }
  }
}

object ErrorSupport {
  val error: Channel[Throwable] = Channel[Throwable]

  val defaultHandler: (Throwable) => Unit = error.attach {
    case t: JavaScriptError => scribe.error(t.message)
    case t: Throwable => scribe.error(t)
  }
}