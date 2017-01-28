package io.youi.comm

import scala.annotation.compileTimeOnly
import scala.concurrent.Future
import scala.reflect.macros.blackbox

@compileTimeOnly("Enable Macros for expansion")
object Macros {
  def create[C <: Communication](context: blackbox.Context)(implicit c: context.WeakTypeTag[C]): context.Expr[C] = {
    import context.universe._

    implicit val futureTypeTag = typeTag[Future[_]]

    var endPointId: Int = -1
    val declaredMethods = c.tpe.decls.toSet
    val methods = c.tpe.members.collect {
      case symbol if symbol.isMethod & symbol.typeSignature.resultType <:< context.typeOf[Future[_]] => {
        endPointId += 1
        val m = symbol.asMethod
        val declared = declaredMethods.contains(m)
        val resultType = symbol.typeSignature.resultType.typeArgs.head
//        val argsToJson = symbol.typeSignature.paramLists.headOption.map(_.map(_)).getOrElse(0)
        println(s"Method: $m, Declared: $declared, Type: $resultType")
        val pickler =
          q"""
             val pickler = new io.youi.comm.Pickler[$resultType] {
                override def read(json: String): $resultType = upickle.default.read[$resultType](json)
                override def write(t: $resultType): String = upickle.default.write[$resultType](t)
             }
           """
        if (declared) {
          // TODO: listen for received
          // TODO: call local method
          // TODO: send message back
          q"""
             comm.onEndPoint($endPointId) { message =>
               val param = upickle.default.read[$resultType](message.content.get)
               ${m.name}(param).map { response =>
                 upickle.default.write[$resultType](response)
               }
             }
           """
        } else {
          // TODO: support args
          q"""
             override def ${m.name}(): scala.concurrent.Future[$resultType] = {
               val invocationId = comm.nextId
               val content: Option[String] = None
               comm.send := io.youi.comm.CommunicationMessage(comm.id, $endPointId, invocationId, content)
               comm.onInvocation[$resultType](invocationId)( message => {
                 upickle.default.read[$resultType](message.content.get)
               })
             }
           """
        }
      }
    }.toList

    val instance = q"""
         new $c {
           ..$methods
         }
      """
    println(s"Instance: $instance")
    context.Expr[C](instance)

//    context.abort(context.enclosingPosition, "Unable to create!")
  }
}