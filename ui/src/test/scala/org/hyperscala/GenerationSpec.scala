package org.hyperscala

import org.hyperscala.html._
import org.hyperscala.io.WriterContext
import org.hyperscala.io.bootstrap.Generation
import org.hyperscala.io.bootstrap.Specification._
import org.hyperscala.ui.dynamic.DynamicTag
import org.scalatest.{Matchers, WordSpec}

/**
 * @author Tim Nieradzik <tim@metastack.pl>
 */
class GenerationSpec extends WordSpec with Matchers {
  val alertHtml =
    DynamicTag.static[tag.Div]("test",
      """
        |<div class="alert alert-danger" role="alert">
        |  Enter a valid e-mail address
        |</div>
      """.stripMargin).create()

  val badgeHtml = DynamicTag.static[tag.Span]("test2",
    """<span class="badge pull-right">42</span>"""
  ).create()

  val columnHtml = DynamicTag.static[tag.Div]("test3",
    """<div class="col-md-8">test</div>"""
  ).create()

  val glyphiconHtml = DynamicTag.static[tag.Span]("test4",
    """<span class="glyphicon glyphicon-ok"></span>"""
  ).create()

  val glyphiconHtml2 = DynamicTag.static[tag.Span]("test5",
    """<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>"""
  ).create()

  val invalidComponentHtml =
    DynamicTag.static[tag.Div]("test6", "<div></div>").create()

  val nestedHtml = DynamicTag.static[tag.Div]("test7",
    """<div class="jumbotron"><span class="label label-primary">42</span></div>"""
  ).create()

  def testConversion(html: HTMLTag, resourceName: String) {
    val component = Generation.findComponent(html).get
    Generation.isConvertable(html, component) should be (true)

    val code = WriterContext(0)
    val vals = WriterContext(0)
    Generation.applyComponent(html, component, code, vals, Map.empty)

    val stream =
      scala.io.Source.fromInputStream(getClass.getResourceAsStream(resourceName))
        .mkString
    (vals.content.toString() ++ code.content.toString()).trim should be (stream.trim)
  }

  def failConversion(html: HTMLTag) {
    val component = Generation.findComponent(html).get
    Generation.isConvertable(html, component) should be (false)
  }

  "Danger component" should {
    "be recognised" in {
      val component = Generation.findComponent(alertHtml).get
      component.name should be ("Alert")
      val props = Generation.extractProperties(alertHtml, component.asInstanceOf[DefComponent])
      props.size should be (1)
      props.head._2 should be (Value.Option("Danger", "alert-danger"))
    }

    "be converted" in {
      testConversion(alertHtml, "/alert.scala")
    }
  }

  "Badge component" should {
    "be recognised" in {
      val component = Generation.findComponent(badgeHtml).get
      component.name should be ("Badge")
      val props = Generation.extractProperties(badgeHtml, component.asInstanceOf[DefComponent])
      props.size should be (1)
      props.head._2 should be (Value.Boolean("pull-right"))
    }

    "be converted" in {
      testConversion(badgeHtml, "/badge.scala")
    }
  }

  "Column component" should {
    "be recognised" in {
      val component = Generation.findComponent(columnHtml).get
      component.name should be ("Column")
      val props = Generation.extractProperties(columnHtml, component.asInstanceOf[DefComponent])
      props.size should be (1)
      props.head._1.name should be ("medium")
      props.head._2 should be (Value.Instance(8))
    }

    "be converted" in {
      testConversion(columnHtml, "/column.scala")
    }
  }

  "Glyphicon component" should {
    "be recognised" in {
      val component = Generation.findComponent(glyphiconHtml).get
      component.name should be ("Glyphicon")
    }

    "be converted" in {
      testConversion(glyphiconHtml, "/glyphicon.scala")
    }
  }

  "Glyphicon component with additional attributes" should {
    "be recognised" in {
      val component = Generation.findComponent(glyphiconHtml2).get
      component.name should be ("Glyphicon")
    }

    "not be converted" in {
      // Additional attributes cannot be represented in glyphicon objects
      failConversion(glyphiconHtml2)
    }
  }

  "Nested components" should {
    "be recognised" in {
      // Top-level component
      val component = Generation.findComponent(nestedHtml).get
      component.name should be ("Jumbotron")
    }

    "not be converted" in {
      testConversion(nestedHtml, "/nested.scala")
    }
  }

  "Invalid component" should {
    "not be recognised" in {
      Generation.findComponent(invalidComponentHtml) should be (None)
    }
  }
}