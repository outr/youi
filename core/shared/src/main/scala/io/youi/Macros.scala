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
          case ((raw, pos), index) => {
            if (index > 0) {
              b.append(String.valueOf(args(index - 1)))
            }
            b.append(raw)
          }
        }
        URL(b.toString())
        c.Expr[URL](q"""URL(${b.toString()})""")
      }
      case _ => c.abort(c.enclosingPosition, "Bad usage of url interpolation.")
    }
  }
}
