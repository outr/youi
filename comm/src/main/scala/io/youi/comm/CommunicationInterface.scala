package io.youi.comm

import profig._
import reactify._
import cats.effect.IO

import scala.language.experimental.macros
import scala.reflect.macros.blackbox

object CommunicationInterface {
  def interface[Interface]()(implicit communicator: Communicator): Interface with CommunicationInterface = macro macroInterface[Interface]
  def implementation[Interface](implementation: Interface): CommunicationImplementation[Interface] = macro macroImplementation[Interface]

  /////////////// Macros ///////////////////////

  def macroInterface[Interface](context: blackbox.Context)()
                               (communicator: context.Expr[Communicator])
                               (implicit interface: context.WeakTypeTag[Interface]): context.Expr[Interface with CommunicationInterface] = {
    import context.universe._

    val methods = lookupMethods(context)(interface.tpe)
    val remoteMethods = methods.map { m =>
      val methodName = m.name.decodedName.toString
      val args = m.typeSignature.paramLists.headOption.getOrElse(Nil)
      val argNames = args.map(_.name.toTermName)
      val argTypes = args.map(_.typeSignature.resultType)
      val jsonify = argNames.map(n => q"${n.decodedName.toString} -> JsonUtil.toJson($n)")
      val params = argNames.zip(argTypes).map {
        case (n, t) => q"$n: $t"
      }
      val returnTypeIO = m.typeSignature.resultType
      val returnType = returnTypeIO.typeArgs.head
      q"""
          override def ${m.name.toTermName}(..$params): $returnTypeIO = communicator
            .method($methodName, Json.obj(..$jsonify))
            .map(_.as[$returnType])
       """
    }
    context.Expr[Interface with CommunicationInterface](
      q"""
          import _root_.io.youi.comm._
          import _root_.profig._
          import _root_.cats.effect._

          new $interface with CommunicationInterface {
            ..$remoteMethods

            override def communicator: Communicator = $communicator
          }
        """)
  }

  def macroImplementation[Interface](context: blackbox.Context)
                                    (implementation: context.Expr[Interface])
                                    (implicit interface: context.WeakTypeTag[Interface]): context.Expr[CommunicationImplementation[Interface]] = {
    import context.universe._

    val methods = lookupMethods(context)(interface.tpe).filter(_.isAbstract)
    val localMethods = methods.map { m =>
      val methodName = m.name.decodedName.toString
      val args = m.typeSignature.paramLists.headOption.getOrElse(Nil)
      val argNames = args.map(_.name.toTermName)
      val argTypes = args.map(_.typeSignature.resultType)
      val params = argNames.zip(argTypes).map {
        case (n, t) => q"""receive.obj(${n.decodedName.toString}).as[$t]"""
      }
      val call = if (m.typeSignature.paramLists.nonEmpty) {
        q"$implementation.${m.name.toTermName}(..$params)"
      } else {
        q"$implementation.${m.name.toTermName}"
      }
      val returnTypeFuture = m.typeSignature.resultType
      val returnType = returnTypeFuture.typeArgs.head
      q"$methodName -> ((receive: Json) => $call.map(response => JsonUtil.toJson[$returnType](response)))"
    }
    context.Expr[CommunicationImplementation[Interface]](
      q"""
            import _root_.io.youi.comm._
            import _root_.profig._
            import _root_.cats.effect._

            new CommunicationImplementation[$interface] {
              private val map: Map[String, Json => IO[Json]] = Map(..$localMethods)

              override def receiveMethod(endpoint: String, receive: Json): IO[Json] = map.get(endpoint) match {
                case Some(f) => f(receive)
                case None => IO.raiseError(new RuntimeException("No endpoint found: " + endpoint))
              }
            }
         """
    )
  }

  private def lookupMethods(context: blackbox.Context)
                           (tpe: context.universe.Type): List[context.Symbol] = {
    import context.universe._

    tpe.members.toList.collect {
      case s if s.isMethod && s.asMethod.isPublic && s.typeSignature.resultType <:< typeOf[IO[Any]] => s
    }
  }
}

trait CommunicationInterface {
  def communicator: Communicator
}