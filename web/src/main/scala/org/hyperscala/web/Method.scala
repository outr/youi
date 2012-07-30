package org.hyperscala.web

import org.powerscala.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
sealed class Method extends EnumEntry[Method]

object Method extends Enumerated[Method] {
  val Head = new Method
  val Get = new Method
  val Post = new Method
  val Put = new Method
  val Delete = new Method
  val Trace = new Method
  val Options = new Method
  val Connect = new Method
  val Patch = new Method

  override def apply(name: String) = super.apply(name) match {
    case null => values.find(m => m.name().toLowerCase == name.toLowerCase).getOrElse(null)
    case e => e
  }
}