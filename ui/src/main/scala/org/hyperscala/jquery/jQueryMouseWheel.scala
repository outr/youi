package org.hyperscala.jquery

import com.outr.net.http.session.Session
import org.hyperscala.event.MouseWheelEvent
import org.hyperscala.html.tag
import org.hyperscala.javascript.dsl.{JSFunction1, WrappedStatement}
import org.hyperscala.jquery.dsl.jQuerySelector
import org.hyperscala.module.Module
import org.hyperscala.web.{Webpage, Website}
import org.powerscala.Version

/**
 * @author Matt Hicks <matt@outr.com>
 */
class jQueryMouseWheel(selector: jQuerySelector) {
  def mouseWheel(f: JSFunction1[MouseWheelEvent, Boolean]) = {
    WrappedStatement[Unit](s"${selector.content}.mousewheel(", f, ")", sideEffects = true)
  }
}

object jQueryMouseWheel extends Module {
  val name = "jquery.mousewheel"

  val version = Version(3, 1, 9)

  override def dependencies = List(jQuery)

  override def init[S <: Session](website: Website[S]) = {
    website.register("/js/jquery.mousewheel.js", "jquery.mousewheel.js")
  }

  override def load[S <: Session](webpage: Webpage[S]) = {
    webpage.head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/jquery.mousewheel.js")
  }
}
