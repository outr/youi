package org.hyperscala.web.module

import org.hyperscala.web.site.Webpage
import org.hyperscala.html.HTMLTag
import org.hyperscala.Unique
import org.powerscala.Version

/**
 * @author Matt Hicks <matt@outr.com>
 */
object IdentifyTags extends Module {
  def name = "identifytags"

  def version = Version(1)

  def load(page: Webpage) = {
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
