package org.hyperscala.site


import org.powerscala.Color
import org.hyperscala.html._
import attributes.Target
import org.hyperscala.css.attributes._
import tag._
import org.hyperscala.web.site.Webpage
import org.hyperscala.ui.WindowSized
import org.hyperscala.web.FormSupport
import language.reflectiveCalls

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class HyperscalaPage extends Webpage with FormSupport {
  def site = HyperscalaSite

  title := "Hyperscala - Statically typed bare-metal HTML, CSS, and JavaScript framework for Scala."

  def sourceURL: String = null

  head.contents += new tag.Link(rel = "stylesheet", href = "/css/style.css")

  val main = new tag.Div(id = "main") {
    WindowSized.heightAlgorithm("main", "windowHeight - 235")
  }
  val middle = new tag.Div(id = "middle") {
    contents += new tag.Img(id = "logo", src = "/images/hyperscala.png") {
      style.float := Float.Left
    }
    if (sourceURL != null) {
      contents += new tag.A(href = sourceURL, target = Target.Blank, content = "View Source") {
        style.float := Float.Right
      }
    }
    contents += new Bar {
      contents += MenuItem("About", site.site.about.link)
      contents += MenuItem("Examples", site.site.examples.link)
      contents += MenuItem("Documentation", site.site.documentation.link)
      contents += MenuItem("Project", "https://github.com/darkfrog26/hyperscala/")
    }
    contents += main
    contents += new Bar {
      contents += new tag.I {
        style.float := Float.Right
        style.color := Color.White
        style.paddingTop := 3.px
        style.paddingRight := 30.px
        style.fontSize := FontSize.Small
        contents += "&copy;2013 Hyperscala.org"
      }
    }
  }
  val wrapper = new tag.Div(id = "wrap") {
    contents += middle
  }

  body.contents += wrapper
}

class Bar extends Div {
  style.backgroundColor := Color.Black
  style.opacity := Opacity(0.5)
  style.borderRadius := 10.px
  style.height := 25.px
  style.clear := Clear.Both
}

case class MenuItem(itemName: String, url: String = "#") extends tag.Div(clazz = List("menuItem")) {
  contents += new A(href = url) {
    contents += itemName
  }
}