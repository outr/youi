package org.hyperscala.jquery.ui

import com.outr.net.http.session.Session
import org.hyperscala.css.attributes.Display
import org.hyperscala.html._
import org.hyperscala.html.constraints.BodyChild
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.jquery.ui.event.ButtonClicked
import org.hyperscala.jquery.{JavaScriptCaller, jQueryComponent}
import org.hyperscala.realtime.Realtime
import org.hyperscala.selector.{AttributeMatcher, Selector}
import org.hyperscala.web._
import org.powerscala.StorageComponent
import org.powerscala.event.Listenable
import org.powerscala.hierarchy.event.StandardHierarchyEventProcessor
import org.powerscala.json.TypedSupport
import org.powerscala.property._
import org.hyperscala.jquery.dsl._

import scala.language.{implicitConversions, reflectiveCalls}

/**
 * @author Matt Hicks <matt@outr.com>
 */
object Dialog extends JavaScriptCaller with StorageComponent[Dialog, HTMLTag] {
  TypedSupport.register("dialogButtonClicked", classOf[ButtonClicked])

  implicit def tag2Dialog(tag: HTMLTag): Dialog = apply(tag)
  
  override def apply(tag: HTMLTag) = {
    tag.require(jQueryUI)
    tag.require(Realtime)
    super.apply(tag)
  }

  def assign(tag: HTMLTag,
             autoOpen: Boolean = true,
             buttons: List[String] = null,
             closeOnEscape: Boolean = true,
             closeText: String = "close",
             dialogClass: String = "",
             draggable: Boolean = true,
             height: Int = -1,
             hide: EffectInstance = null,
             maxHeight: Int = -1,
             maxWidth: Int = -1,
             minHeight: Int = 150,
             minWidth: Int = 150,
             modal: Boolean = false,
             position: String = "{ my: 'center', at: 'center', of: 'window', collision: 'none' }",
             resizable: Boolean = true,
             show: EffectInstance = null,
             title: String = "Dialog",
             width: Int = 300) = {
    val d = new Dialog(tag, autoInit = false)
    d.autoOpen := autoOpen
    d.buttons := buttons
    d.closeOnEscape := closeOnEscape
    d.closeText := closeText
    d.dialogClass := dialogClass
    d.draggable := draggable
    d.height := height
    d.hide := hide
    d.maxHeight := maxHeight
    d.maxWidth := maxWidth
    d.minHeight := minHeight
    d.minWidth := minWidth
    d.modal := modal
    d.position := position
    d.resizable := resizable
    d.show := show
    d.title := title
    d.width := width
    d.init()
    set(tag, d)
    d
  }
  
  protected def create(tag: HTMLTag) = new Dialog(tag, autoInit = true)

  private val buttonsConverter = (buttons: List[String]) => {
    JavaScriptString(buttons.map(b => s"'$b': function() { realtime.send({ id: $$(this).attr('id'), type: 'dialogButtonClicked', name: '$b'}) }").mkString("{ ", ", ", " }"))
  }

  def show(webpage: Webpage, title: String, content: BodyChild, width: Int = 300, height: Int = -1, modal: Boolean = true, buttons: List[String] = null)(f: String => Unit) = {
    webpage.body.contents += content
    val dialog = Dialog(content)
    dialog.title := title
    dialog.autoOpen := true
    dialog.buttons := buttons
    dialog.width := width
    dialog.height := height
    dialog.modal := modal
    dialog.buttonEvent.on {
      case evt => {
        f(evt.name)
        dialog.close()
        closed()
      }
    }
    dialog.closeEvent.on {
      case evt => closed()
    }

    def closed() = webpage.eval($(dialog.dialogSelector).remove())
  }
}

class Dialog private(val wrapped: HTMLTag, val autoInit: Boolean) extends jQueryComponent {
  def functionName = "dialog"

  wrapped.style.display := Display.None    // Keep the tag from appearing before it's ready.

  implicit def listenable: Listenable = wrapped

  val autoOpen = property("autoOpen", true)
  val buttons = property[List[String]]("buttons", null, toJS = Dialog.buttonsConverter)
  val closeOnEscape = property("closeOnEscape", true)
  val closeText = property("closeText", "close")
  val dialogClass = property("dialogClass", "")
  val draggable = property("draggable", true)
  val height = property("height", -1)
  val hide = property[EffectInstance]("hide", null)
  val maxHeight = property("maxHeight", -1)
  val maxWidth = property("maxWidth", -1)
  val minHeight = property("minHeight", 150)
  val minWidth = property("minWidth", 150)
  val modal = property("modal", false)
  val position = property("position", "{ my: 'center', at: 'center', of: 'window', collision: 'none' }")
  val resizable = property("resizable", true)
  val show = property[EffectInstance]("show", null)
  val title = property("title", getClass.getSimpleName)
  val width = property("width", 300)

  val isOpen = Property[Boolean](default = Option(false))

  lazy val dialogSelector = Selector.attribute("aria-describedby", AttributeMatcher.exactly, wrapped.identity, None)

  def open() = {
    call("open")
    isOpen := true
  }
  def close() = {
    call("close")
    isOpen := false
  }
  def destroy() = {
    call("destroy")
    isOpen := false
  }

  val openEvent = event("open")
  val closeEvent = event("close")
  val buttonEvent = new StandardHierarchyEventProcessor[ButtonClicked]("buttonEvent")
  wrapped.handle[ButtonClicked] {
    case evt => buttonEvent.fire(evt)
  }

  openEvent.on {
    case evt => isOpen := true
  }
  closeEvent.on {
    case evt => isOpen := false
  }

  def toggleOpen() = if (isOpen()) {
    close()
  } else {
    open()
  }
}