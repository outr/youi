package io.youi.communication

import reactify.Var
import io.youi.http.Connection

import scala.annotation.compileTimeOnly
import scala.concurrent.Future
import scala.reflect.macros.blackbox

@compileTimeOnly("Enable Macros for expansion")
object Macros {
  private var counter = 0

  def shared[T](context: blackbox.Context)(default: context.Expr[T])(implicit t: context.WeakTypeTag[T]): context.Expr[Var[T]] = {
    import context.universe._

    val endPoint = counter.toString
    counter += 1

    context.Expr[Var[T]](
      q"""
         import io.youi.communication._

         val v = reactify.Var[$t]($default)
         val modifying = new ThreadLocal[Boolean] {
           override def initialValue(): Boolean = false
         }
         v.attach { value =>
           if (!modifying.get()) {
             val jsonString = profig.JsonUtil.toJsonString[$t](value)
             val message = CommunicationMessage(CommunicationMessage.SharedVariable, $endPoint, 0, List(jsonString), None)
             comm.send := message
           }
         }
         comm.receive.attach { message =>
           if (message.endPoint == $endPoint && message.messageType == CommunicationMessage.SharedVariable) {
             val jsonString = message.content.head
             val value = profig.JsonUtil.fromJsonString[$t](jsonString)
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

    val typeName = c.tpe.toString match {
      case s => s.substring(s.lastIndexOf('.') + 1)
    }
    val isClient = typeName.startsWith("Client")
    val isServer = typeName.startsWith("Server")
    if (!isClient && !isServer) {
      context.abort(context.enclosingPosition, s"${c.tpe} must start with either Client or Server to define what it implements.")
    }
    val baseTypeName = s"${c.tpe.toString.substring(0, c.tpe.toString.lastIndexOf('.') + 1)}${typeName.substring(6)}"

    val declaredMethods = c.tpe.decls.toSet
    val methods = c.tpe.members.toList.sortBy(_.fullName).collect {
      case symbol if symbol.isMethod & symbol.typeSignature.resultType <:< context.typeOf[Future[_]] => {
        val endPoint = s"$baseTypeName.${symbol.name}"
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
          if (isClient && isServerMethod) {
            context.abort(context.enclosingPosition, s"$symbol is defined as a @server method, but is defined in the client.")
          } else if (isServer && isClientMethod) {
            context.abort(context.enclosingPosition, s"$symbol is defined as a @client method, but is defined in the server.")
          }
          val params = args.zipWithIndex.map {
            case (arg, index) =>
              q"""
                 val jsonString = message.content($index)
                 profig.JsonUtil.fromJsonString[${arg.typeSignature.resultType}](jsonString)
               """
          }
          if (params.nonEmpty) {
            q"""
             comm.onEndPoint($endPoint) { message =>
               ${m.name}(..$params).map { response =>
                 profig.JsonUtil.toJsonString[$resultType](response)
               }
             }
           """
          } else {
            q"""
             comm.onEndPoint($endPoint) { message =>
               ${m.name}.map { response =>
                 profig.JsonUtil.toJsonString[$resultType](response)
               }
             }
           """
          }
        } else {
          if (isClient && isClientMethod) {
            context.abort(context.enclosingPosition, s"$symbol is defined as a @client method, but is not defined in the client.")
          } else if (isServer && isServerMethod) {
            context.abort(context.enclosingPosition, s"$symbol is defined as a @server method, but is not defined in the server.")
          }
          val argList = args.map { arg =>
            q"$arg: ${arg.typeSignature.resultType}"
          }
          val params = args.map { arg =>
            val argName = arg.name.toTermName
            q"profig.JsonUtil.toJsonString[${arg.typeSignature.resultType}]($argName)"
          }
          q"""
             override def ${m.name}(..$argList): scala.concurrent.Future[$resultType] = {
               val invocationId = comm.nextId()
               comm.send := io.youi.communication.CommunicationMessage(io.youi.communication.CommunicationMessage.MethodRequest, $endPoint, invocationId, List(..$params), None)
               comm.onInvocation[$resultType](invocationId)( message => {
                 profig.JsonUtil.fromJsonString[$resultType](message.content.head)
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
    context.Expr[C](instance)
  }
}
