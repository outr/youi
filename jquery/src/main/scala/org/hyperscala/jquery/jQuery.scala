package org.hyperscala.jquery

import org.hyperscala.module._
import org.hyperscala.html._
import org.hyperscala.web.site.Webpage
import org.hyperscala.javascript.{JavaScriptContent, JavaScriptString}
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

  def call(t: HTMLTag, functionName: String, values: Map[String, Any]): Unit = {
    val function = if (values.nonEmpty) {
      val body = values.map {
        case (key, value) => s"\t$key: ${JavaScriptContent.toJS(value)}"
      }.mkString(",\n")
      s"$functionName({\n$body\n});"
    } else {
      s"$functionName();"
    }
    call(t, function)
  }

  def options(t: HTMLTag, functionName: String, values: Map[String, String], waitForResults: Boolean = true) = {
    values.foreach {
      case (key, value) => {    // TODO: see if there is a more efficient way to set multiple options at once
        option(t, functionName, key, value, waitForResults = waitForResults)
      }
    }
  }

  def option(t: HTMLTag, functionName: String, key: String, value: Any, waitForResults: Boolean = true) = {
    val function = s"$functionName('option', '$key', ${JavaScriptContent.toJS(value)})"
    call(t, function)
  }

  def call(t: HTMLTag, function: String): Unit = call("#%s".format(t.identity), function)

  def call(selector: String, function: String, waitForResults: Boolean = true): Unit = {
    val content = if (waitForResults) {
      """
        |var callFunction = function() {
        |  if ($('%1$s').length == 0) {
        |    setTimeout(callFunction, 10);
        |  } else {
        |    $('%1$s').%2$s;
        |  }
        |}
        |callFunction();
      """.stripMargin.format(selector, function)
    } else {
      s"$$('$selector').$function;"
    }
    Webpage().body.contents += new tag.Script(content = new JavaScriptString(content))
  }
}