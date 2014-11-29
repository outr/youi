package org.hyperscala.web

import org.hyperscala.XMLContent
import org.hyperscala.html.HTMLTag
import org.hyperscala.html.constraints.BodyChild
import org.hyperscala.io.HTMLWriter
import com.outr.net.http.session.Session
import org.powerscala.hierarchy.MutableChildLike

/**
 * Scoped provides a mechanism to provide sections of a page that scoped differently than the rest of the page.
 *
 * Request: Created per load.
 * Page: Created per page
 *
 * @author Matt Hicks <matt@outr.com>
 */
class Scoped[T <: HTMLTag, S <: Session](scope: Scope, creator: () => T, webpage: Webpage[S]) extends BodyChild {
  private def website = webpage.website

  identity      // Give this element a unique id

  private def createAndAssign() = {
    val t = creator()
    MutableChildLike.assignParent(t, this)
    t
  }

  def apply() = scope match {
    case Scope.Request => website.request.store.getOrSet(s"scoped$identity", createAndAssign())
    case Scope.Page => webpage.store.getOrSet(s"scoped$identity", createAndAssign())      // TODO: fix - this will only work if the scope of the page is page
    case Scope.Application => website.application.getOrSet(s"scoped$identity", createAndAssign())
    case Scope.Session => website.session.getOrSet(s"scoped$identity", createAndAssign())
  }

  override def xmlLabel = apply().xmlLabel

  override def xmlChildren = List(apply())

  override def write(writer: HTMLWriter) = apply().write(writer)
}

object Scoped {
  def apply[T <: HTMLTag, S <: Session](scope: Scope, webpage: Webpage[S])(creator: => T) = new Scoped[T, S](scope, () => creator, webpage)
}

// TODO: scoped blocks of code that get invoked each time that scope is created?