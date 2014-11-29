package org.hyperscala.web

import org.hyperscala.html.{HTMLCloner, HTMLTag}
import org.hyperscala.Container
import org.hyperscala.html.constraints.BodyChild
import com.outr.net.http.session.Session

/**
 * ScopedReplacement offers a convenient mechanism to scope an existing page element
 *
 * @author Matt Hicks <matt@outr.com>
 */
class ScopedReplacement[T <: HTMLTag, S <: Session](webpage: Webpage[S], scope: Scope, original: T, processor: T => Unit = (t: T) => Unit) {
  val originalParent = original.parent.asInstanceOf[Container[BodyChild]]
  original.removeFromParent()

  originalParent.contents += Scoped(scope, webpage) {
    val cloned = HTMLCloner.clone(original, idHandler = HTMLCloner.RetainIdHandler).asInstanceOf[T]
    processor(cloned)
    cloned
  }
}

object ScopedReplacement {
  def apply[T <: HTMLTag, S <: Session](webpage: Webpage[S], scope: Scope, original: T)(processor: T => Unit) = {
    new ScopedReplacement(webpage, scope, original, processor)
  }
}