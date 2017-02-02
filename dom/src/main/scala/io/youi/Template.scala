package io.youi

import java.io.File

import io.youi.stream.{ByClass, ById, HTMLParser}
import org.scalajs.dom.Element

import scala.annotation.compileTimeOnly
import scala.language.experimental.macros
import scala.reflect.macros.blackbox

object Template {
  def byId[E <: Element](path: String, id: String): E = macro TemplateMacros.existingById[E]
  def byClass[E <: Element](path: String, className: String): List[E] = macro TemplateMacros.existingByClass[E]
}

@compileTimeOnly("Enable Macros for expansion")
object TemplateMacros {
  def existingById[E <: Element](context: blackbox.Context)(path: context.Expr[String], id: context.Expr[String])(implicit e: context.WeakTypeTag[E]): context.Expr[E] = {
    import context.universe._

    val pathValue = path match {
      case Expr(Literal(Constant(value: String))) => value
    }
    val idValue = id match {
      case Expr(Literal(Constant(value: String))) => value
    }
    val file = new File(pathValue)
    if (!file.exists()) {
      context.abort(context.enclosingPosition, s"Unable to find path for ${file.getAbsolutePath}.")
    }
    val parser = HTMLParser(file)
    val template = parser.stream(Nil, selector = Some(ById(idValue)))

    context.Expr[E](
      q"""
         io.youi.dom.fromString[$e]($template).head
       """)
  }

  def existingByClass[E <: Element](context: blackbox.Context)(path: context.Expr[String], className: context.Expr[String])(implicit e: context.WeakTypeTag[E]): context.Expr[List[E]] = {
    import context.universe._

    val pathValue = path match {
      case Expr(Literal(Constant(value: String))) => value
    }
    val classValue = className match {
      case Expr(Literal(Constant(value: String))) => value
    }
    val file = new File(pathValue)
    if (!file.exists()) {
      context.abort(context.enclosingPosition, s"Unable to find path for ${file.getAbsolutePath}.")
    }
    val parser = HTMLParser(file)
    val template = parser.stream(Nil, selector = Some(ByClass(classValue)), includeAllMatches = true)

    context.Expr[List[E]](
      q"""
         io.youi.dom.fromString[$e]($template)
       """)
  }
}