package org.hyperscala.html.attributes

import org.hyperscala.{ToScala, AttributeValue}
import org.hyperscala.persistence.CaseClassPersistence

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
case class Target(value: String) extends AttributeValue with ToScala {
  def name = value.substring(1).capitalize

  import scala.reflect.runtime.universe._
  def toScala: Tree =
    this match {
      case Target.Blank => q"Target.Blank"
      case Target.Self => q"Target.Self"
      case Target.Parent => q"Target.Parent"
      case Target.Top => q"Target.Top"
    }
}

object Target extends CaseClassPersistence[Target] {
  val Blank = new Target("_blank")
  val Self = new Target("_self")
  val Parent = new Target("_parent")
  val Top = new Target("_top")
}