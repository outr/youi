package io.youi

import com.outr.reactify.Channel

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

  val defaultHandler: (Throwable) => Unit = error.attach(scribe.error(_))
}