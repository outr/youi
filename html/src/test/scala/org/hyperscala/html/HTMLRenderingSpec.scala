package org.hyperscala.html

import attributes.ContentEditable
import org.jdom2.Element
import org.hyperscala.css.attributes.Display
import org.jdom2.input.SAXBuilder
import java.io.StringReader
import tag.{Head, HTML, Body, Title}
import org.scalatest.{Matchers, WordSpec}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class HTMLRenderingSpec extends WordSpec with Matchers {
  val builder = new SAXBuilder()

  "HTML element" should {
    "render properly with one attribute set" in {
      val html = new HTML()
      html.name := "testing"
      val out = html.outputString
      out should equal("\n<html name=\"testing\"></html>")
    }
    "have correct parent for attributes" in {
      val html = new HTML()
      html.name.parent should equal(html)
    }
    "load properly with one attribute set" in {
      val html = new HTML()
      val element = new Element("html")
      element.setAttribute("name", "testing")
      html.read(element)
      val out = html.outputString
      out should equal("\n<html name=\"testing\"></html>")
    }
    "load properly with an enum attribute set" in {
      val html = new HTML {
        contentEditable := ContentEditable.Inherit
      }
      val out = html.outputString
      out should equal("\n<html contenteditable=\"inherit\"></html>")
    }
    "properly add Head tag" in {
      val html = new HTML {
        contents += new Head
      }
      val out = html.outputString
      out should equal("\n<html>\n  <head></head>\n</html>")
    }
  }
  "DOM building" should {
    "properly create a simple html document" in {
      val html = new HTML {
        contents += new Head {
          contents += new Title(content = "Test Title")
        }
        contents += new Body {
          contents += "Test Body"
        }
      }
      val out = html.outputString
      out should equal("<html><head><title>Test Title</title></head><body>Test Body</body></html>")
    }
  }
  "Attributes" should {
    "show properly when Boolean true" in {
      val html = new HTML(hidden = true)
      val out = html.outputString
      out should equal("<html hidden=\"\"/>")
    }
    "show properly when Boolean false" in {
      val html = new HTML(hidden = false)
      val out = html.outputString
      out should equal("<html/>")
    }
    "show properly when Char" in {
      val html = new HTML(accessKey = 'T')
      val out = html.outputString
      out should equal("<html accesskey=\"T\"/>")
    }
    "show properly when Int" in {
      val html = new HTML(tabIndex = 5)
      val out = html.outputString
      out should equal("<html tabindex=\"5\"/>")
    }
    "show properly when List[String]" in {
      val html = new HTML(clazz = List("one", "two", "three"))
      val out = html.outputString
      out should equal("<html class=\"one two three\"/>")
    }
    "show properly when EnumEntry" in {
      val html = new HTML(contentEditable = ContentEditable.True)
      val out = html.outputString
      out should equal("<html contenteditable=\"true\"/>")
    }
    "show properly when CSS" in {
//      val css = new StyleSheet {
//        font.face := "Arial"
//        display := Display.Inline
//      }
      val html = new HTML {
        style.fontFace := "Arial"
        style.display := Display.Inline
      }
      val out = html.outputString
      out should equal("<html style=\"display: inline; font-face: Arial\"/>")
    }
  }
  "DOM deserialization" should {
    "properly deserialize a basic HTML page" in {
      val content = "<html><head><title>Test Title</title></head><body>Test Body</body></html>"
      val html = new HTML
      val document = builder.build(new StringReader(content))
      html.read(document.getRootElement)
      val out = html.outputString
      out should equal(content)
    }
    "properly deserialize a basic HTML page with CSS" in {
      val content = "<html><head><title>Test Title</title></head><body><h1 style=\"display: none\">Test Body</h1></body></html>"
      val html = new HTML
      html.read(builder.build(new StringReader(content)).getRootElement)
      val out = html.outputString
      out should equal(content)
    }
  }
}