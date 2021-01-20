package io.youi

import java.io.File

import io.youi.stream.{HTMLParser, Selector}
import org.scalajs.dom.Element
import profig.Profig
import profig.jdk._

import scala.annotation.compileTimeOnly
import scala.reflect.macros.blackbox

@compileTimeOnly("Enable Macros for expansion")
object TemplateMacros {
  def existingById[E <: Element](context: blackbox.Context)(path: context.Expr[String],
                                                            id: context.Expr[String],
                                                            appName: context.Expr[String])(implicit e: context.WeakTypeTag[E]): context.Expr[E] = {
    import context.universe._

    Profig.init()

    val pathValue = path match {
      case Expr(Literal(Constant(value: String))) => value
    }
    val idValue = id match {
      case Expr(Literal(Constant(value: String))) => value
    }
    val pathKey = appName match {
      case Expr(Literal(Constant(value: String))) => s"$value.template.path"
    }
    val templatePath = Profig(pathKey).opt[String]
    val file = templatePath match {
      case Some(basePath) => new File(basePath, pathValue)
      case None => new File(pathValue)
    }
    if (!file.exists()) {
      if (templatePath.isEmpty) {
        context.warning(context.enclosingPosition, s"No configuration defined for $pathKey.")
      }
      context.abort(context.enclosingPosition, s"Unable to find path for ${file.getAbsolutePath}.")
    }
    val parser = HTMLParser(file)
    val template = parser.stream(Nil, selector = Some(Selector.ById(idValue)))
    if (template.trim.isEmpty) {
      context.abort(context.enclosingPosition, s"No content found for #$idValue in ${file.getAbsolutePath}")
    }

    context.Expr[E](
      q"""
         try {
           io.youi.dom.fromString[$e]($template).head
         } catch {
           case t: Throwable => throw new RuntimeException("Error parsing HTML [" + $template + "] byId " + $idValue, t)
         }
       """)
  }

  def existingByClass[E <: Element](context: blackbox.Context)(path: context.Expr[String],
                                                               className: context.Expr[String],
                                                               appName: context.Expr[String])(implicit e: context.WeakTypeTag[E]): context.Expr[List[E]] = {
    import context.universe._

    Profig.init()

    val pathValue = path match {
      case Expr(Literal(Constant(value: String))) => value
    }
    val classValue = className match {
      case Expr(Literal(Constant(value: String))) => value
    }
    val pathKey = appName match {
      case Expr(Literal(Constant(value: String))) => s"$value.template.path"
    }
    val templatePath = Profig(pathKey).opt[String]
    val file = templatePath match {
      case Some(basePath) => new File(basePath, pathValue)
      case None => new File(pathValue)
    }
    if (!file.exists()) {
      if (templatePath.isEmpty) {
        context.warning(context.enclosingPosition, s"No configuration defined for $pathKey.")
      }
      context.abort(context.enclosingPosition, s"Unable to find path for ${file.getAbsolutePath}.")
    }
    val parser = HTMLParser(file)
    val template = parser.stream(Nil, selector = Some(Selector.ByClass(classValue)), includeAllMatches = true)
    if (template.trim.isEmpty) {
      context.abort(context.enclosingPosition, s"No content found for .$classValue in ${file.getAbsolutePath}")
    }

    context.Expr[List[E]](
      q"""
         try {
           io.youi.dom.fromString[$e]($template)
         } catch {
           case t: Throwable => throw new RuntimeException("Error parsing HTML [" + $template + "] byClass " + $classValue, t)
         }
       """)
  }

  def existingByTag[E <: Element](context: blackbox.Context)(path: context.Expr[String],
                                                             tagName: context.Expr[String],
                                                             appName: context.Expr[String])(implicit e: context.WeakTypeTag[E]): context.Expr[List[E]] = {
    import context.universe._

    Profig.init()

    val pathValue = path match {
      case Expr(Literal(Constant(value: String))) => value
    }
    val tagValue = tagName match {
      case Expr(Literal(Constant(value: String))) => value
    }
    val pathKey = appName match {
      case Expr(Literal(Constant(value: String))) => s"$value.template.path"
    }
    val templatePath = Profig(pathKey).opt[String]
    val file = templatePath match {
      case Some(basePath) => new File(basePath, pathValue)
      case None => new File(pathValue)
    }
    if (!file.exists()) {
      if (templatePath.isEmpty) {
        context.warning(context.enclosingPosition, s"No configuration defined for $pathKey.")
      }
      context.abort(context.enclosingPosition, s"Unable to find path for ${file.getAbsolutePath}.")
    }
    val parser = HTMLParser(file)
    val template = parser.stream(Nil, selector = Some(Selector.ByTag(tagValue)), includeAllMatches = true)
    if (template.trim.isEmpty) {
      context.abort(context.enclosingPosition, s"No content found for $tagValue in ${file.getAbsolutePath}")
    }

    context.Expr[List[E]](
      q"""
         try {
           io.youi.dom.fromString[$e]($template)
         } catch {
           case t: Throwable => throw new RuntimeException("Error parsing HTML [" + $template + "] byTag " + $tagValue, t)
         }
       """)
  }
}
