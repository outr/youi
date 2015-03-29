package org.hyperscala.examples.fabricjs

import org.hyperscala.bootstrap.component.Button
import org.hyperscala.css.SelectorStyleSheet
import org.hyperscala.css.attributes.{Clear, Float, LineStyle}
import org.hyperscala.examples.Example
import org.hyperscala.fabricjs._
import org.hyperscala.fabricjs.filters.{Brightness, Sepia, Grayscale}
import org.hyperscala.fabricjs.paint.{ColorStop, LinearGradient}
import org.hyperscala.fabricjs.prop.Adjust
import org.hyperscala.fabricjs.util.Ease
import org.hyperscala.html._
import org.hyperscala.realtime._
import org.hyperscala.selector.Selector
import org.powerscala.Color

/**
 * @author Matt Hicks <matt@outr.com>
 */
class FabricIntroPart2 extends Example {
  require(FabricJS)

  connected[tag.HTML] {
    case html => html.head.verifyCSS("http://fonts.googleapis.com/css?family=Pacifico")
  }

  new SelectorStyleSheet(Selector.clazz("canvas-container"))(this) {
    borderWidth := 1.px
    borderColor := Color.Black
    borderStyle := LineStyle.Solid
    float := Float.Left
    marginAll(10.px)
  }

  val canvases = new tag.Div
  contents += canvases

  val buttons = new tag.Div {
    style.clear := Clear.Both
  }
  contents += buttons

  first()
  second()
  third()
  fourth()
  fifth()
  sixth()
  seventh()
  eighth()
  ninth()
  tenth()
  eleventh()
  twelfth()
  thirteenth()
  fourteenth()
  fifteenth()
  sixteenth()

  def first() = {
    val canvas = Canvas(canvases, 200, 200)

    val rect = new Rect {
      left := 100.0
      top := 100.0
      fill := Color.Red
      width := 20.0
      height := 20.0
      originX := "center"
      originY := "center"
    }

    canvas.contents += rect

    buttons.contents += new Button("Rotate Object") {
      clickEvent.onRealtime {
        case evt => {
          rect.angle.animate(Adjust(45.0))
        }
      }
    }
  }

  def second() = {
    val canvas = Canvas(canvases, 200, 200)

    val rect = new Rect {
      left := 100.0
      top := 100.0
      fill := Color.Red
      width := 20.0
      height := 20.0
      originX := "center"
      originY := "center"
    }

    canvas.contents += rect

    buttons.contents += new Button("Rotate Object Relative") {
      clickEvent.onRealtime {
        case evt => {
          rect.angle.animate(Adjust += 45.0)
        }
      }
    }
  }

  def third() = {
    val canvas = Canvas(canvases, 200, 200)

    val rect = new Rect {
      left := 100.0
      top := 100.0
      fill := Color.Red
      width := 20.0
      height := 20.0
      originX := "center"
      originY := "center"
    }

    canvas.contents += rect

    buttons.contents += new Button("Ease Object") {
      clickEvent.onRealtime {
        case evt => {
          rect.left.animate(Adjust(10.0), from = Some(100.0), duration = 1.0, ease = Ease.OutBounce)
        }
      }
    }
  }

  def fourth() = {
    val canvas = Canvas(canvases, 200, 200)

    val image = new Image("/images/pug_small.jpg") {
      left := 100.0
      top := 100.0
      angle := -30.0
      opacity := 0.85
      originX := "center"
      originY := "center"
      scaleX := 0.3
      scaleY := 0.3
      filters.contents += Grayscale
    }

    canvas.contents += image
  }

  def fifth() = {
    val canvas = Canvas(canvases, 200, 200)

    val image = new Image("/images/pug_small.jpg") {
      left := 100.0
      top := 100.0
      angle := -30.0
      opacity := 0.85
      originX := "center"
      originY := "center"
      scaleX := 0.3
      scaleY := 0.3
      filters.contents += Sepia
    }

    canvas.contents += image
  }

  def sixth() = {
    val canvas = Canvas(canvases, 200, 200)

    val image = new Image("/images/pug_small.jpg") {
      left := 100.0
      top := 100.0
      angle := -30.0
      opacity := 0.85
      originX := "center"
      originY := "center"
      scaleX := 0.3
      scaleY := 0.3
      filters.contents += Sepia
      filters.contents += new Brightness(100)
    }

    canvas.contents += image
  }

  def seventh() = {       // TODO: re-render after linear gradient defined
    val canvas = Canvas(canvases, 200, 200)

    val circle = new Circle {
      radius := 50.0
      left := 100.0
      top := 100.0
      originX := "center"
      originY := "center"
      fill := LinearGradient(x1 = 0.0, y1 = 0.0, x2 = 0.0, y2 = 100.0, colorStops = List(ColorStop(0.0, Color.Black), ColorStop(1.0, Color.White)))
    }

    canvas.contents += circle
  }

  def eighth() = {       // TODO: re-render after linear gradient defined
    val canvas = Canvas(canvases, 200, 200)

    val circle = new Circle {
      radius := 50.0
      left := 100.0
      top := 100.0
      originX := "center"
      originY := "center"
      fill := LinearGradient(x1 = 0.0, y1 = 0.0, x2 = 100.0, y2 = 0.0, colorStops = List(ColorStop(0.0, Color.Red), ColorStop(1.0, Color.Blue)))
    }

    canvas.contents += circle
  }

  def ninth() = {       // TODO: re-render after linear gradient defined
    val canvas = Canvas(canvases, 200, 200)

    val circle = new Circle {
      radius := 50.0
      left := 100.0
      top := 100.0
      originX := "center"
      originY := "center"
      fill := LinearGradient(x1 = 0.0, y1 = 0.0, x2 = 100.0, y2 = 0.0, colorStops = List(ColorStop(0.0, Color.Red), ColorStop(0.2, Color.Orange), ColorStop(0.4, Color.Yellow), ColorStop(0.6, Color.Green), ColorStop(0.8, Color.Blue), ColorStop(1.0, Color.Purple)))
    }

    canvas.contents += circle
  }

  def tenth() = {       // TODO: re-render after Google Font loads
    val canvas = Canvas(canvases, 200, 200)

    val text = new Text("I'm in Pacifico") {
      fontFamily := "Pacifico"
      angle := -25.0
      left := 100.0
      top := 100.0
      originX := "center"
      originY := "center"
      scaleX := 0.5
      scaleY := 0.5
    }

    canvas.contents += text
  }

  def eleventh() = {
    val canvas = Canvas(canvases, 200, 200)

    val text1 = new Text("I'm at fontSize 40") {
      fontSize := 40.0
      left := 5.0
      top := 25.0
      scaleX := 0.65
      scaleY := 0.65
    }

    val text2 = new Text("I'm at fontSize 20") {
      fontSize := 20.0
      left := 35.0
      top := 100.0
      scaleX := 0.65
      scaleY := 0.65
    }

    canvas.contents.addAll(text1, text2)
  }

  def twelfth() = {
    val canvas = Canvas(canvases, 200, 200)

    val text1 = new Text("I'm a normal text") {
      fontSize := 40.0
      left := 5.0
      top := 25.0
      scaleX := 0.65
      scaleY := 0.65
      fontWeight := "normal"
    }

    val text2 = new Text("I'm a bold text") {
      fontSize := 40.0
      left := 5.0
      top := 100.0
      scaleX := 0.65
      scaleY := 0.65
      fontWeight := "bold"
    }

    canvas.contents.addAll(text1, text2)
  }

  def thirteenth() = {
    val canvas = Canvas(canvases, 200, 200)

    val scale = 0.5

    val text1 = new Text("I'm an underlined text") {
      fontSize := 40.0
      left := 5.0
      top := 25.0
      scaleX := scale
      scaleY := scale
      textDecoration := "underline"
    }

    val text2 = new Text("I'm a strike-through text") {
      fontSize := 40.0
      left := 5.0
      top := 80.0
      scaleX := scale
      scaleY := scale
      textDecoration := "line-through"
      fill := Color.Blue
    }

    val text3 = new Text("I'm an overlined text") {
      fontSize := 40.0
      left := 5.0
      top := 135.0
      scaleX := scale
      scaleY := scale
      textDecoration := "overline"
      fill := Color.Red
    }

    canvas.contents.addAll(text1, text2, text3)
  }

  def fourteenth() = {
    val canvas = Canvas(canvases, 200, 200)

    val scale = 0.5

    val text1 = new Text("I'm a text with shadow") {
      fontSize := 40.0
      left := 5.0
      top := 25.0
      scaleX := scale
      scaleY := scale
      shadow := "rgba(0, 0, 0, 0.3) 5px 5px 5px"
    }

    val text2 = new Text("And another shadow...") {
      fontSize := 40.0
      left := 5.0
      top := 80.0
      scaleX := scale
      scaleY := scale
      shadow := "rgba(0, 0, 0, 0.2) 0 0 5px"
      fill := Color.Blue
    }

    val text3 = new Text("I'm an overlined text") {
      fontSize := 40.0
      left := 5.0
      top := 135.0
      scaleX := scale
      scaleY := scale
      shadow := "green -5px -5px 3px"
      fill := Color.Red
    }

    canvas.contents.addAll(text1, text2, text3)
  }

  def fifteenth() = {
    val canvas = Canvas(canvases, 200, 200)

    val scale = 0.5

    val text1 = new Text("A very fancy italic text") {
      fontSize := 40.0
      left := 5.0
      top := 25.0
      scaleX := scale
      scaleY := scale
      fontStyle := "italic"
    }

    val text2 = new Text("another italic text") {
      fontSize := 40.0
      left := 5.0
      top := 80.0
      scaleX := scale
      scaleY := scale
      fill := Color.Blue
      fontStyle := "italic"
    }

    canvas.contents.addAll(text1, text2)
  }

  def sixteenth() = {
    val canvas = Canvas(canvases, 200, 200)

    val scale = 0.5

    val text1 = new Text("Text with a stroke") {
      fontSize := 40.0
      left := 5.0
      top := 25.0
      scaleX := scale
      scaleY := scale
      stroke := Color.immutable("#ff1318")
      strokeWidth := 1.0
    }

    val text2 = new Text("Lorem ipsum dolor") {
      fontSize := 40.0
      left := 5.0
      top := 80.0
      scaleX := scale
      scaleY := scale
      stroke := Color.immutable("#c3bfbf")
      strokeWidth := 3.0
    }

    canvas.contents.addAll(text1, text2)
  }
}
