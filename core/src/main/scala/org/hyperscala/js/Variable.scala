package com.outr.webframework.js

import com.outr.webframework.WebContent

class Variable[T](name: String, instanceRepresentation: String) extends TypedVar[T] {
  override val output = Some("var %s = %s;\r\n".format(name, instanceRepresentation))
  override val reference = Some(name)
}

object Variable {
  def apply[T](value: T, name: String) = new Variable[T](name, JavaScript.outputConstant(value))

  def apply[T](v: TypedVar[T], name: String) = new Variable[T](name, v.reference.get)

  def content[T <: WebContent](v: T, name: String) = {
    new Variable[T](name, v.reference.get)
    v.reference = Some(name)
    v
  }
}