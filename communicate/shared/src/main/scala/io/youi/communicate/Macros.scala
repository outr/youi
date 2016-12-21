package io.youi.communicate

import scala.annotation.compileTimeOnly
import scala.reflect.macros.blackbox

@compileTimeOnly("Enable Macros for expansion")
object Macros {
  private def localCall(context: blackbox.Context)(instance: context.universe.Tree, v: context.universe.TermSymbol): context.universe.Tree = {
    import context.universe._

    val responseType = v.info.resultType.typeArgs.head

    val method =
      q"""
        override def apply() = {
          val context = $instance.context
          scala.concurrent.Future {
            $instance.contextualize(context) {
              $instance.${v.name}
            }
          }
        }
       """

    q"""
      override val ${v.name} = {
        new ${v.info.resultType.typeSymbol}[$responseType] {
          override val responsePickler = new io.youi.communicate.Pickler[$responseType] {
            override def read(json: String): $responseType = upickle.default.read[$responseType](json)
            override def write(t: $responseType): String = upickle.default.write[$responseType](t)
          }
          $method
        }
      }
     """
  }

  private def remoteCall(context: blackbox.Context)(instance: context.universe.Tree, v: context.universe.TermSymbol): context.universe.Tree = {
    import context.universe._

    val responseType = v.info.resultType.typeArgs.head
    q"""
      override val ${v.name} = {
        new ${v.info.resultType.typeSymbol}[$responseType] {
          override val responsePickler = new io.youi.communicate.Pickler[$responseType] {
            override def read(json: String): $responseType = upickle.default.read[$responseType](json)
            override def write(t: $responseType): String = upickle.default.write[$responseType](t)
          }
          override def apply() = $instance.call[$responseType](this)
        }
      }
     """
  }

  private def remoteMethod(context: blackbox.Context)(instance: context.universe.Tree, v: context.universe.TermSymbol): context.universe.Tree = {
    import context.universe._

    val requestType = v.info.resultType.typeArgs.head
    val responseType = v.info.resultType.typeArgs.tail.head
    q"""
       override val ${v.name} = {
         new ${v.info.resultType.typeSymbol}[$requestType, $responseType] {
           override val requestPickler = new io.youi.communicate.Pickler[$requestType] {
             override def read(json: String): $requestType = upickle.default.read[$requestType](json)
             override def write(t: $requestType): String = upickle.default.write[$requestType](t)
           }
           override val responsePickler = new io.youi.communicate.Pickler[$responseType] {
             override def read(json: String): $responseType = upickle.default.read[$responseType](json)
             override def write(t: $responseType): String = upickle.default.write[$responseType](t)
           }
           override def apply(request: $requestType) = $instance.method[$requestType, $responseType](this, request)
         }
       }
     """
  }

  private def localMethod(context: blackbox.Context)(instance: context.universe.Tree, v: context.universe.TermSymbol): context.universe.Tree = {
    import context.universe._

    val requestType = v.info.resultType.typeArgs.head
    val responseType = v.info.resultType.typeArgs.tail.head
    val method =
      q"""
         override def apply(request: $requestType) = {
          val context = $instance.context
          scala.concurrent.Future {
            $instance.contextualize(context) {
              $instance.${v.name}(request)
            }
          }
         }
       """
    q"""
      override val ${v.name} = {
        new ${v.info.resultType.typeSymbol}[$requestType, $responseType] {
          override val requestPickler = new io.youi.communicate.Pickler[$requestType] {
            override def read(json: String): $requestType = upickle.default.read[$requestType](json)
            override def write(t: $requestType): String = upickle.default.write[$requestType](t)
          }
          override val responsePickler = new io.youi.communicate.Pickler[$responseType] {
            override def read(json: String): $responseType = upickle.default.read[$responseType](json)
            override def write(t: $responseType): String = upickle.default.write[$responseType](t)
          }
          $method
        }
      }
     """
  }

  def server[I <: Interface](context: blackbox.Context)(implicit i: context.WeakTypeTag[I]): context.Expr[I] = {
    import context.universe._

    val instance = context.prefix.tree
    val fields = i.tpe.decls.collect {
      case v: TermSymbol if v.info.typeSymbol.fullName == "io.youi.communicate.ClientCall" => {
        remoteCall(context)(instance, v)
      }
      case v: TermSymbol if v.info.typeSymbol.fullName == "io.youi.communicate.ServerCall" => {
        localCall(context)(instance, v)
      }
      case v: TermSymbol if v.info.typeSymbol.fullName == "io.youi.communicate.ClientMethod" => {
        remoteMethod(context)(instance, v)
      }
      case v: TermSymbol if v.info.typeSymbol.fullName == "io.youi.communicate.ServerMethod" => {
        localMethod(context)(instance, v)
      }
    }
    val interface =
      q"""
         new $i {
           import scala.concurrent.ExecutionContext.Implicits.global

           ..$fields
         }
       """
    context.Expr[I](interface)
  }

  def client[I <: Interface](context: blackbox.Context)(implicit i: context.WeakTypeTag[I]): context.Expr[I] = {
    import context.universe._

    val instance = context.prefix.tree
    val fields = i.tpe.decls.collect {
      case v: TermSymbol if v.info.typeSymbol.fullName == "io.youi.communicate.ClientCall" => {
        localCall(context)(instance, v)
      }
      case v: TermSymbol if v.info.typeSymbol.fullName == "io.youi.communicate.ServerCall" => {
        remoteCall(context)(instance, v)
      }
      case v: TermSymbol if v.info.typeSymbol.fullName == "io.youi.communicate.ClientMethod" => {
        localMethod(context)(instance, v)
      }
      case v: TermSymbol if v.info.typeSymbol.fullName == "io.youi.communicate.ServerMethod" => {
        remoteMethod(context)(instance, v)
      }
    }
    val interface =
      q"""
         new $i {
           import scala.concurrent.ExecutionContext.Implicits.global

           ..$fields
         }
       """
    context.Expr[I](interface)
  }
}
