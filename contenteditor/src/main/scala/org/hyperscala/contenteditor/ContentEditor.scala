package org.hyperscala.contenteditor

import com.outr.net.http.session.Session
import org.hyperscala.css.Style
import org.hyperscala.html._
import org.hyperscala.javascript.JavaScriptContent
import org.hyperscala.module.Module
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
  def toggle[T, S <: Style[T]](style: S, value: T) = call(s"toggle('${style.cssName}', '${style.value(value)}')")
  def set[T, S <: Style[T]](style: S, value: T) = call(s"set('${style.cssName}', '${style.value(value)}')")
  def insertImage(url: String) = call(s"insert('img', { src: '$url' })")

  def indent() = send(ifFocused(document.execCommand(Command.indent)))
  def unIndent() = send(ifFocused(document.execCommand(Command.outdent)))

  private def ifFocused[R](statement: Statement[R]) = MultiStatement(sideEffects = true, s"if (ContentEditor.byId('${container.identity}').focused()) { ", statement, " }")
  private def call(function: String) = send(s"ContentEditor.byId('${container.identity}').$function;")
  private def send(js: JavaScriptContent) = container.connected[Webpage[Session]] {
    case webpage => webpage.eval(js)
  }
}