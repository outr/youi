package org.hyperscala.jquery.ui

import event.ButtonClicked
import org.hyperscala.html._
import org.hyperscala.web.site.Webpage
import org.powerscala.property._
import org.powerscala.event.Listenable
import org.powerscala.property.event.PropertyChangeEvent
import org.hyperscala.realtime.Realtime
import org.hyperscala.css.attributes.Display
import org.hyperscala.jquery.JavaScriptCaller
import org.hyperscala.Message

import language.reflectiveCalls
import org.powerscala.hierarchy.event.StandardHierarchyEventProcessor

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait Dialog extends HTMLTag with JavaScriptCaller {
  identity    // Make sure it has an id

  Webpage().require(Realtime)
  Webpage().require(jQueryUI, jQueryUI191)

  style.display = Display.None

  private var _open = false

  private var generated = false

  val dialog = new {
    val buttonEvent = new StandardHierarchyEventProcessor[ButtonClicked]("buttonEvent")

    val autoOpen = new DialogProperty[Boolean]("autoOpen", default = Some(true))
    val buttons = new DialogProperty[DialogButtons]("buttons", default = Some(null))
    val closeOnEscape = new DialogProperty[Boolean]("closeOnEscape", default = Some(true))
    val closeText = new DialogProperty[String]("closeText", default = Some("close"))
    val dialogClass = new DialogProperty[String]("dialogClass", default = Some(""))
    val draggable = new DialogProperty[Boolean]("draggable", default = Some(true))
    val height = new DialogProperty[Int]("height", default = Some(-1))
    val hide = new DialogProperty[EffectInstance]("hide", default = Some(null))
    val maxHeight = new DialogProperty[Int]("maxHeight", default = Some(-1))
    val maxWidth = new DialogProperty[Int]("maxWidth", default = Some(-1))
    val minHeight = new DialogProperty[Int]("minHeight", default = Some(150))
    val minWidth = new DialogProperty[Int]("minWidth", default = Some(150))
    val modal = new DialogProperty[Boolean]("modal", default = Some(false))
    val position = new DialogProperty[String]("position", default = Some("{ my: 'center', at: 'center', of: 'window', collision: 'none' }"))
    val resizable = new DialogProperty[Boolean]("resizable", default = Some(true))
    val show = new DialogProperty[EffectInstance]("show", default = Some(null))
    val stack = new DialogProperty[Boolean]("stack", default = Some(true))
    val title = new DialogProperty[String]("title", default = Some(getClass.getSimpleName))
    val width = new DialogProperty[Int]("width", default = Some(300))
    val zIndex = new DialogProperty[Int]("zIndex", default = Some(1000))

    val properties = List(autoOpen, buttons, closeOnEscape, closeText, dialogClass, draggable, height, hide, maxHeight, maxWidth, minHeight, minWidth, modal, position, resizable, show, stack, title, width, zIndex)

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
      case p: DialogProperty[_] if (p.modified && p.isInstanceOf[DialogProperty[_]]) => "%s: %s".format(p.name, value2String(p()))
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

case class DialogButtons(names: String*)