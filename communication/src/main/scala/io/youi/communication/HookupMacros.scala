package io.youi.communication

import scala.concurrent.{ExecutionContext, Future}
import scala.reflect.macros.blackbox

object HookupMacros {
  private val VariableExtraction = """.*val (\S+)[:].+""".r

  def interface[Interface](context: blackbox.Context)
                          (ec: context.Expr[ExecutionContext])
                          (implicit interface: context.WeakTypeTag[Interface]): context.Expr[Interface with Hookup[Interface]] = {
    import context.universe._

    val connection = context.prefix.tree
    val methods = lookupMethods(context)(interface.tpe)
    val remote = methods.filter(_.isAbstract)

    val name = extractVariableName(context)

    val remoteMethods = remote.map { m =>
      val methodName = m.name.decodedName.toString
      val args = m.typeSignature.paramLists.headOption.getOrElse(Nil)
      val argNames = args.map(_.name.toTermName)
      val argTypes = args.map { arg =>
        arg.typeSignature.resultType
      }
      val jsonify = argNames.map { n =>
        q"${n.decodedName.toString} -> JsonUtil.toJson($n)"
      }
      val params = argNames.zip(argTypes).map {
        case (n, t) => q"$n: $t"
      }
      val returnTypeFuture = m.typeSignature.resultType
      val returnType = returnTypeFuture.typeArgs.head
      val endPoint = s"$name.$methodName"
      q"""
         override def ${m.name.toTermName}(..$params): $returnTypeFuture = {
           val params: Json = Json.obj(..$jsonify)
           val request = Json.obj(
             "endpoint" -> Json.fromString($endPoint),
             "params" -> params
           )
           connection.queue.enqueue(MessageType.Invoke, request).map { response =>
             JsonUtil.fromJson[$returnType](response)
           }
         }
       """
    }

    context.Expr[Interface with Hookup[Interface]](
      q"""
        import _root_.io.youi.communication._
        import _root_.profig._
        import _root_.io.circe._
        import _root_.scala.concurrent.Future

        new Hookup[$interface] with $interface {
          override def name: String = $name
          override def connection: Connection = $connection
          override val local: Map[String, Json => Future[Json]] = Map.empty
          override def instance: $interface = this

          // Define method implementations
          ..$remoteMethods
        }
       """)
  }

  def implementation[Interface, Implementation <: Interface](context: blackbox.Context)
                                                            (ec: context.Expr[ExecutionContext])
                                                            (implicit interface: context.WeakTypeTag[Interface],
                                                                      implementation: context.WeakTypeTag[Implementation]): context.Expr[Implementation with Hookup[Interface]] = {
    import context.universe._

    val connection = context.prefix.tree
    val methods = lookupMethods(context)(interface.tpe)
    val local = methods.filter(_.isAbstract)

    val name = extractVariableName(context)

    val localMethods = local.map { m =>
      val args = m.typeSignature.paramLists.headOption.getOrElse(Nil)
      val argNames = args.map(_.name.toTermName)
      val argTypes = args.map { arg =>
        arg.typeSignature.resultType
      }
      val params = argNames.zip(argTypes).map {
        case (n, t) => {
          val paramName = n.decodedName.toString
          q"""$n = JsonUtil.fromJson[$t](((json \\ "params").head \\ $paramName).head)"""
        }
      }
      val call = if (m.typeSignature.paramLists.nonEmpty) {
        q"instance.${m.name.toTermName}(..$params)"
      } else {
        q"instance.${m.name.toTermName}"
      }
      q"""
         def ${m.name.toTermName}(json: Json): Future[Json] = {
           val future = $call
           future.map { response =>
             JsonUtil.toJson(response)
           }
         }
       """
    }

    val localMappings = local.map { m =>
      val methodName = m.name.decodedName.toString
      q"$methodName -> ((json: Json) => callers.${m.name.toTermName}(json))"
    }

    context.Expr[Implementation with Hookup[Interface]](
      q"""
         import _root_.io.youi.communication._
         import _root_.profig._
         import _root_.io.circe._
         import _root_.scala.concurrent.Future

         new $implementation with Hookup[$interface] {
           override def name: String = $name
           override def connection: Connection = $connection
           override val local: Map[String, Json => Future[Json]] = Map(..$localMappings)
           override def instance: $interface = this

           object callers {
             ..$localMethods
           }
         }
       """
    )
  }

  private def lookupMethods(context: blackbox.Context)
                           (tpe: context.universe.Type): List[context.Symbol] = {
    import context.universe._

    tpe.members.toList.collect {
      case s if s.isMethod && s.asMethod.isPublic && s.typeSignature.resultType <:< typeOf[Future[Any]] => s
    }
  }

  private def extractVariableName(context: blackbox.Context): String = {
    context.enclosingPosition.source.lineToString(context.enclosingPosition.line - 1) match {
      case VariableExtraction(name) => name
      case line => context.abort(context.enclosingPosition, s"Unable to extract variable name from line: $line")
    }
  }
}