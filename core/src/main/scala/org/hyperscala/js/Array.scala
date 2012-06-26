package com.outr.webframework.js

case class Array[T](values: Seq[TypedVar[T]], name: String) extends Var {
  override val output = Some("var %s = %s;\r\n".format(name, values.map(v => v.reference.get).mkString("[", ", ", "]")))
  override val reference = Some(name)

  def update(index: Int, value: TypedVar[T]) = Instruction(output = Some("%s[%s] = %s;\r\n".format(name, index, value.reference.get)))
}
