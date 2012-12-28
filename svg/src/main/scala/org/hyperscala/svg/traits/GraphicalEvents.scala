package org.hyperscala.svg.traits

import org.hyperscala.svg.SVGTag
import org.hyperscala.PropertyAttribute
import org.hyperscala.javascript.{EventProperty, JavaScriptContent}

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
trait GraphicalEvents {
  this: SVGTag =>

  val event = new {
    val focusIn = new PropertyAttribute[JavaScriptContent]("onfocusin", null) with EventProperty
    val focusOut = new PropertyAttribute[JavaScriptContent]("onfocusout", null) with EventProperty
    val activate = new PropertyAttribute[JavaScriptContent]("onactivate", null) with EventProperty
    val click = new PropertyAttribute[JavaScriptContent]("onclick", null) with EventProperty
    val mouseDown = new PropertyAttribute[JavaScriptContent]("onmousedown", null) with EventProperty
    val mouseUp = new PropertyAttribute[JavaScriptContent]("onmouseup", null) with EventProperty
    val mouseOver = new PropertyAttribute[JavaScriptContent]("onmouseover", null) with EventProperty
    val mouseMove = new PropertyAttribute[JavaScriptContent]("onmousemove", null) with EventProperty
    val mouseOut = new PropertyAttribute[JavaScriptContent]("onmouseout", null) with EventProperty
    val load = new PropertyAttribute[JavaScriptContent]("onload", null) with EventProperty
  }
}