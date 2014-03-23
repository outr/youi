package org.hyperscala.web

import org.hyperscala.html.HTMLTag
import org.hyperscala.html.constraints.BodyChild
import org.hyperscala.io.HTMLWriter

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Scoped[T <: HTMLTag](scope: Scope, creator: () => T) extends BodyChild {
  identity      // Give this element a unique id

  def apply() = scope match {
    case Scope.Request | Scope.Page => Website().request.store.getOrSet(s"scoped$identity", creator())      // TODO: this won't work for realtime
    case Scope.Application => Website().application.getOrSet(s"scoped$identity", creator())
    case Scope.Session => Website().session.getOrSet(s"scoped$identity", creator())
  }

  override def xmlLabel = apply().xmlLabel

  override def write(writer: HTMLWriter) = apply().write(writer)
}

object Scoped {
  def apply[T <: HTMLTag](scope: Scope)(creator: => T) = new Scoped[T](scope, () => creator)
}

// TODO: scoped blocks of code that get invoked each time that scope is created?