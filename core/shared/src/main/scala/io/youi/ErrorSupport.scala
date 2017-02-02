package io.youi

import com.outr.scribe.logger

trait ErrorSupport {
  def error(t: Throwable): Unit = logger.error(t)

  def errorSupport[R](f: => R): R = try {
    f
  } catch {
    case t: Throwable => {
      error(t)
      throw t
    }
  }
}