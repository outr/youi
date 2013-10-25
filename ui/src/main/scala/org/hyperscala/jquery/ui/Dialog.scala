package org.hyperscala.jquery.ui

import event.ButtonClicked
import org.hyperscala.html._
import org.hyperscala.web.Webpage
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

/**
 * @author Matt Hicks <matt@outr.com>
 */
object Dialog extends JavaScriptCaller with StorageComponent[Dialog, HTMLTag] {
  implicit def tag2Dialog(tag: HTMLTag) = apply(tag)
  
  override def apply(tag: HTMLTag) = {
    Webpage().require(jQueryUI.LatestWithDefault)
    Webpage().require(Realtime)
    super.apply(tag)
  }
  
  protected def create(tag: HTMLTag) = new Dialog(tag)

  private val buttonsConverter = (buttons: List[String]) => {
    JavaScriptString(buttons.map(b => s"'$b': function() { realtimeSend($$(this).attr('id'), 'buttonClicked', { 'name': '$b' }); }").mkString("{ ", ", ", " }"))
  }
}

class Dialog private(val wrapped: HTMLTag) extends jQueryComponent {
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
      buttonEvent.fire(ButtonClicked(evt.message[String]("name")))
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