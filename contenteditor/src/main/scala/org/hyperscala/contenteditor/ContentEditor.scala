package org.hyperscala.contenteditor

import com.outr.net.http.session.Session
import org.hyperscala.css.Style
import org.hyperscala.css.attributes.Alignment
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

  def insert(t: HTMLTag) = {
    val entries = t.attributes.collect {
      case (name, attribute) if name != "id" || attribute.attributeValue.nonEmpty => s"$name: '${attribute.attributeValue}'"
    }.mkString("{", ", ", "}")
    call(s"insert('${t.xmlLabel}', $entries)")
  }

  def wrap(t: HTMLTag) = {
    val entries = t.attributes.collect {
      case (name, attribute) if name != "id" || attribute.attributeValue.nonEmpty => s"$name: '${attribute.attributeValue}'"
    }.mkString("{", ", ", "}")
    call(s"wrap('${t.xmlLabel}', $entries)")
  }

  def indent() = sendCommand(Command.indent)
  def unIndent() = sendCommand(Command.outdent)

  def orderedList() = sendCommand(Command.insertOrderedList)
  def unorderedList() = sendCommand(Command.insertUnorderedList)

  def align(alignment: Alignment) = alignment match {
    case Alignment.Left => sendCommand(Command.justifyLeft)
    case Alignment.Center => sendCommand(Command.justifyCenter)
    case Alignment.Right => sendCommand(Command.justifyRight)
    case Alignment.Justify => sendCommand(Command.justifyFull)
  }

  def clearFormatting() = call("clearFormatting()")

  def bind[S <: Style[_]](t: HTMLTag, style: S, document: Statement[HTMLTag] = document, valueCleaner: JSFunction1[String, String] = null) = {
    val cleanerFunction = if (valueCleaner != null) valueCleaner.content else "null"
    send(s"ContentEditor.bind('${t.identity}', '${style.cssName}', ${document.content}, $cleanerFunction);")
  }

  def bindFontStyle(t: HTMLTag, document: Statement[HTMLTag] = document) = {
    send(s"ContentEditor.bindFontStyle('${t.identity}', ${document.content});")
  }

  private def ifFocused[R](statement: Statement[R]) = MultiStatement(sideEffects = true, s"if (ContentEditor.byId('${container.identity}').focused()) { ", statement, " }")
  private def call(function: String) = send(s"ContentEditor.byId('${container.identity}').$function;")
  private def send(js: JavaScriptContent) = container.connected[Webpage[Session]] {
    case webpage => webpage.eval(js)
  }
  private def sendCommand(command: Command) = {
    send(ifFocused(document.execCommand(command)))
  }

  send(s"ContentEditor.byId('${container.identity}')")    // Initialize it
}