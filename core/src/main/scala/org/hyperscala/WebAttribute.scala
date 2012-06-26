package com.outr.webframework

import js.{Instruction, Var, TypedVar}
import value.Property

trait WebAttribute extends Var {
  def name: String
  def attribute: String
  def bodyContent: BodyContent

  override lazy val reference = Some("%s.%s".format(bodyContent.reference.get, name))
}

object WebAttribute {
  def apply[T](name: String)(implicit conversion: T => String, bodyContent: BodyContent) = new GenericAttribute[T](name)(conversion, bodyContent)
}

class GenericAttribute[T](val name: String)(implicit conversion: T => String, val bodyContent: BodyContent) extends Property[T] with WebAttribute {
  bodyContent.attributes += (name -> this)
  def attribute = value match {
    case null => ""
    case _ => conversion(value.value)
  }

  def :=(v: TypedVar[T]) = Instruction(output = Some("%s = %s;\r\n".format(reference.get, v.reference.get)))
}