package org.hyperscala.web.module

import org.hyperscala.web.site.Webpage
import org.hyperscala.html.HTMLTag
import org.hyperscala.Unique

/**
 * @author Matt Hicks <matt@outr.com>
 */
object IdentifyTags extends Module {
  def name = "identifytags"

  def version = "1.0"

  protected[web] def load(page: Webpage) = {
    page.view.foreach {
      case tag => if (tag.id() == null) {
        tag.id := Unique()
      }
    }
    page.intercept.init {
      case tag: HTMLTag => {
        if (tag.id() == null) {
          tag.id := Unique()
        }
      }
    }
  }
}
