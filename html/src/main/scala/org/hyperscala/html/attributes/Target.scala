package org.hyperscala.html.attributes

import org.hyperscala.AttributeValue
import org.hyperscala.persistence.CaseClassPersistence

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
case class Target(value: String) extends AttributeValue

object Target extends CaseClassPersistence[Target] {
  val Blank = new Target("_blank")
  val Self = new Target("_self")
  val Parent = new Target("_parent")
  val Top = new Target("_top")
}