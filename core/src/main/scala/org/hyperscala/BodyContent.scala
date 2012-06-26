package com.outr.webframework

import tags.attributes.AttributeValue
import xml._


/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
trait BodyContent extends WebContent {
  implicit val bodyContent: BodyContent = this

  implicit def c2s(c: Char) = c.toString
  implicit def i2s(i: Int) = i.toString
  implicit def av2s(av: AttributeValue) = av.value

  def xml = {
    val elem = Elem(null, tag, Null, TopScope, children: _*)
    val seq = attributes.collect {
      case (n, v) if (v.attribute != null && v.attribute.trim.nonEmpty) => new UnprefixedAttribute(n, v.attribute, Null)
    }
    (elem /: seq) ( _ % _ )
  }

  def tag: String
  private[webframework] var attributes = Map.empty[String, WebAttribute]
}
