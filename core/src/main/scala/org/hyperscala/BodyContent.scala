package org.hyperscala

import js.JavaScriptContent
import tags.attributes.AttributeValue
import value.Property
import xml._


/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait BodyContent extends WebContent {
  implicit val bodyContent: BodyContent = this

  implicit def c2s(c: Char) = c.toString
  implicit def i2s(i: Int) = i.toString
  implicit def jsc2s(jsc: JavaScriptContent) = jsc.toJS
  implicit def av2s(av: AttributeValue) = av.value

  def xml = {
    val elem = Elem(null, tag, Null, TopScope, children: _*)
    val seq = attributes.collect {
      case (n, v) if (v.attribute != null && v.attribute.trim.nonEmpty) => new UnprefixedAttribute(n, v.attribute, Null)
    }
    (elem /: seq) ( _ % _ )
  }

  def tag: String
  private[hyperscala] var attributes = Map.empty[String, WebAttribute]

  def custom(name: String, value: String) = attributes += name -> new CustomAttribute(name, value)

  /**
   * Updates attribute with value if it's not null.
   */
  protected def up[T](attribute: Property[T], value: T) = {
    if (value != null) {
      attribute := value
    }
  }

  protected def up(attribute: Property[Int], value: java.lang.Integer) = {
    if (value != null) {
      attribute := value.intValue()
    }
  }
}

class CustomAttribute(name: String, value: String)(implicit bodyContent: BodyContent) extends GenericAttribute[String](name) {
  this := value
}