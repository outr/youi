package org.hyperscala.jquery

import org.hyperscala.module._
import org.hyperscala.html._
import org.hyperscala.web.site.Webpage
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.jquery.dsl.jQueryDSL

/**
 * @author Matt Hicks <matt@outr.com>
 */
object jQuery extends Interface with jQueryDSL {
  def Latest = jQuery191
  val LatestWithDefault = InterfaceWithDefault(jQuery, Latest)

  def name = "jquery"

  def scrollTop(offset: Int) = call("html,body", "scrollTop(%s)".format(offset))

  def blur(tag: HTMLTag) = call(tag, "blur()")

  def change(tag: HTMLTag) = call(tag, "change()")

  def focus(tag: HTMLTag) = call(tag, "focus()")

  def select(tag: HTMLTag) = call(tag, "select()")

  def submit(tag: HTMLTag) = call(tag, "submit()")

  def call(t: HTMLTag, function: String): Unit = call("#%s".format(t.identity), function)

  def call(selector: String, function: String): Unit = {
    Webpage().body.contents += new tag.Script(content = new JavaScriptString(
      """
        |var callFunction = function() {
        |  if ($('%1$s').length == 0) {
        |    setTimeout(callFunction, 10);
        |  } else {
        |    $('%1$s').%2$s;
        |  }
        |}
        |callFunction();
      """.stripMargin.format(selector, function)))
  }
}