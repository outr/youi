package org.hyperscala.web.module

import org.hyperscala.web.site.Webpage
import org.hyperscala.html.HTMLTag
import org.hyperscala.{IdentifiableTag, Unique}
import org.powerscala.Version
import org.hyperscala.module._

/**
 * @author Matt Hicks <matt@outr.com>
 */
object IdentifyTags extends Module {
  def name = "identifytags"

  def version = Version(1)

  def init() = {}

  def load() = {
    val page = Webpage()
    page.view.foreach {
      case tag: IdentifiableTag => if (tag.id() == null) {
        tag.id := Unique()
      }
    }
    page.intercept.init.on {
      case tag: HTMLTag => {
        if (tag.id() == null) {
          tag.id := Unique()
        }
      }
      case _ => // Ignore
    }
  }
}
