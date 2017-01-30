package io.youi.app

import io.youi.communication.Communication

import scala.annotation.compileTimeOnly
import scala.reflect.macros.blackbox

@compileTimeOnly("Enable Macros for expansion")
object Macros {
  def communication[C <: Communication](context: blackbox.Context)(implicit c: context.WeakTypeTag[C]): context.Expr[CommunicationManager[C]] = {
    import context.universe._

    val app = context.prefix.tree

    val isJS = try {
      context.universe.rootMirror.staticClass("scala.scalajs.js.Any")
      true
    } catch {
      case t: Throwable => false
    }

    val typeString = c.tpe.toString
    val (preType, postType) = if (typeString.indexOf('.') != -1) {
      val index = typeString.lastIndexOf('.')
      typeString.substring(0, index + 1) -> typeString.substring(index + 1)
    } else {
      "" -> typeString
    }

    val communicationTypeString = if (isJS) {
      s"${preType}Client$postType"
    } else {
      s"${preType}Server$postType"
    }
    val communicationType = try {
      context.universe.rootMirror.staticClass(communicationTypeString)
    } catch {
      case exc: ScalaReflectionException => context.abort(context.enclosingPosition, s"Unable to find implementation trait $communicationTypeString for $typeString.")
    }

    context.Expr[CommunicationManager[C]](
      q"""
         import io.youi.app._
         import io.youi.communication._
         import scala.concurrent.ExecutionContext.Implicits.global

         new CommunicationManager[$c]($app, c => Communication.create[$communicationType](c))
       """)
  }
}
