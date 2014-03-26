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

  def version = Version(4, 3, 2)

  override def dependencies = List(Realtime)

  override def init[S <: Session](website: Website[S]) = {
    website.addClassPath("/cke/", "ckeditor-4.3.2/")
  }

  override def load[S <: Session](webpage: Webpage[S]) = {
    webpage.head.contents += new tag.Script(mimeType = "text/javascript", src = "/cke/ckeditor.js")
  }
}