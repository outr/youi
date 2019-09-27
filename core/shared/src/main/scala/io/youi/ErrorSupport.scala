package io.youi

import java.io.IOException

import io.youi.net.MalformedURLException
import reactify.Channel
import reactify.reaction.Reaction

trait ErrorSupport {
  def error(t: Throwable): Unit = ErrorSupport.error @= t

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

  val defaultHandler: Reaction[Throwable] = error.attach {
    case exc: IOException if exc.getMessage == "Connection reset by peer" => scribe.warn(exc.getMessage)
    case exc: IOException if exc.getMessage == "Broken pipe" => scribe.warn(exc.getMessage)
    case exc: MalformedURLException => scribe.warn(exc.message)
    case exc: MessageException => scribe.error(exc.message)
    case t: Throwable => scribe.error(t)
  }
}