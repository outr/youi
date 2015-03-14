package org.hyperscala.ui.module

import org.hyperscala.module.Module
import org.powerscala.Version
import org.hyperscala.web.{Website, Webpage}
import org.hyperscala.html.HTMLTag
import org.hyperscala.selector.Selector
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <matt@outr.com>
 */
object ExternalStyle extends Module {
  val name = "external-style"
  val version = Version(1)

  override def init(website: Website) = {}

  override def load(webpage: Webpage) = {
    webpage.byTag[HTMLTag].foreach(t => convertToSelector(webpage, t))      // Convert all tags to use selector
    webpage.intercept.init.on {                            // Convert all future tags to use selector
      case t: HTMLTag => convertToSelector(webpage, t)
    }
  }

  def convertToSelector(webpage: Webpage, t: HTMLTag) = if (t.style.selector == null) {     // Local style sheet, must be changed
    val styleSheet = webpage.head.selector(Selector.id(t))      // Based on the id as of render-time
    styleSheet(t.style)                                      // Apply existing CSS from the local style sheet
    t.styleProperty := styleSheet                            // Swap the property value so all future style changes are applied to the selector
  }
}
