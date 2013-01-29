package org.hyperscala.jquery.ui

import org.hyperscala.html._
import org.hyperscala.web.site.Webpage
import org.hyperscala.javascript.JavaScriptString

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
trait DatePicker extends HTMLTag {
  identity        // Make sure it has an id

  Webpage().require(jQueryUI, jQueryUI191)

  override protected def initialize() {
    super.initialize()

    val script = """
                   |$(function() {
                   | $('#%s').datepicker();
                   |});
                 """.stripMargin.format(id())
    Webpage().head.contents += new tag.Script {
      contents += new JavaScriptString(script)
    }
  }
}

object DatePicker {
  def apply(t: HTMLTag) = {
    Webpage().require(jQueryUI, jQueryUI191)

    val script = """
                   |$(function() {
                   | $('#%s').datepicker();
                   |});
                 """.stripMargin.format(t.identity)
    Webpage().head.contents += new tag.Script {
      contents += new JavaScriptString(script)
    }
  }
}
