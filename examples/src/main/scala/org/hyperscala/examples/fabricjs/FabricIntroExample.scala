package org.hyperscala.examples.fabricjs

import org.hyperscala.css.SelectorStyleSheet
import org.hyperscala.css.attributes.{Clear, Float, LineStyle}
import org.hyperscala.examples.Example
import org.hyperscala.fabricjs.FabricJS
import org.hyperscala.html._
import org.hyperscala.jquery.Gritter
import org.hyperscala.selector.Selector
import org.hyperscala.ui.module.WebFontLoader
import org.hyperscala.web.Webpage
import org.powerscala.Color

/**
 * @author Matt Hicks <matt@outr.com>
 */
abstract class FabricIntroExample extends Webpage with Example {
  require(FabricJS)
  require(Gritter)
  require(WebFontLoader)

  new SelectorStyleSheet(Selector.clazz("canvas-container"))(body) {
    borderWidth := 1.px
    borderColor := Color.Black
    borderStyle := LineStyle.Solid
    backgroundColor := Color.White
    float := Float.Left
    marginAll(10.px)
  }

  val canvases = new tag.Div
  body.contents += canvases

  val buttons = new tag.Div {
    style.clear := Clear.Both
  }
  body.contents += buttons
}
