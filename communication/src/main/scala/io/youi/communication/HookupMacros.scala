package io.youi.communication

import scala.concurrent.Future
import scala.reflect.macros.blackbox

object HookupMacros {
  def interface[Interface](context: blackbox.Context)
                          (connection: context.Expr[Connection])
                          (implicit interface: context.WeakTypeTag[Interface]): context.Expr[Interface with Hookup[Interface]] = {
    import context.universe._

    val methods = lookupMethods(context)(interface.tpe)
    val remote = methods.filter(_.isAbstract)

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
      q"""
         override def ${m.name.toTermName}(..$params): $returnTypeFuture = {
           val params: Json = Json.obj(..$jsonify)
           val request = Json.obj(
             "endpoint" -> Json.fromString($methodName),
             "params" -> params
           )
           connection.queue.enqueue(request).map { response =>
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
          override def connection: Connection = $connection
          override val local: Map[String, Json => Future[Json]] = Map.empty
          override def instance: $interface = this

          // Define method implementations
          ..$remoteMethods
        }
       """)
  }

  def implementation[Interface, Implementation <: Interface](context: blackbox.Context)
                               (connection: context.Expr[Connection])
                               (implicit interface: context.WeakTypeTag[Interface],
                                         implementation: context.WeakTypeTag[Implementation]): context.Expr[Implementation with Hookup[Interface]] = {
    import context.universe._

    val methods = lookupMethods(context)(interface.tpe)
    val local = methods.filter(_.isAbstract)

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
      q"""
         def ${m.name.toTermName}(json: Json): Future[Json] = {
           val future = instance.${m.name.toTermName}(..$params)
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
}