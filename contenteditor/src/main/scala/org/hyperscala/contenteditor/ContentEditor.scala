package org.hyperscala.contenteditor

import com.outr.net.http.session.Session
import org.hyperscala.Container
import org.hyperscala.css.Style
import org.hyperscala.css.attributes.Alignment
import org.hyperscala.event.BrowserEvent
import org.hyperscala.html._
import org.hyperscala.io.HTMLToScala
import org.hyperscala.javascript.JavaScriptContent
import org.hyperscala.module.Module
import org.hyperscala.realtime._
import org.hyperscala.ui.Rangy
import org.hyperscala.web._
import org.hyperscala.javascript.dsl._
import org.powerscala.event.Listenable
import org.powerscala.event.processor.UnitProcessor
import org.powerscala.json.TypedSupport
import org.powerscala.property.Property
import org.powerscala.{StorageComponent, Version}

/**
 * @author Matt Hicks <matt@outr.com>
 */
object ContentEditor extends Module with StorageComponent[ContentEditor, HTMLTag] {
  TypedSupport.register("contentChanged", classOf[ContentChangedEvent])

  override val name = "contentEditor"

  override val version = Version(1)

  override def dependencies = List(Rangy, Realtime)

  override def init(website: Website) = {
    website.register("/js/contenteditor.js", "contenteditor.js")
    website.register("/js/hyperscala-contenteditor.js", "hyperscala-contenteditor.js")
  }

  override def load(webpage: Webpage) = {
    webpage.head.contents += new tag.Script(src = "/js/contenteditor.js")
    webpage.head.contents += new tag.Script(src = "/js/hyperscala-contenteditor.js")
  }

  override def apply(t: HTMLTag) = {
    t.require(this)
    super.apply(t)
  }

  protected def create(t: HTMLTag) = new ContentEditor(t)

  val PixelConversion = JSFunction1[String, String](
    """if (isNaN(p1)) {
      |  return p1;
      |} else {
      |  return p1 + 'px';
      |}""".stripMargin)
}

class ContentEditor private(val container: HTMLTag) extends Listenable {
  /**
   * Auto applies the new value to the container when html is modified.
   *
   * Defaults to false.
   */
  val autoApply = Property[Boolean](default = Some(false))

  /**
   * Fired when contents are modified in the editor.
   */
  val change = new UnitProcessor[ContentChangedEvent]("change")

  /**
   * The current value of the editor.
   */
  val html = Property[String](default = Option(container.outputChildrenString))

  container.handle[ContentChangedEvent] {   // Re-fire change event received on the container
    case evt => {
      RealtimePage.dontSend {
        html := evt.newValue
      }
      change.fire(evt)
    }
  }
  html.change.on {
    case evt => if (autoApply()) {
      HTMLToScala.replaceChildren(container.asInstanceOf[HTMLTag with Container[_ <: HTMLTag]], html())
    }
  }

  def toggle[T, S <: Style[T]](style: S, value: T, reverse: T, tagName: String = "span") = call(s"toggle('${style.cssName}', '${style.value(value)}', '${style.value(reverse)}', '$tagName')")
  def set[T, S <: Style[T]](style: S, value: T, tagName: String = "span") = call(s"set('${style.cssName}', '${style.value(value)}', '$tagName')")

  def insert(t: HTMLTag) = insertHTML(t.outputString.trim)

  def insertHTML(html: String) = call(s"insertHTML(${JavaScriptContent.toJS(html)})")

  def wrap(t: HTMLTag) = {
    val entries = t.attributes.collect {
      case (name, attribute) if name != "id" || attribute.attributeValue.nonEmpty => s"$name: '${attribute.attributeValue}'"
    }.mkString("{", ", ", "}")
    call(s"wrap('${t.xmlLabel}', $entries)")
  }

  def wrapHTML(t: HTMLTag) = call(s"wrapHTML(${JavaScriptContent.toJS(t.outputString)})")

  def indent() = exec(Command.indent)
  def unIndent() = exec(Command.outdent)

  def orderedList() = exec(Command.insertOrderedList)
  def unorderedList() = exec(Command.insertUnorderedList)

  def align(alignment: Alignment) = alignment match {
    case Alignment.Left => exec(Command.justifyLeft)
    case Alignment.Center => exec(Command.justifyCenter)
    case Alignment.Right => exec(Command.justifyRight)
    case Alignment.Justify => exec(Command.justifyFull)
    case Alignment.Inherit => // Ignore
  }

  def clearFormatting() = call("clearFormatting()")

  def bindStyle[T](t: HTMLTag, style: Style[T], styleToSet: Style[T], default: T, document: Statement[HTMLTag] = document) = {
    val js =
      s"""ContentEditor.on('styleChanged', function(evt) {
        | var tag = ${document.content}.jQuery('#${t.identity}');
        | var value = evt.style['${style.cssName}'];
        | if (value == null || value == '') {
        |   value = '${style.persistence.toString(default, style.name, default.getClass)}';
        | }
        | tag.css('${styleToSet.cssName}', value);
        |});""".stripMargin
    send(js)
  }

  def bindInput[S <: Style[_]](t: HTMLTag, style: S, document: Statement[HTMLTag] = document, valueCleaner: JSFunction1[String, String] = null, tagName: String = "span") = {
    val cleanerFunction = if (valueCleaner != null) valueCleaner.content else "null"
    send(s"ContentEditor.bindInput('${t.identity}', '${style.cssName}', ${document.content}, $cleanerFunction, '$tagName');")
  }

  def bindFontStyle(t: HTMLTag, document: Statement[HTMLTag] = document) = {
    send(s"ContentEditor.bindFontStyle('${t.identity}', ${document.content});")
  }

  def bindToggle[T, S <: Style[T]](t: HTMLTag, style: S, value: T, reverse: T, tagName: String = "span") = t.clickEvent.onRealtime {
    case evt => toggle(style, value, reverse, tagName)
  }

  def exec(command: Command) = {
    send(ifFocused(document.execCommand(command)))
  }

  def adjustStyle[S](style: Style[S], adjuster: JSFunction1[S, S], tagName: String = "span") = {
    call(s"adjustStyle('${style.name}', ${adjuster.content}, '$tagName')")
  }

  def adjustStyleSize[S](style: Style[S], adjustment: Int, min: Int = 0, max: Int = Int.MaxValue, tagName: String = "span")(implicit manifest: Manifest[S]) = {
    val js =
      """
        |var regex = /(-?\d+[.]?\d*)(.*)/;
        |var result = regex.exec(p1);
        |var n = parseInt(result[1]);
        |var type = result[2];
        |var value = n + $adjustment
        |if (value < $min) {
        | value = $min
        |}
        |if (value > $max) {
        | value = $max
        |}
        |return value + type;
      """.stripMargin.replaceAll("[$]adjustment", adjustment.toString).replaceAll("[$]min", min.toString).replaceAll("[$]max", max.toString)
    adjustStyle[S](style, JSFunction1[S, S](js), tagName)
  }


  private def ifFocused[R](statement: Statement[R]) = MultiStatement(sideEffects = true, s"if (ContentEditor.byId('${container.identity}').focused()) { ", statement, " }")
  private def call(function: String) = send(s"ContentEditor.byId('${container.identity}').$function;")
  private def send(js: JavaScriptContent) = container.connected[Webpage] {
    case webpage => webpage.eval(js)
  }

  send(s"ContentEditor.byId('${container.identity}')")    // Initialize it
}

case class ContentChangedEvent(tag: HTMLTag, oldValue: String, newValue: String) extends BrowserEvent