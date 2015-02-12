package org.hyperscala.contenteditor

import com.outr.net.http.session.Session
import org.hyperscala.html._
import org.hyperscala.module.{Interface, Module}
import org.hyperscala.realtime._
import org.hyperscala.ui.Rangy
import org.hyperscala.web._
import org.hyperscala.javascript.dsl._
import org.powerscala.event.Listenable
import org.powerscala.{StorageComponent, Version}

/**
 * @author Matt Hicks <matt@outr.com>
 */
object ContentEditor extends Module with StorageComponent[ContentEditor, HTMLTag] {
  override val name = "contentEditor"

  override val version = Version(1)

  override def dependencies = List(Rangy, Realtime)

  override def init[S <: Session](website: Website[S]) = {
    website.register("/js/contenteditor.js", "contenteditor.js")
    website.register("/js/hyperscala-contenteditor.js", "hyperscala-contenteditor.js")
  }

  override def load[S <: Session](webpage: Webpage[S]) = {
    webpage.head.contents += new tag.Script(src = "/js/contenteditor.js")
    webpage.head.contents += new tag.Script(src = "/js/hyperscala-contenteditor.js")
  }

  override def apply(t: HTMLTag) = {
    t.require(this)
    super.apply(t)
  }

  protected def create(t: HTMLTag) = new ContentEditor(t)
}

class ContentEditor private(val container: HTMLTag) extends Listenable {
  def toggleBold() = send(s"ContentEditor.byId('${container.identity}').toggleBold();")

  private def send(js: String) = container.connected[Webpage[Session]] {
    case webpage => webpage.eval(js)
  }
}