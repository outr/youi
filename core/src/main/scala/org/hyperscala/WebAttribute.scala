package org.hyperscala

import js.{Instruction, Var, TypedVar}
import value.Property
import org.sgine.reflect._

trait WebAttribute extends Var {
  def name: String
  def attribute: String
  def bodyContent: BodyContent
  def modified: Boolean

  override lazy val reference = Some("%s.%s".format(bodyContent.reference.get, name))

  // TODO: optimize this...REALLY slow right now
  lazy val scalaName = {
    bodyContent.getClass.methods.find {
      m => classOf[WebAttribute].isAssignableFrom(m.returnType.`type`.javaClass) && m.invoke[WebAttribute](bodyContent) == this
    }.map(m => m.name)
  }
}

object WebAttribute {
  def apply[T](name: String)(implicit conversion: T => String, bodyContent: BodyContent, manifest: Manifest[T]) = new GenericAttribute[T](name)(conversion, bodyContent, manifest)
  def apply[T](name: String, value: T)(implicit conversion: T => String, bodyContent: BodyContent, manifest: Manifest[T]) = {
    val a = new GenericAttribute[T](name)(conversion, bodyContent, manifest)
    a := value
    a
  }
}

class GenericAttribute[T](val name: String)(implicit conversion: T => String, val bodyContent: BodyContent, val manifest: Manifest[T]) extends Property[T] with WebAttribute {
  bodyContent.attributes += (name -> this)
  def attribute = value match {
    case null => ""
    case _ => conversion(value.value)
  }

  def :=(v: TypedVar[T]) = Instruction(output = Some("%s = %s;\r\n".format(reference.get, v.reference.get)))
}