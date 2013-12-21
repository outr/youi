package org.hyperscala.jquery

import org.hyperscala.module.Module
import org.powerscala.Version
import org.hyperscala.web.{Webpage, Website}
import org.hyperscala.html.tag
import org.hyperscala.jquery.dsl.jQuerySelector
import org.hyperscala.javascript.dsl.{WrappedStatement, JSFunction1}
import org.hyperscala.event.MouseWheelEvent

/**
 * @author Matt Hicks <matt@outr.com>
 */
class jQueryMouseWheel(selector: jQuerySelector) {
  Webpage().require(jQueryNearest)

  def mouseWheel(f: JSFunction1[MouseWheelEvent, Boolean]) = {
    WrappedStatement[Unit](s"${selector.content}.mousewheel(", f, ")", sideEffects = true)
  }
}

object jQueryMouseWheel extends Module {
  val name = "jquery.mousewheel"

  val version = Version(3, 1, 9)

  override def dependencies = List(jQuery.LatestWithDefault)

  def init() = {
    Website().register("/js/jquery.mousewheel.js", "jquery.mousewheel.js")
  }

  def load() = {
    val page = Webpage()
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/jquery.mousewheel.js")
  }
}
