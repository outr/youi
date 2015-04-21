package org.hyperscala.css.attributes

import org.hyperscala.AttributeValue
import org.hyperscala.persistence.CaseClassPersistence

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
case class Resource(value: String) extends AttributeValue

object Resource extends CaseClassPersistence[Resource] {
  val None = new Resource("none")
  val Inherit = new Resource("inherit")

  def url(url: String) = Resource(s"url('$url')")

  def get(name: String) = if (name != null) {
    name.toLowerCase match {
      case "none" => Some(None)
      case "inherit" => Some(Inherit)
      case _ => Some(Resource(name))
    }
  } else {
    scala.None
  }
}