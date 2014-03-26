package org.hyperscala.web

import org.hyperscala.html.HTMLTag
import org.hyperscala.html.constraints.BodyChild
import org.hyperscala.io.HTMLWriter
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Scoped[T <: HTMLTag, S <: Session](scope: Scope, creator: () => T, website: Website[S]) extends BodyChild {
  identity      // Give this element a unique id

  def apply() = scope match {
    case Scope.Request | Scope.Page => website.request.store.getOrSet(s"scoped$identity", creator())      // TODO: this won't work for realtime
    case Scope.Application => website.application.getOrSet(s"scoped$identity", creator())
    case Scope.Session => website.session.getOrSet(s"scoped$identity", creator())
  }

  override def xmlLabel = apply().xmlLabel

  override def write(writer: HTMLWriter) = apply().write(writer)
}

object Scoped {
  def apply[T <: HTMLTag, S <: Session](scope: Scope, website: Website[S])(creator: => T) = new Scoped[T, S](scope, () => creator, website)
}

// TODO: scoped blocks of code that get invoked each time that scope is created?