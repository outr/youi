package io.youi

import io.youi.net.URL

import scala.annotation.compileTimeOnly
import scala.language.experimental.macros
import scala.reflect.macros.blackbox

@compileTimeOnly("Enable compile-time Macros for expansion")
object Macros {
  def url(c: blackbox.Context)(args: c.Expr[Any]*): c.Expr[URL] = {
    import c.universe._

    c.prefix.tree match {
      case Apply(_, List(Apply(_, rawParts))) => {
        val parts = rawParts map { case t @ Literal(Constant(const: String)) => (const, t.pos) }

        val b = new StringBuilder
        parts.zipWithIndex.foreach {
          case ((raw, _), index) => {
            if (index > 0) {
              c.abort(c.enclosingPosition, "URL interpolation can only contain string literals. Use URL.apply for runtime parsing.")
            }
            b.append(raw)
          }
        }
        val url = URL(b.toString())
        val protocol = url.protocol.scheme
        val host = url.host
        val port = url.port
        val path = url.path.decoded
        val parameters = url.parameters.entries.map(t => t._1 -> t._2.values)
        val fragment = url.fragment
        c.Expr[URL](q"URL.build(protocol = $protocol, host = $host, port = $port, path = $path, parameters = $parameters, fragment = $fragment)")
      }
      case _ => c.abort(c.enclosingPosition, "Bad usage of url interpolation.")
    }
  }
}
