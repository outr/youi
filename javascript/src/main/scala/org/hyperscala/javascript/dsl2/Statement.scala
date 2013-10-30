package org.hyperscala.javascript.dsl2

import scala.language.existentials

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait Statement[T] {
  implicit def context: JavaScriptContext
  def content: String
  def sideEffects: Boolean

  JavaScriptContext.created(this)

  def +(s: Statement[T]) = OperatorStatement[T](this, "+", s)
  def -(s: Statement[T])(implicit ev: T =:= Double) = OperatorStatement[T](this, "-", s)
  def *(s: Statement[T])(implicit ev: T =:= Double) = OperatorStatement[T](this, "*", s)
  def /(s: Statement[T])(implicit ev: T =:= Double) = OperatorStatement[T](this, "/", s)

  def toJS = if (context != null) {
    context.variable(this) match {
      case Some(name) => name
      case None => content
    }
  } else {
    content
  }
}

case class WrappedStatement[T](pre: String, statement: Statement[_], post: String, sideEffects: Boolean)(implicit val context: JavaScriptContext) extends Statement[T] {
  def content = s"$pre${statement.toJS}$post"
}

case class OperatorStatement[T](left: Statement[_], operator: String, right: Statement[_], sideEffects: Boolean = false)(implicit val context: JavaScriptContext) extends Statement[T] {
  def content = s"(${left.toJS} $operator ${right.toJS})"
}

case class ConstantStatement[T](value: T, sideEffects: Boolean = false)(implicit val context: JavaScriptContext) extends Statement[T] {
  def content = JavaScriptContext.toJS(value, context)
}

case class ExistingStatement[T](content: String, sideEffects: Boolean = false)(implicit val context: JavaScriptContext) extends Statement[T]