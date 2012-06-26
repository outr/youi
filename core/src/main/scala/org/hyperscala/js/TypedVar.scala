package com.outr.webframework.js

trait TypedVar[T] extends Var {
  def :=(v: TypedVar[T]) = Instruction(output = Some("%s = %s;\r\n".format(reference.get, v.reference.get)))

  def Equals(that: TypedVar[T]) = condition("==", that)

  def NotEquals(that: TypedVar[T]) = condition("!=", that)

  def <=(that: TypedVar[T]) = condition("<=", that)

  def >=(that: TypedVar[T]) = condition(">=", that)

  def <(that: TypedVar[T]) = condition("<", that)

  def >(that: TypedVar[T]) = condition(">", that)

  def in(array: Array[T]) = ForLoop("%s in %s".format(reference.get, array.reference.get))

  def *(that: TypedVar[T]) = ExistingVariable[T]("%s * %s".format(reference.get, that.reference.get))

  def +(that: TypedVar[T]) = ExistingVariable[T]("%s + %s".format(reference.get, that.reference.get))

//  def +=(that: TypedVar[T]) = ExistingVariable[T]("%s += %s".format(reference.get, that.reference.get))

  protected def condition(operator: String, that: TypedVar[T]) = {
    new ExplicitInstruction(reference = Some("%s %s %s".format(reference.get, operator, that.reference.get))) with Condition
  }
}
