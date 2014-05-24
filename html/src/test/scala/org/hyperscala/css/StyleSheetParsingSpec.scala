package org.hyperscala.css

import org.scalatest.{Matchers, WordSpec}
import org.powerscala.Color
import org.hyperscala.selector._
import org.hyperscala.css.attributes.{Resource, Origin}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class StyleSheetParsingSpec extends WordSpec with Matchers {
  val css1 = """  #heading {
               |  background-color: #ff0000;
               |}""".stripMargin
  val css2 = """#heading {
               |  background-color: #ff0000;
               |}
               |
               |[data-type="richText"] {
               |  background-color: #0000ff;
               |}""".stripMargin
  val css3 = """div .test {
               | background-color: #00ff00;
               |}""".stripMargin
  val css4 = """div.test { background-color: red; }"""
  val css5 = """[data-type] { background-color: black; }"""
  val css6 = """[data-type], [data-other="test"], #heading { background-color: blue; }"""
  val css7 = """#heading:hover, #heading.test_state_hover {
               |  background-color: #ff0000;
               |}""".stripMargin
  val css8 = """#heading {
               |  background-origin: border-box;
               |}""".stripMargin
  val css9 = """#heading { background-image: url('http://www.testimage.com/test.jpg'); }"""

  "StyleSheet.parse" should {
    "properly parse out one StyleSheet" in {
      val sheets = StyleSheet.parse(null, css1)
      sheets.length should be(1)
      val sheet = sheets.head
      sheet.selectorString should be("#heading")
      sheet.backgroundColor() should be(Color.Red)
    }
    "properly parse out two StyleSheets" in {
      val sheets = StyleSheet.parse(null, css2)
      sheets.length should be(2)
      val sheet1 = sheets.head
      sheet1.selectorString should be("#heading")
      sheet1.backgroundColor() should be(Color.Red)
      val sheet2 = sheets.tail.head
      sheet2.selectorString should be("[data-type=\"richText\"]")
      sheet2.backgroundColor() should be(Color.Blue)
    }
    "properly parse out a complex selector" in {
      val sheets = StyleSheet.parse(null, css3)
      sheets.length should be(1)
      val sheet1 = sheets.head
      sheet1.selectorString should be("div .test")
      sheet1.backgroundColor() should be(Color("#00ff00"))
    }
    "properly parse out a single-line two-part selector" in {
      val sheets = StyleSheet.parse(null, css4)
      sheets.length should be(1)
      val sheet1 = sheets.head
      sheet1.selector.getClass should be(classOf[ClassSelector])
      sheet1.selectorString should be("div.test")
      sheet1.backgroundColor() should be(Color.Red)
    }
    "properly parse out a single-line attribute exists selector" in {
      val sheets = StyleSheet.parse(null, css5)
      sheets.length should be(1)
      val sheet1 = sheets.head
      sheet1.selector.getClass should be(classOf[AttributeExistsSelector])
      sheet1.selectorString should be("[data-type]")
      sheet1.backgroundColor() should be(Color.Black)
    }
    "properly parse out a multi-selector entry" in {
      val sheets = StyleSheet.parse(null, css6)
      sheets.length should be(1)
      val sheet1 = sheets.head
      val selectors = sheet1.selector.toList.toVector
      selectors.length should be(5)

      val s1 = selectors(0)
      s1.getClass should be(classOf[AttributeExistsSelector])
      s1.thisValue should be("[data-type]")
      val s2 = selectors(1)
      s2.getClass should be(classOf[MultipleSelector])
      s2.thisValue should be(", ")
      val s3 = selectors(2)
      s3.getClass should be(classOf[AttributeSelector])
      s3.thisValue should be("[data-other=\"test\"]")
      val s4 = selectors(3)
      s4.getClass should be(classOf[MultipleSelector])
      s4.thisValue should be(", ")
      val s5 = selectors(4)
      s5.getClass should be(classOf[IdSelector])
      s5.thisValue should be("#heading")

      sheet1.selector.value should be("[data-type], [data-other=\"test\"], #heading")
    }
    "properly parse out another multi-selector entry" in {
      val sheets = StyleSheet.parse(null, css7)
      sheets.length should be(1)
      val sheet1 = sheets.head
      val selectors = sheet1.selector.toList.toVector
      selectors.length should be(5)

      selectors(0).thisValue should be("#heading")
      selectors(1).thisValue should be(":hover")
      selectors(2).thisValue should be(", ")
      selectors(3).thisValue should be("#heading")
      selectors(4).thisValue should be(".test_state_hover")

      sheet1.selector.value should be("#heading:hover, #heading.test_state_hover")
    }
    "properly parse out background-origin" in {
      val sheets = StyleSheet.parse(null, css8)
      sheets.length should be(1)
      val sheet1 = sheets.head
      sheet1.backgroundOrigin() should be(Origin.BorderBox)
    }
    "properly parse out background-image" in {
      val sheets = StyleSheet.parse(null, css9)
      sheets.length should be(1)
      val sheet1 = sheets.head
      sheet1.backgroundImage() should be(Resource("http://www.testimage.com/test.jpg"))
    }
  }
}
