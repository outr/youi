package org.hyperscala.ui.widgets

import org.hyperscala.module._
import org.powerscala.Version
import org.hyperscala.web.{Website, Webpage}
import org.hyperscala.html.tag
import org.hyperscala.realtime.Realtime
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <matt@outr.com>
 */
object CKEditor extends Module {
  def name = "ckeditor"

  def version = Version(4, 4, 5)

  override def dependencies = List(Realtime)

  override def init(website: Website) = {}

  override def load(webpage: Webpage) = {
    webpage.head.contents += new tag.Script(mimeType = "text/javascript", src = s"//cdn.ckeditor.com/$version/full/ckeditor.js")
  }
}