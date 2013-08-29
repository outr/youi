package org.hyperscala.jquery.ui

import event.ButtonClicked
import org.hyperscala.html._
import org.hyperscala.web.site.Webpage
import org.powerscala.property._
import org.powerscala.event.Listenable
import org.hyperscala.realtime.Realtime
import org.hyperscala.jquery.{jQueryComponent, JavaScriptCaller}

import language.reflectiveCalls
import org.powerscala.hierarchy.event.StandardHierarchyEventProcessor
import org.powerscala.StorageComponent

import scala.language.implicitConversions

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
}

class Dialog private(val html: HTMLTag) extends jQueryComponent {
  def functionName = "dialog"

  implicit def listenable: Listenable = html

  val autoOpen = property("autoOpen", true)
  val buttons = property[List[String]]("buttons", null)
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

  def open() = call("open")
  def close() = call("close")

  def toggleOpen() = if (isOpen()) {
    close()
  } else {
    open()
  }

  val buttonEvent = new StandardHierarchyEventProcessor[ButtonClicked]("buttonEvent")
}
/*
// TODO: convert to using jQueryComponent
trait Dialog extends HTMLTag with JavaScriptCaller {
  identity    // Make sure it has an id

  Webpage().require(Realtime)
  Webpage().require(jQueryUI.LatestWithDefault)

  style.display := Display.None

  private var _open = false

  private var generated = false

  val dialog = new {
    val buttonEvent = new StandardHierarchyEventProcessor[ButtonClicked]("buttonEvent")

    val autoOpen = property("autoOpen", true)
    val buttons = property("buttons", null)
    val closeOnEscape = property("closeOnEscape", true)
    val closeText = property("closeText", "close")
    val dialogClass = property("dialogClass", "")
    val draggable = property("draggable", true)
    val height = property("height", -1)
    val hide = property("hide", null)
    val maxHeight = property("maxHeight", -1)
    val maxWidth = property("maxWidth", -1)
    val minHeight = property("minHeight", 150)
    val minWidth = property("minWidth", 150)
    val modal = property("modal", false)
    val position = property("position", "{ my: 'center', at: 'center', of: 'window', collision: 'none' }")
    val resizable = property("resizable", true)
    val show = property("show", null)
    val title = property("title", getClass.getSimpleName)
    val width = property("width", 300)

    val properties = List(autoOpen, buttons, closeOnEscape, closeText, dialogClass, draggable, height, hide, maxHeight, maxWidth, minHeight, minWidth, modal, position, resizable, show, title, width)

    def close() = {
      injectScript("$('#%s').dialog('close');".format(id()))
      _open = false
    }

    def destroy() = injectScript("$('#%s').dialog('destroy');".format(id()))

    def isOpen = _open

    def moveToTop() = injectScript("$('#%s').dialog('moveToTop');".format(id()))

    def open() = {
      injectScript("$('#%s').dialog('open');".format(id()))
      _open = true
    }

    def toggleOpen() = if (isOpen) {
      close()
    } else {
      open()
    }

    // TODO: hook up events

    Listenable.listenTo[PropertyChangeEvent[_], Unit, Unit]("change", properties: _*)() {
      case evt => if (generated) {
        injectScript("$('#%s').dialog('option', '%s', %s);".format(id(), evt.property.asInstanceOf[DialogProperty[_]].name, value2String(evt.newValue)))
      }
    }
  }

  override protected def before() = {
    super.before()

    Realtime.sendJavaScript(generateScript(), forId = id(), onlyRealtime = false)
  }

  private def generateScript() = {
    generated = true
    _open = dialog.autoOpen()
    val options = dialog.properties.collect {
      case p: DialogProperty[_] if p.modified && p.isInstanceOf[DialogProperty[_]] => "%s: %s".format(p.name, value2String(p()))
    }.mkString(",\n    ")
    """
      |$(function() {
      | $('#%s').dialog({
      |   %s
      | });
      |});
    """.stripMargin.format(id(), options)
  }

  override protected def value2String(v: Any): String = v match {
    case db: DialogButtons => "{ %s }".format(db.names.map(name => "'%1$s': function() { jsFire($(this), 'buttonClicked', { 'name': '%1$s' }); }".format(name)).mkString(", "))
    case _ => super.value2String(v)
  }

  override def receive(event: String, message: Message) = event match {
    case "buttonClicked" => dialog.buttonEvent.fire(ButtonClicked(message[String]("name")))
    case _ => super.receive(event, message)
  }
}

class DialogProperty[T](val name: String, default: Option[T])(implicit listenable: Listenable, manifest: Manifest[T]) extends Property[T](default = default) {
  def modified = value != default.getOrElse(null)
}

case class DialogButtons(names: String*)*/