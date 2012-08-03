package org.hyperscala.site

import org.hyperscala.web.HTMLPage

import org.powerscala.property._
import org.powerscala.Color
import org.hyperscala.html._
import org.hyperscala.css.attributes._

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class HyperscalaPage extends HTMLPage {
  title := "Hyperscala - Statically typed bare-metal HTML, CSS, and JavaScript framework for Scala."

  body.style.margin := "0"
  body.style.background.color := Color.Black
  body.style.color := Color.White
  body.style.font.family := "Arial, sans-serif"

  val page = new Div {
    style.background.color := Color.White
    (0 until 40).foreach(i => contents += new Br)
  }

  val main = new Div {
    style.width := 960.px
    style.display := Display.Block
    style.margin.left := "auto"
    style.margin.right := "auto"

    contents += new Div {
      style.width := 960.px
      style.height := 120.px
      style.background.image := Resource("top_clouds.png")
      contents += new Img(src = "hyperscala.png") {
        style.margin.top := "10px"
        style.margin.left := "10px"
      }
    }
    contents += new Bar {
      contents += new Table {
        style.width := 100.pct
        contents += new Tr {
          contents += MenuItem("Home")
          contents += MenuItem("Examples")
          contents += MenuItem("Documentation")
          contents += MenuItem("Utilities")
        }
      }
    }
    contents += page
    contents += new Bar {
      contents += new Div {
        style.padding.right := 10.px
        style.padding.top := 8.px
        style.float := Float.Right
        style.font.size := FontSize.Small
        contents += "&copy;2012 Hyperscala.org"
      }
    }
  }
  body.contents += main
}

class Bar extends Div {
  style.width := 960.px
  style.height := 30.px
  style.background.image := Resource("bar.png")
}

case class MenuItem(itemName: String) extends Td {
  style.text.align := Alignment.Center
  style.font.weight := "bold"
  style.width := 150.px
  style.padding.top := 2.px

  contents += new A(href = "#") {
    style.text.decoration := "none"
    style.color := Color.White
    contents += itemName
  }
}