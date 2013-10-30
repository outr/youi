package org.hyperscala.javascript.dsl2

import scala.language.existentials

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait Statement[T] {
  val context = JavaScriptContext.created(this)
  def content: String
  def sideEffects: Boolean

  def +(s: Statement[T])(implicit ev: T =:= Double) = OperatorStatement[T](this, "+", s)
  def -(s: Statement[T])(implicit ev: T =:= Double) = OperatorStatement[T](this, "-", s)
  def *(s: Statement[T])(implicit ev: T =:= Double) = OperatorStatement[T](this, "*", s)
  def /(s: Statement[T])(implicit ev: T =:= Double) = OperatorStatement[T](this, "/", s)

  def toJS = context match {
    case Some(c) => c.variable(this) match {
      case Some(name) => name
      case None => content
    }
    case None => content
  }
}

case class WrappedStatement[T](pre: String, statement: Statement[_], post: String, sideEffects: Boolean) extends Statement[T] {
  def content = s"$pre${statement.toJS}$post"
}

case class OperatorStatement[T](left: Statement[_], operator: String, right: Statement[_], sideEffects: Boolean = false) extends Statement[T] {
  def content = s"(${left.toJS} $operator ${right.toJS})"
}

case class ConstantStatement[T](value: T, sideEffects: Boolean = false) extends Statement[T] {
  def content = JavaScriptContext.toJS(value)
}

case class ExistingStatement[T](content: String, sideEffects: Boolean = false) extends Statement[T]