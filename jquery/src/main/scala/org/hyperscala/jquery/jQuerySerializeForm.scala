package org.hyperscala.jquery

import com.outr.net.http.session.Session
import org.hyperscala.module.Module
import org.hyperscala.html._
import org.hyperscala.web.{Webpage, Website}
import org.powerscala.Version

/**
 * @author Matt Hicks <matt@outr.com>
 */
object jQuerySerializeForm extends Module {
  val name = "jquery-serializeform"
  val version = Version(1)

  override def init(website: Website) = {
    website.register("/js/jquery.serializeform.js", "jquery.serializeform.js")
  }

  override def load(webpage: Webpage) = {
    webpage.head.contents += new tag.Script(src = "/js/jquery.serializeform.js")
  }
}
