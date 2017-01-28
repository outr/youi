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

        val args = symbol.typeSignature.paramLists.headOption.getOrElse(Nil)

        if (declared) {
          val params = args.zipWithIndex.map {
            case (arg, index) => q"upickle.default.read[${arg.typeSignature.resultType}](message.content($index))"
          }
          if (params.nonEmpty) {
            q"""
             comm.onEndPoint($endPointId) { message =>
               ${m.name}(..$params).map { response =>
                 upickle.default.write[$resultType](response)
               }
             }
           """
          } else {
            q"""
             comm.onEndPoint($endPointId) { message =>
               ${m.name}.map { response =>
                 upickle.default.write[$resultType](response)
               }
             }
           """
          }
        } else {
          val argList = args.map { arg =>
            q"$arg: ${arg.typeSignature.resultType}"
          }
          val params = args.map { arg =>
            val argName = arg.name.toTermName
            q"upickle.default.write[${arg.typeSignature.resultType}]($argName)"
          }
          q"""
             override def ${m.name}(..$argList): scala.concurrent.Future[$resultType] = {
               val invocationId = comm.nextId
               comm.send := io.youi.comm.CommunicationMessage(comm.id, $endPointId, invocationId, List(..$params))
               comm.onInvocation[$resultType](invocationId)( message => {
                 upickle.default.read[$resultType](message.content.head)
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
    println(instance)
    context.Expr[C](instance)
  }
}