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
    DynamicTag.from[tag.Div](
      """
        |<div class="alert alert-danger" role="alert">
        |  Enter a valid e-mail address
        |</div>
      """.stripMargin).create()

  val badgeHtml = DynamicTag.from[tag.Span](
    """<span class="badge pull-right">42</span>"""
  ).create()

  val columnHtml = DynamicTag.from[tag.Div](
    """<div class="col-md-8">test</div>"""
  ).create()

  val glyphiconHtml = DynamicTag.from[tag.Span](
    """<span class="glyphicon glyphicon-ok"></span>"""
  ).create()

  val glyphiconHtml2 = DynamicTag.from[tag.Span](
    """<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>"""
  ).create()

  val invalidComponentHtml =
    DynamicTag.from[tag.Div]("<div></div>").create()

  val nestedHtml = DynamicTag.from[tag.Div](
    """<div class="jumbotron"><span class="label label-primary">42</span></div>"""
  ).create()

  val listGroupHtml = DynamicTag.from[tag.Div](
    """
      |<div class="list-group">
      |  <a href="#" class="list-group-item active">
      |    <h4 class="list-group-item-heading">List group item heading</h4>
      |    <p class="list-group-item-text">...</p>
      |  </a>
      |</div>
    """.stripMargin
  ).create()

  val listGroupItemHtml = DynamicTag.from[tag.A](
    """
      |<a href="#" class="list-group-item active">
      |  <h4 class="list-group-item-heading">List group item heading</h4>
      |  <p class="list-group-item-text">...</p>
      |</a>
    """.stripMargin
  ).create()

  val tabsHtml = DynamicTag.from[tag.Ul](
    """
      |<ul class="nav nav-tabs">
      |  <li role="presentation" class="active"><a href="#">Home</a></li>
      |  <li role="presentation"><a href="#">Profile</a></li>
      |  <li role="presentation"><a href="#">Messages</a></li>
      |</ul>
    """.stripMargin
  ).create()

  def testConversion(html: HTMLTag, resourceName: String) {
    val component = Generation.findComponent(html).get

    val code = WriterContext(0)
    val vals = WriterContext(0)
    Generation.applyComponent(html, component, code, vals, Map.empty)

    val stream =
      scala.io.Source.fromInputStream(getClass.getResourceAsStream(resourceName))
        .mkString
    (vals.content.toString() ++ code.content.toString()).trim should be (stream.trim)
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
    "not be recognised" in {
      // Additional attributes cannot be represented in glyphicon objects
      val component = Generation.findComponent(glyphiconHtml2)
      component should be (None)
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

  "ListGroup component" should {
    "be recognised" in {
      val component = Generation.findComponent(listGroupHtml).get
      component.name should be ("ListGroup")
    }

    "be converted" in {
      testConversion(listGroupHtml, "/listGroup.scala")
    }
  }

  "Invalid ListGroupItem" should {
    "not be recognised" in {
      val component = Generation.findComponent(listGroupItemHtml)
      component should be (None)
    }
  }

  "Tabs component" should {
    "be recognised" in {
      val component = Generation.findComponent(tabsHtml).get
      component.name should be ("Tabs")
    }

    "be converted" in {
      testConversion(tabsHtml, "/tabs.scala")
    }
  }

  "Invalid component" should {
    "not be recognised" in {
      val component = Generation.findComponent(invalidComponentHtml)
      component should be (None)
    }
  }
}