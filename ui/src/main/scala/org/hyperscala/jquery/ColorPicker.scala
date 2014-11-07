package org.hyperscala.jquery

import com.outr.net.http.session.Session
import org.hyperscala.html._
import org.hyperscala.html.tag.Input
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.jquery.ui.jQueryUI
import org.hyperscala.module.Module
import org.hyperscala.realtime.RealtimeEvent
import org.hyperscala.web._
import org.powerscala.event.Intercept
import org.powerscala.{Color, StorageComponent, Version}

import scala.language.implicitConversions

/**
 * @author Matt Hicks <matt@outr.com>
 */
object ColorPicker extends Module with JavaScriptCaller with StorageComponent[ColorPicker, tag.Input] {
  implicit def tag2ColorPicker(tag: Input): ColorPicker = apply(tag)

  private val OkFunction = JavaScriptString(
    """function(event, color) {
      | var id = $(this).attr('id');
      | realtimeSend(id, 'colorSelected', {
      |   value: color.formatted
      | });
      |}
    """.stripMargin)

  def name = "colorpicker"
  def version = Version(1, 0, 4)

  override def dependencies = List(jQuery, jQueryUI)

  override def init[S <: Session](website: Website[S]) = {
    website.addClassPath("/colorpicker-1.0.4/", "colorpicker-1.0.4/")
  }

  override def load[S <: Session](webpage: Webpage[S]) = {
    webpage.head.contents += new tag.Link(href = "/colorpicker-1.0.4/jquery.colorpicker.css", rel = "stylesheet")
    webpage.head.contents += new tag.Script(mimeType = "text/javascript", src = "/colorpicker-1.0.4/jquery.colorpicker.js")
  }

  override def apply(tag: Input) = {
    tag.require(this)
    super.apply(tag)
  }

  protected def create(t: Input) = new ColorPicker(t)
}

class ColorPicker private(val wrapped: tag.Input, val autoInit: Boolean = true) extends jQueryComponent {
  def functionName = "colorpicker"

  // Make sure events fire back to server upon select
  on("ok", ColorPicker.OkFunction)
  wrapped.eventReceived.on {
    case evt if evt.event == "colorSelected" => {
      val value = evt.json.string("value")
      if (value != null && value.trim.nonEmpty) {
        color := Color(value)
      } else {
        color := null
      }

      Intercept.Stop
    }
    case _ => Intercept.Continue
  }
  option("colorFormat", "#HEX")

  if (wrapped.changeEvent() == null) {
    wrapped.changeEvent := RealtimeEvent()
  }

  /**
   * The initial color will be set with the color option. If you don't pass in a color, it will use the
   * value attribute on the input.
   *
   * Defaults to value of input.
   */
  val color = property("color", colorFromValue())
  /**
   * Whether or not to to show the inputs for alpha.
   *
   * Defaults to false.
   */
  val alpha = property("alpha", false)
  /**
   * Change the opacity of the altField element(s) according to the alpha setting.
   *
   * Defaults to true.
   */
  val altAlpha = property("altAlpha", true)
  /**
   * Change the background color of the elements specified in this element.
   *
   * Defaults to "".
   */
  val altField = property("altField", "")
  /**
   * If true, the altField element(s) are updated on every change, otherwise only upon closing.
   *
   * Defaults to true.
   */
  val altOnChange = property("altOnChange", true)
  /**
   * List of CSS properties to set color of in the altField. The following properties are allowed, all others are
   * ignored:
   * - background-color
   * - color
   * - border-color
   * - outline-color
   *
   * Defaults to List("background-color").
   */
  val altProperties = property("altProperties", List("background-color"))
  /**
   * If true, the dialog opens automatically upon page load.
   *
   * Defaults to false.
   */
  val autoOpen = property("autoOpen", false)
  /**
   * If this option is set, the button will be assigned the class specified.
   *
   * Defaults to null.
   */
  val buttonClass = property[String]("buttonClass", null)
  /**
   * If a buttonImage is specified, change the background color of the image when the color is changed.
   */
  val buttonColorize = property("buttonColorize", false)
  val buttonImage = property("buttonImage", "images/ui-colorpicker.png")
  val buttonImageOnly = property("buttonImageOnly", false)
  /**
   * If null, use language default from jQueryUI.
   *
   * Defaults to null.
   */
  val buttonText = property[String]("buttonText", null)
  /**
   * Close the window when pressing the Escape key on the keyboard.
   *
   * Defaults to true.
   */
  val closeOnEscape = property("closeOnEscape", true)
  /**
   * Close the window when clicking outside the colorpicker display.
   *
   * Defaults to true.
   */
  val closeOnOutside = property("closeOnOutside", false)
  /**
   * Make the dialog draggable if the header is visible and the dialog is not inline.
   */
  val draggable = property("draggable", true)
  /**
   * Defaults to "fast"
   */
  val duration = property("duration", "fast")
  /**
   * Whether or not to show the inputs for HSV.
   *
   * Defaults to true.
   */
  val hsv = property("hsv", true)
  /**
   * If set to false, attaching to a non-input will still make the dialog a popup instead of inline.
   *
   * Defaults to true.
   */
  val inline = property("inline", true)
  /**
   * If enabled, shows a border and background when inline. Disabling may allow closer integration.
   *
   * Defaults to true.
   */
  val inlineFrame = property("inlineFrame", true)
  /**
   * Ensures no other controls on screen can be used while the dialog is opened. Also look at showCancelButton
   * and closeOnEscape to use in combination with the modal option. closeOnOutside is redundant when used with modal.
   *
   * Defaults to false.
   */
  val modal = property("modal", false)
  /**
   * Determines the functionality of the map and bar components. Allowed values are; 'h', 's', 'l', 'r', 'g', 'b'
   * or 'a', for hue, saturation, luminosity, red, green, blue and alpha respectively.
   *
   * Defaults to "h".
   */
  val mode = property("mode", "h")
  /**
   * Close the window when pressing the Enter key on the keyboard, keeping the selected color when set to true.
   *
   * Defaults to false.
   */
  val okOnEnter = property("okOnEnter", false)
  val regional = property("regional", "")
  /**
   * If enabled, closing the dialog through any means but the OK button will revert the color back to the previous
   * state, as if pressing the Cancel button. The revert option changes the behaviour of the [X] button in the header,
   * the Escape keyboard button and clicking outside the dialog, when any of these features are enabled.
   *
   * Defaults to false.
   */
  val revert = property("revert", false)
  /**
   * Whether or not to show the inputs for RGB.
   *
   * Defaults to true.
   */
  val rgb = property("rgb", true)
  /**
   * Animation when showing the picker.
   *
   * Defaults to "fadeIn"
   */
  val showAnim = property("showAnim", "fadeIn")
  /**
   * Show the Cancel button if true.
   *
   * Defaults to true.
   */
  val showCancelButton = property("showCancelButton", true)
  /**
   * Show the Close button if the header is visible. If the dialog is inline, the close button is never shown.
   *
   * Defaults to true.
   */
  val showCloseButton = property("showCloseButton", true)
  /**
   * Show the None/Revert button if true.
   *
   * Defaults to false.
   */
  val showNoneButton = property("showNoneButton", false)
  /**
   * Specified what user events will show the colorpicker if not inline.
   * - focus: When the element comes into focus (either tab or click)
   * - click: When the element is clicked (for non-inputs)
   * - alt: When clicking on an element specified with as altField
   * - button: When clicking on the button created if this event is specified
   * - both: Selects all possible triggers
   */
  val showOn = property("showOn", List("focus", "click", "alt"))
  /**
   * Title to display in the header. If null, use language default.
   *
   * Defaults to null.
   */
  val title = property("title", null)

  color.bindTo(wrapped.value)(s => colorFromValue())
  wrapped.value.bindTo(color)(c => if (c != null) c.hex.rgb else null)

  private def colorFromValue() = wrapped.value() match {
    case null | "" => null
    case s => Color(s)
  }
}