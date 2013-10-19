package org.hyperscala.jquery

import org.powerscala.{StorageComponent, Color, Version}
import org.hyperscala.module.{Module, InterfaceWithDefault}
import org.hyperscala.web.{Webpage, Website}
import org.hyperscala.html.tag

import scala.language.implicitConversions
import org.hyperscala.html.tag.Input
import org.hyperscala.event.JavaScriptEvent
import org.hyperscala.javascript.JavaScriptContent

/**
 * Spectrum is a wrapper around the the jQuery Colorpicker found here: http://bgrins.github.io/spectrum/
 *
 * Most of the documentation is duplicated directly from the JavaScript documentation.
 *
 * Spectrum is a StorageComponent, so activating it on an Input can be done by just calling Spectrum(input). You can
 * invoke the method multiple times and it will return the same instance for the same input.
 *
 * @author Matt Hicks <matt@outr.com>
 */
object Spectrum extends Module with JavaScriptCaller with StorageComponent[Spectrum, Input] {
  implicit def tag2Spectrum(tag: Input) = apply(tag)

  def name = "spectrum"

  def version = Version(1, 1, 1)

  override def dependencies = List(InterfaceWithDefault(jQuery, jQuery.Latest))

  def init() = {
    Website().addClassPath("/spectrum/", "spectrum/")
  }

  def load() = {
    val page = Webpage()
    page.head.contents += new tag.Link(href = "/spectrum/spectrum.css", rel = "stylesheet")
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "/spectrum/spectrum.js")
  }

  override def apply(tag: Input) = {
    Webpage().require(this)
    super.apply(tag)
  }

  protected def create(t: Input) = new Spectrum(t)
}

class Spectrum private(val wrapped: Input) extends jQueryComponent {
  def functionName = "spectrum"

  if (wrapped.changeEvent() == null) {
    wrapped.changeEvent := JavaScriptEvent()
  }

  /**
   * The initial color will be set with the color option. If you don't pass in a color, Spectrum will use the
   * value attribute on the input.
   *
   * Defaults to value of input or Black if input does not contain a color value.
   */
  val color = property("color", colorFromValue())
  /**
   * This means that it will always show up at full size, and be positioned as an inline-block element.
   *
   * Defaults to false.
   */
  val flat = property("flat", false)
  /**
   * You can add an input to allow free form typing. The color parsing is very permissive in the allowed strings.
   *
   * Defaults to false.
   */
  val showInput = property("showInput", false)
  /**
   * Spectrum can show the color that was initially set when opening. This provides an easy way to click back to
   * what was set when opened.
   *
   * Defaults to false.
   */
  val showInitial = property("showInitial", false)
  /**
   * Shows alpha transparency selection.
   *
   * Defaults to false.
   */
  val showAlpha = property("showAlpha", false)
  /**
   * Spectrum can be automatically disabled if you pass in the disabled flag. Additionally, if the input that you
   * initialize spectrum on is disabled, this will be the default value.
   *
   * Note: you cannot enable spectrum if the input is disabled.
   *
   * Defaults to false.
   */
  val disabled = property("disabled", false)
  /**
   * The key used to store selection data in the browser's localStorage object.
   *
   * Defaults to null.
   */
  val localStorageKey = property[String]("localStorageKey", null)
  /**
   * Spectrum can show a palette below the colorpicker to make it convenient for users to choose from frequently or
   * recently used colors. When the colorpicker is closed, the current color will be added to the palette if it isn't
   * there already.
   *
   * Defaults to false.
   */
  val showPalette = property("showPalette", false)
  /**
   * If you'd like, spectrum can show the palettes you specify, and nothing else.
   *
   * Defaults to false.
   */
  val showPaletteOnly = property("showPaletteOnly", false)
  /**
   * Spectrum can keep track of what has been selected by the user with this option. If the localStorageKey option is
   * defined, the selection will be saved in the browser's localStorage object.
   *
   * Defaults to false.
   */
  val showSelectionPalette = property("showSelectionPalette", false)
  /**
   * When clicking outside of the colorpicker, you can force it to fire a change event rather than having it revert
   * the change.
   *
   * Defaults to false.
   */
  val clickoutFiresChange = property("clickoutFiresChange", false)
  /**
   * The text to display on the cancel button.
   *
   * Defaults to "Cancel".
   */
  val cancelText = property("cancelText", "Cancel")
  /**
   * The text to display on the choose button.
   *
   * Defaults to "Confirm".
   */
  val chooseText = property("chooseText", "Confirm")
  /**
   * You can show or hide the buttons using this property. If there are no buttons, the behavior will be to fire the
   * change event (and update the original input) when the picker is closed.
   *
   * Defaults to true.
   */
  val showButtons = property("showButtons", true)
  /**
   * You can add an additional class name to the replacer and container element using the className property.
   *
   * Defaults to null.
   */
  val className = property("className", null)
  /**
   * You can set the format that is displayed.
   *
   * Options are:
   *    - hex
   *    - hex6
   *    - hsl
   *    - rgb
   *    - name (falls back to hex)
   *
   * Defaults to "name".
   */
  val preferredFormat = property("preferredFormat", "name", includeDefault = true)
  /**
   * The color palette to use.
   *
   * Defaults to Nil.
   */
  val palette = property[List[Color]]("palette", Nil)

  color.bindTo(wrapped.value)(s => colorFromValue())
  wrapped.value.bindTo(color)(c => if (c != null) c.hex.rgb else null)

  private def colorFromValue() = {
    println(s"wrapped: ${wrapped.value()}")
    wrapped.value() match {
      case null | "" => null
      case s => Color(s)
    }
  }

  override def option(key: String, value: Any) = key match {
    case "color" => jQuery.call(wrapped, s"spectrum('set', ${JavaScriptContent.toJS(value)})")
    case _ => super.option(key, value)
  }
}