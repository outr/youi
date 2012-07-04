package org.hyperscala.javascript.helper

import org.hyperscala.{BodyContent, WebContent}
import collection.mutable.ListBuffer
import xml.Node


/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class Conditional extends BodyContent {
  def tag = "conditional"

  val conditions = ListBuffer.empty[Condition]

  var default: Condition = _

  override protected def children = {
    var first = true
    var list: List[Node] = conditions.toList.map(c => if (first) {
      first = false
      c.asIf
    } else {
      c.asElseIf
    })
    if (default != null) {
      list ++= default.asElse
    }
    list
  }
}

case class Condition(expression: String = "")(f: => WebContent) {
  def asIf = <if condition={expression}>
    {f.xml}
  </if>
  def asElseIf = <elseif condition={expression}>
    {f.xml}
  </elseif>
  def asElse = <else>
    {f.xml}
  </else>
}