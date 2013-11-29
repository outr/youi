package org.hyperscala.javascript.dsl2

import scala.language.existentials

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait Statement[T] {
  final def context = JavaScriptContext.current
  def content: String
  def sideEffects: Boolean

  JavaScriptContext.created(this)

  def +(s: Statement[T]) = OperatorStatement[T](this, "+", s, enclose = false)
  def +=(s: Statement[T]) = OperatorStatement[T](this, "+=", s, enclose = false, sideEffects = true)
  def -(s: Statement[T])(implicit ev: T =:= Double) = OperatorStatement[T](this, "-", s, enclose = true)
  def -=(s: Statement[T])(implicit ev: T =:= Double) = OperatorStatement[T](this, "-=", s, enclose = false, sideEffects = true)
  def *(s: Statement[T])(implicit ev: T =:= Double) = OperatorStatement[T](this, "*", s, enclose = true)
  def *=(s: Statement[T])(implicit ev: T =:= Double) = OperatorStatement[T](this, "*=", s, enclose = false, sideEffects = true)
  def /(s: Statement[T])(implicit ev: T =:= Double) = OperatorStatement[T](this, "/", s, enclose = true)
  def /=(s: Statement[T])(implicit ev: T =:= Double) = OperatorStatement[T](this, "/=", s, enclose = false, sideEffects = true)

  def toJS = content
}

case class WrappedStatement[T](pre: String, statement: Statement[_], post: String, sideEffects: Boolean) extends Statement[T] {
  def content = s"$pre${statement.toJS}$post"
}

case class OperatorStatement[T](left: Statement[_], operator: String, right: Statement[_], enclose: Boolean = true, sideEffects: Boolean = false) extends Statement[T] {
  def content = if (enclose) {
    s"(${left.toJS} $operator ${right.toJS})"
  } else {
    s"${left.toJS} $operator ${right.toJS}"
  }
}

case class ConstantStatement[T](value: T, sideEffects: Boolean = false) extends Statement[T] {
  def content = JavaScriptContext.toJS(value)
}

case class ExistingStatement[T](content: String, sideEffects: Boolean = false) extends Statement[T]

trait DelayedStatement[T] {
  def toStatement: Statement[T]
}

class Variable[T](initialValue: Statement[T] = null) extends Statement[T] {
  var name: String = null

  def :=(v: Statement[T]) = OperatorStatement[T](VariableName(this), "=", v, enclose = false, sideEffects = true)

  def content = if (initialValue != null) {
    initialValue.content
  } else {
    null
  }

  def sideEffects = true

  override def toJS = if (name != null) {
    name
  } else {
    super.toJS
  }
}

case class VariableName[T](v: Variable[T]) extends Statement[T] {
  def content = v.name

  def sideEffects = false
}