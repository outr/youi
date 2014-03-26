package org.hyperscala.web.module

import org.hyperscala.web.{Website, Webpage}
import org.hyperscala.html.HTMLTag
import org.hyperscala.IdentifiableTag
import org.powerscala.{Unique, Version}
import org.hyperscala.module._
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <matt@outr.com>
 */
object IdentifyTags extends Module {
  def name = "identifytags"

  def version = Version(1)

  def init[S <: Session](website: Website[S]) = {}

  def load[S <: Session](webpage: Webpage[S]) = {
    // TODO: only apply to body, not head
    webpage.html.byTag[IdentifiableTag].foreach {
      case t => if (t.id() == null) {
        t.identity
      }
    }
    webpage.intercept.init.on {
      case tag: HTMLTag => {
        if (tag.id() == null) {
          tag.id := Unique()
        }
      }
      case _ => // Ignore
    }
  }
}
