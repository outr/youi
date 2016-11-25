package io.youi.communicate

import scala.annotation.compileTimeOnly
import scala.reflect.macros.blackbox

@compileTimeOnly("Enable Macros for expansion")
object Macros {
  def clientCall[Response](c: blackbox.Context)(implicit response: c.WeakTypeTag[Response]): c.Expr[ClientCall[Response]] = {
    import c.universe._

    c.Expr[ClientCall[Response]](q"new io.youi.communicate.ClientCall[$response] {}")
  }

  def serverCall[Response](c: blackbox.Context)(implicit response: c.WeakTypeTag[Response]): c.Expr[ServerCall[Response]] = {
    import c.universe._

    c.Expr[ServerCall[Response]](q"new io.youi.communicate.ServerCall[$response] {}")
  }

  def clientMethod[Request, Response](c: blackbox.Context)(implicit request: c.WeakTypeTag[Request], response: c.WeakTypeTag[Response]): c.Expr[ClientMethod[Request, Response]] = {
    import c.universe._

    c.Expr[ClientMethod[Request, Response]](q"new io.youi.communicate.ClientMethod[$request, $response] {}")
  }

  def serverMethod[Request, Response](c: blackbox.Context)(implicit request: c.WeakTypeTag[Request], response: c.WeakTypeTag[Response]): c.Expr[ServerMethod[Request, Response]] = {
    import c.universe._

    c.Expr[ServerMethod[Request, Response]](q"new io.youi.communicate.ServerMethod[$request, $response] {}")
  }

  private def localCall(context: blackbox.Context)(instance: context.universe.Tree, v: context.universe.TermSymbol): context.universe.Tree = {
    import context.universe._

    val responseType = v.info.resultType.typeArgs.head
    q"""
      override val ${v.name} = {
        new ${v.info.resultType.typeSymbol}[$responseType] {
          override val responsePickler = new io.youi.communicate.Pickler[$responseType] {
            override def read(json: String): $responseType = upickle.default.read[$responseType](json)
            override def write(t: $responseType): String = upickle.default.write[$responseType](t)
          }
          override def apply() = scala.concurrent.Future($instance.${v.name})
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
          override def apply(request: $requestType) = scala.concurrent.Future($instance.${v.name}(request))
        }
      }
     """
  }

  def server[I <: Interface](context: blackbox.Context)(implicit i: context.WeakTypeTag[I]): context.Expr[I] = {
    import context.universe._

    val instance = context.prefix.tree
    val fields = i.tpe.decls.collect {
      case v: TermSymbol if v.isVal && v.info.typeSymbol.fullName == "io.youi.communicate.ClientCall" => {
        remoteCall(context)(instance, v)
      }
      case v: TermSymbol if v.isVal && v.info.typeSymbol.fullName == "io.youi.communicate.ServerCall" => {
        localCall(context)(instance, v)
      }
      case v: TermSymbol if v.isVal && v.info.typeSymbol.fullName == "io.youi.communicate.ClientMethod" => {
        remoteMethod(context)(instance, v)
      }
      case v: TermSymbol if v.isVal && v.info.typeSymbol.fullName == "io.youi.communicate.ServerMethod" => {
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
      case v: TermSymbol if v.isVal && v.info.typeSymbol.fullName == "io.youi.communicate.ClientCall" => {
        localCall(context)(instance, v)
      }
      case v: TermSymbol if v.isVal && v.info.typeSymbol.fullName == "io.youi.communicate.ServerCall" => {
        remoteCall(context)(instance, v)
      }
      case v: TermSymbol if v.isVal && v.info.typeSymbol.fullName == "io.youi.communicate.ClientMethod" => {
        localMethod(context)(instance, v)
      }
      case v: TermSymbol if v.isVal && v.info.typeSymbol.fullName == "io.youi.communicate.ServerMethod" => {
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
