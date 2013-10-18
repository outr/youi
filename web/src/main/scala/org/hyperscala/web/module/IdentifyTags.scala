package org.hyperscala.web.module

import org.hyperscala.web.Webpage
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
    page.html.byTag[IdentifiableTag].foreach {
      case t => if (t.id() == null) {
        t.identity
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
