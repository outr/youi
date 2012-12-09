package org.hyperscala.examples.ui

import org.hyperscala.web.site.Webpage

import org.hyperscala.html._
import org.hyperscala.web.module.jQueryUI191
import org.hyperscala.javascript.JavaScriptString

/**
 * @author Matt Hicks <matt@outr.com>
 */
class DialogExample extends Webpage {
  require(jQueryUI191)

  body.contents += new tag.Div(id = "dialog", titleText = "Dialog Example") {
    contents += new tag.P(content = "This is the default dialog which is useful for displaying information. The dialog window can be moved, resized and closed with the 'x' icon.")
  }
  body.contents += new tag.Script {
    contents += new JavaScriptString(
      """
        |$(function() {
        | $('#dialog').dialog();
        |});
      """.stripMargin)
  }

  body.contents += new tag.Button(content = "Do Something") {
    event.click := JavaScriptString(
      """
        |$('#dialog').dialog({title: 'Hello World!'});
      """.stripMargin)
  }
}
