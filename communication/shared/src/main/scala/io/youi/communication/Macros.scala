package io.youi.communication

import com.outr.reactify.Var
import io.youi.http.Connection

import scala.annotation.compileTimeOnly
import scala.concurrent.Future
import scala.reflect.macros.blackbox

@compileTimeOnly("Enable Macros for expansion")
object Macros {
  private var counter = 0

  def shared[T](context: blackbox.Context)(default: context.Expr[T])(implicit t: context.WeakTypeTag[T]): context.Expr[Var[T]] = {
    import context.universe._

    val endPointId = counter
    counter += 1

    context.Expr[Var[T]](
      q"""
         import io.youi.communication._

         val endPointId = $endPointId
         val v = com.outr.reactify.Var[$t]($default)
         val modifying = new ThreadLocal[Boolean] {
           override def initialValue(): Boolean = false
         }
         v.attach { value =>
           if (!modifying.get()) {
             val json = upickle.default.write[$t](value)
             val message = CommunicationMessage(CommunicationMessage.SharedVariable, endPointId, 0, List(json), None)
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

  def create[C <: Communication](context: blackbox.Context)(connection: context.Expr[Connection])(implicit c: context.WeakTypeTag[C]): context.Expr[C] = {
    import context.universe._

    implicit val futureTypeTag = typeTag[Future[_]]

    val isJS = try {
      context.universe.rootMirror.staticClass("scala.scalajs.js.Any")
      true
    } catch {
      case t: Throwable => false
    }

    val declaredMethods = c.tpe.decls.toSet
    val methods = c.tpe.members.toList.sortBy(_.fullName).collect {
      case symbol if symbol.isMethod & symbol.typeSignature.resultType <:< context.typeOf[Future[_]] => {
        val endPointId = Communication.nextEndPointId
        val m = symbol.asMethod
        val declared = declaredMethods.contains(m)
        val resultType = symbol.typeSignature.resultType.typeArgs.head

        val args = symbol.typeSignature.paramLists.headOption.getOrElse(Nil)
        val annotations = symbol.annotations ::: symbol.overrides.flatMap(_.annotations)
        val isServerMethod = annotations.exists(_.toString == "io.youi.communication.server")
        val isClientMethod = annotations.exists(_.toString == "io.youi.communication.client")

        if (!isServerMethod && !isClientMethod) {
          context.abort(context.enclosingPosition, s"$symbol must be annotated with either @client or @server in the base trait to indicate where they will be implemented (${symbol.overrides.map(_.annotations)}).")
        } else if (isServerMethod && isClientMethod) {
          context.abort(context.enclosingPosition, s"$symbol must have either @client or @server, but not both.")
        }

        if (declared) {
          if (isJS && isServerMethod) {
            context.abort(context.enclosingPosition, s"$symbol is defined as a @server method, but is defined in the client.")
          } else if (!isJS && isClientMethod) {
            context.abort(context.enclosingPosition, s"$symbol is defined as a @client method, but is defined in the server.")
          }
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
          if (isJS && isClientMethod) {
            context.abort(context.enclosingPosition, s"$symbol is defined as a @client method, but is not defined in the client.")
          } else if (!isJS && isServerMethod) {
            context.abort(context.enclosingPosition, s"$symbol is defined as a @server method, but is not defined in the server.")
          }
          val argList = args.map { arg =>
            q"$arg: ${arg.typeSignature.resultType}"
          }
          val params = args.map { arg =>
            val argName = arg.name.toTermName
            q"upickle.default.write[${arg.typeSignature.resultType}]($argName)"
          }
          q"""
             override def ${m.name}(..$argList): scala.concurrent.Future[$resultType] = {
               val invocationId = comm.nextId()
               comm.send := io.youi.communication.CommunicationMessage(io.youi.communication.CommunicationMessage.MethodRequest, $endPointId, invocationId, List(..$params), None)
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
           override def connection = $connection

           ..$methods
         }
      """
//    println(instance)
    context.Expr[C](instance)
  }
}
