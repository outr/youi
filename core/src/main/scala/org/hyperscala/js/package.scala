package com.outr.webframework

import tags.Text
import value.JSValue

package object js {
  implicit def value2Variable[T](v: T) = Constant[T](v)

  implicit def var2Content(v: Var) = Text(JSValue(v.reference.get), preformat = true)

  implicit def var2Condition(v: TypedVar[Boolean]) = {
    new ExplicitInstruction(reference = Some(v.reference.get)) with Condition
  }

  def alert(v: TypedVar[String]) = Instruction(output = Some("alert(%s);\r\n".format(v.reference.get)))

  def parseInt(v: Var) = ExistingVariable[Int]("parseInt(%s)".format(v.reference.get))

  def isNaN(v: TypedVar[Int]) = ExistingVariable[Boolean]("isNaN(%s)".format(v.reference.get))

  def V = Variable
}