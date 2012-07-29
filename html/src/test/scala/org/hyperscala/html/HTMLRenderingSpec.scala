package org.hyperscala.html

import attributes.ContentEditable
import org.specs2.mutable._
import org.jdom2.Element

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class HTMLRenderingSpec extends Specification {
  "HTML element" should {
    "render properly with one attribute set" in {
      val html = new HTML()
      html.name := "testing"
      val out = html.outputString
      out must_==("<html name=\"testing\" />")
    }
    "have correct parent for attributes" in {
      val html = new HTML()
      html.name.parent must_==(html)
    }
    "load properly with one attribute set" in {
      val html = new HTML()
      val element = new Element("html")
      element.setAttribute("name", "testing")
      html.fromXML(element)
      val out = html.outputString
      out must_==("<html name=\"testing\" />")
    }
    "load properly with an enum attribute set" in {
      val html = new HTML {
        contentEditable := ContentEditable.Inherit
      }
      val out = html.outputString
      out must_==("<html contenteditable=\"inherit\" />")
    }
    "properly add Head tag" in {
      val html = new HTML {
        contents += new Head
      }
      val out = html.outputString
      out must_==("<html><head /></html>")
    }
  }
}
