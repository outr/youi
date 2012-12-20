package org.hyperscala.site


import org.powerscala.property._
import org.powerscala.Color
import org.hyperscala.html._
import attributes.Target
import org.hyperscala.css.attributes._
import tag._
import org.hyperscala.web.site.Webpage
import org.hyperscala.ui.WindowSized

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class HyperscalaPage extends Webpage {
  def site = HyperscalaSite

  title := "Hyperscala - Statically typed bare-metal HTML, CSS, and JavaScript framework for Scala."

  def sourceURL: String = null

  head.contents += new tag.Link(rel = "stylesheet", href = "/css/style.css")

  val main = new tag.Div(id = "main") {
    WindowSized.heightAlgorithm("main", "windowHeight - 235")
  }
  val middle = new tag.Div(id = "middle") {
    contents += new tag.Img(src = "/images/hyperscala.png") {
      style.float := Float.Left
    }
    if (sourceURL != null) {
      contents += new tag.A(href = sourceURL, target = Target.Blank, content = "View Source") {
        style.float := Float.Right
      }
    }
    contents += new Bar {
      contents += MenuItem("Home")
      contents += MenuItem("About", site.site.about.link)
      contents += MenuItem("Examples")
      contents += MenuItem("Utilities")
      contents += MenuItem("Documentation")
    }
    contents += main
    contents += new Bar {
      contents += new tag.I {
        style.float := Float.Right
        style.color := Color.White
        style.padding.top := 3.px
        style.padding.right := 30.px
        style.font.size := FontSize.Small
        contents += "&copy;2012 Hyperscala.org"
      }
    }
  }
  val wrapper = new tag.Div(id = "wrap") {
    contents += middle
  }

  body.contents += wrapper
}

class Bar extends Div {
  style.background.color := Color.Black
  style.opacity := Opacity(0.5)
  style.border.radius := 10.px
  style.height := 25.px
  style.clear := Clear.Both
}

case class MenuItem(itemName: String, url: String = "#") extends tag.Div(clazz = List("menuItem")) {
  contents += new A(href = url) {
    contents += itemName
  }
}