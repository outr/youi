package org.hyperscala.jquery.ui

import event.ButtonClicked
import org.hyperscala.html._
import org.hyperscala.web._
import org.powerscala.property._
import org.powerscala.event.{Intercept, Listenable}
import org.hyperscala.realtime.Realtime
import org.hyperscala.jquery.{jQueryComponent, JavaScriptCaller}

import language.reflectiveCalls
import org.powerscala.hierarchy.event.StandardHierarchyEventProcessor
import org.powerscala.StorageComponent

import scala.language.implicitConversions
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.css.attributes.Display
import com.outr.net.http.session.Session
import org.hyperscala.html.constraints.BodyChild
import org.hyperscala.selector.{AttributeMatcher, Selector}

/**
 * @author Matt Hicks <matt@outr.com>
 */
object Dialog extends JavaScriptCaller with StorageComponent[Dialog, HTMLTag] {
  implicit def tag2Dialog(tag: HTMLTag) = apply(tag)
  
  override def apply(tag: HTMLTag) = {
    tag.require(jQueryUI.LatestWithDefault)
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
    JavaScriptString(buttons.map(b => s"'$b': function() { realtimeSend($$(this).attr('id'), 'buttonClicked', { 'name': '$b' }); }").mkString("{ ", ", ", " }"))
  }

  def show[S <: Session](webpage: Webpage[S], title: String, content: BodyChild, width: Int = 300, height: Int = -1, modal: Boolean = true, buttons: List[String] = null)(f: String => Unit) = {
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

    def closed() = {
      Realtime.sendJavaScript(webpage, s"$$('${dialog.dialogSelector.value}').remove();")
    }
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

  val openEvent = event("open")
  val closeEvent = event("close")
  val buttonEvent = new StandardHierarchyEventProcessor[ButtonClicked]("buttonEvent")
  wrapped.eventReceived.on {
    case evt if evt.event == "buttonClicked" => {
      buttonEvent.fire(ButtonClicked(evt.json.string("name")))
      Intercept.Stop
    }
    case _ => Intercept.Continue
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