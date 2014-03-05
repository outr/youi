package org.hyperscala.ui.module

import org.hyperscala.module.Module
import org.powerscala.Version
import org.hyperscala.web.Webpage
import org.hyperscala.html.HTMLTag
import org.hyperscala.selector.Selector

/**
 * @author Matt Hicks <matt@outr.com>
 */
object ExternalStyle extends Module {
  val name = "external-style"
  val version = Version(1)

  override def init() = {}

  override def load() = {
    val page = Webpage()
    page.byTag[HTMLTag].foreach(convertToSelector)      // Convert all tags to use selector
    page.intercept.init.on {                            // Convert all future tags to use selector
      case t: HTMLTag => convertToSelector(t)
    }
  }

  def convertToSelector(t: HTMLTag) = if (t.style.selector == null) {     // Local style sheet, must be changed
    val page = Webpage()
    val styleSheet = page.head.selector(Selector.id(t))      // Based on the id as of render-time
    styleSheet(t.style)                                      // Apply existing CSS from the local style sheet
    t.styleProperty := styleSheet                            // Swap the property value so all future style changes are applied to the selector
  }
}
