package org.hyperscala.jquery.ui

import org.hyperscala.html._
import org.hyperscala.web.site.Webpage
import org.hyperscala.javascript.JavaScriptString
import org.powerscala.property._
import org.powerscala.event.Listenable
import org.powerscala.property.event.PropertyChangeEvent
import org.hyperscala.realtime.Realtime

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait Dialog extends HTMLTag {
  identity    // Make sure it has an id

  Webpage().require(Realtime)
  Webpage().require(jQueryUI, jQueryUI191)

  private var _open = false

  val dialog = new {
    val autoOpen = new StandardProperty[Boolean]("autoOpen", true) with DialogProperty
    val buttons = new StandardProperty[List[String]]("buttons", Nil) with DialogProperty
    val closeOnEscape = new StandardProperty[Boolean]("closeOnEscape", true) with DialogProperty
    val closeText = new StandardProperty[String]("closeText", "close") with DialogProperty
    val dialogClass = new StandardProperty[String]("dialogClass", "") with DialogProperty
    val draggable = new StandardProperty[Boolean]("draggable", true) with DialogProperty
    val height = new StandardProperty[Int]("height", -1) with DialogProperty
    val hide = new StandardProperty[EffectInstance]("hide", null) with DialogProperty
    val maxHeight = new StandardProperty[Int]("maxHeight", -1) with DialogProperty
    val maxWidth = new StandardProperty[Int]("maxWidth", -1) with DialogProperty
    val minHeight = new StandardProperty[Int]("minHeight", 150) with DialogProperty
    val minWidth = new StandardProperty[Int]("minWidth", 150) with DialogProperty
    val modal = new StandardProperty[Boolean]("modal", false) with DialogProperty
    val position = new StandardProperty[String]("position", null) with DialogProperty
    val resizable = new StandardProperty[Boolean]("resizable", true) with DialogProperty
    val show = new StandardProperty[EffectInstance]("show", null) with DialogProperty
    val stack = new StandardProperty[Boolean]("stack", true) with DialogProperty
    val title = new StandardProperty[String]("title", getClass.getSimpleName) with DialogProperty
    val width = new StandardProperty[Int]("width", 300) with DialogProperty
    val zIndex = new StandardProperty[Int]("zIndex", 1000) with DialogProperty

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

    Listenable.listenTo(autoOpen, buttons, closeOnEscape, closeText, dialogClass, draggable, height, hide, maxHeight, maxWidth, minHeight, minWidth, modal, position, resizable, show, stack, title, width, zIndex).synchronous {
      case evt: PropertyChangeEvent if (generated) => {   // Send changes dynamically after page generated
        injectScript("$('#%s').dialog('option', '%s', %s);".format(id(), evt.property.name(), value2String(evt.newValue)))
      }
    }
  }

  private var generated = false

  override protected def before() = {
    super.before()

    Realtime.sendJavaScript(generateScript(), forId = id(), onlyRealtime = false)
  }

  private def generateScript() = {
    generated = true
    _open = dialog.autoOpen()
    val options = properties.collect {
      case p: StandardProperty[_] if (p.modified && p.isInstanceOf[DialogProperty]) => "%s: %s".format(p.name(), value2String(p()))
    }.mkString(",\n    ")
    """
      |$(function() {
      | $('#%s').dialog({
      |   %s
      | });
      |});
    """.stripMargin.format(id(), options)
  }

  private def injectScript(script: String) = Webpage().head.contents += new tag.Script {
    contents += new JavaScriptString(script)
  }

  private def value2String(v: Any) = v match {
    case null => "null"
    case s: String => "'%s'".format(s)
    case _ => v.toString
  }
}

trait DialogProperty