package org.hyperscala.intercept

import org.hyperscala.Markup
import annotation.tailrec
import org.powerscala.reflect._

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Intercepting {
  private var _interceptors = List.empty[Interceptor[_]]

  val interceptors = new {
    def +=(interceptor: Interceptor[_]) = synchronized {
      _interceptors = (interceptor :: _interceptors.reverse).reverse
    }

    def -=(interceptor: Interceptor[_]) = synchronized {
      _interceptors = _interceptors.filterNot(i => i == interceptor)
    }
  }

  def init(markup: Markup): Unit = invokeInit(markup, _interceptors)

  def before(markup: Markup): Unit = invokeBefore(markup, _interceptors)

  def after(markup: Markup): Unit = invokeAfter(markup, _interceptors)

  @tailrec
  private def invokeInit(markup: Markup, interceptors: List[Interceptor[_]]): Unit = {
    if (interceptors.nonEmpty) {
      val i = interceptors.head
      if (markup.getClass.hasType(i.manifest.erasure)) {
        i.asInstanceOf[Interceptor[Markup]].init(markup)
      }

      invokeInit(markup, interceptors.tail)
    }
  }

  @tailrec
  private def invokeBefore(markup: Markup, interceptors: List[Interceptor[_]]): Unit = {
    if (interceptors.nonEmpty) {
      val i = interceptors.head
      if (markup.getClass.hasType(i.manifest.erasure)) {
        i.asInstanceOf[Interceptor[Markup]].before(markup)
      }

      invokeBefore(markup, interceptors.tail)
    }
  }

  @tailrec
  private def invokeAfter(markup: Markup, interceptors: List[Interceptor[_]]): Unit = {
    if (interceptors.nonEmpty) {
      val i = interceptors.head
      if (markup.getClass.hasType(i.manifest.erasure)) {
        i.asInstanceOf[Interceptor[Markup]].after(markup)
      }

      invokeAfter(markup, interceptors.tail)
    }
  }
}
