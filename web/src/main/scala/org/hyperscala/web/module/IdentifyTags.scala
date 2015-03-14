package org.hyperscala.web.module

import org.hyperscala.web.{Website, Webpage}
import org.hyperscala.IdentifiableTag
import org.powerscala.{Storage, Unique, Version}
import org.hyperscala.module._
import com.outr.net.http.session.Session

import scala.annotation.tailrec

/**
 * @author Matt Hicks <matt@outr.com>
 */
object IdentifyTags extends Module {
  def name = "identifytags"

  def version = Version(1)

  def init(website: Website) = {}

  def load(webpage: Webpage) = {
    // TODO: only apply to body, not head
    webpage.html.byTag[IdentifiableTag].foreach {
      case t => if (t.id() == null && shouldIdentify(t)) {
        t.identity
      }
    }
    webpage.intercept.init.on {
      case tag: IdentifiableTag => {
        if (tag.id() == null && shouldIdentify(tag)) {
          tag.id := Unique()
        }
      }
      case _ => // Ignore
    }
  }

  @tailrec
  final def shouldIdentify(t: IdentifiableTag): Boolean = if (Storage.get(t, "doNotIdentifyChildren").nonEmpty) {
    false
  } else if (t.parent == null || !t.parent.isInstanceOf[IdentifiableTag]) {
    true
  } else {
    shouldIdentify(t.parent.asInstanceOf[IdentifiableTag])
  }

  def excludeChildren(t: IdentifiableTag) = Storage.set(t, "doNotIdentifyChildren", "true")
}
