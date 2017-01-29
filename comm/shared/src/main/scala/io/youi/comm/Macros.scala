package io.youi.comm

import com.outr.reactify.Var

import scala.annotation.compileTimeOnly
import scala.concurrent.Future
import scala.reflect.macros.blackbox

@compileTimeOnly("Enable Macros for expansion")
object Macros {
  def shared[T](context: blackbox.Context)(default: context.Expr[T])(implicit t: context.WeakTypeTag[T]): context.Expr[Var[T]] = {
    import context.universe._

    context.Expr[Var[T]](
      q"""
         import io.youi.comm._

         val endPointId = io.youi.comm.Communication.nextEndPointId
         val v = com.outr.reactify.Var[$t]($default)
         val modifying = new ThreadLocal[Boolean] {
           override def initialValue(): Boolean = false
         }
         v.attach { value =>
           if (!modifying.get()) {
             val json = upickle.default.write[$t](value)
             val message = CommunicationMessage(CommunicationMessage.SharedVariable, endPointId, 0, List(json))
             comm.send := message
           }
         }
         comm.receive.attach { message =>
           if (message.endPointId == endPointId && message.messageType == CommunicationMessage.SharedVariable) {
             val value = upickle.default.read[$t](message.content.head)
             modifying.set(true)
             try {
               v := value
             } finally {
               modifying.set(false)
             }
           }
         }
         v
       """)
  }

  def create[C <: Communication](context: blackbox.Context)(implicit c: context.WeakTypeTag[C]): context.Expr[C] = {
    import context.universe._

    implicit val futureTypeTag = typeTag[Future[_]]

    val declaredMethods = c.tpe.decls.toSet
    val methods = c.tpe.members.toList.sortBy(_.fullName).collect {
      case symbol if symbol.isMethod & symbol.typeSignature.resultType <:< context.typeOf[Future[_]] => {
        val endPointId = Communication.nextEndPointId
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
               comm.send := io.youi.comm.CommunicationMessage(io.youi.comm.CommunicationMessage.MethodRequest, $endPointId, invocationId, List(..$params))
               comm.onInvocation[$resultType](invocationId)( message => {
                 upickle.default.read[$resultType](message.content.head)
               })
             }
           """
        }
      }
    }

    val instance = q"""
         new $c {
           ..$methods
         }
      """
//    println(instance)
    context.Expr[C](instance)
  }
}