package org.hyperscala.javascript.dsl

import scala.language.existentials
import org.hyperscala.javascript.JavaScriptContent

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait Statement[T] {
  protected[javascript] var used = false
  def isUsed = used

  final def context = JavaScriptContext.current
  var name: String = null
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

  def toJS = if (name != null) {
    name
  } else {
    content
  }
}

case class WrappedStatement[T](pre: String, statement: Statement[_], post: String, sideEffects: Boolean) extends Statement[T] {
  def content = s"$pre${statement.toJS}$post"
}

case class MultiStatement[T](sideEffects: Boolean, parts: Any*) extends Statement[T] {
  parts.foreach {
    case s: Statement[_] => s.used = true     // Make sure statements within this statement are classified as used
    case _ => // Ignore everything else
  }

  def content = parts.map {
    case s: Statement[_] => s.toJS
    case s: String => s
    case p => JavaScriptContent.toJS(p)
  }.mkString("")
}

case class OperatorStatement[T](left: Statement[_], operator: String, right: Statement[_], enclose: Boolean = true, sideEffects: Boolean = false) extends Statement[T] {
  left.used = true
  right.used = true

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
  def :=(v: Statement[T]) = OperatorStatement[T](VariableName(this), "=", v, enclose = false, sideEffects = true)

  def content = if (initialValue != null) {
    initialValue.content
  } else {
    null
  }

  def sideEffects = true
}

case class VariableName[T](v: Variable[T]) extends Statement[T] {
  def content = v.name

  def sideEffects = false
}