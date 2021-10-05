package io.youi.net

import scala.reflect.macros.blackbox
import scala.util.matching.Regex

trait IP extends Location {
  def address: Array[Int]
  def addressString: String

  override def toString: String = addressString
}

object IP {
  lazy val LocalHost: IP = IPv4()

  val IPv4Regex: Regex = """\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\b""".r

  def get(address: String): Option[IP] = address match {
    case IPv4Regex(p1, p2, p3, p4) => Some(IPv4(p1.toInt, p2.toInt, p3.toInt, p4.toInt))
    case _ if IPv6.isValid(address) => Some(IPv6(address))
    case _ => None
  }

  def apply(address: String): IP = {
    get(address).getOrElse(throw new NullPointerException(s"Unable to parse: $address to IP address."))
  }
  def apply(address: Array[Byte]): IP = if (address.length == 4) {
    IPv4(address(0).toInt, address(1).toInt, address(2).toInt, address(3).toInt)
  } else {
    throw new RuntimeException(s"Address must have exactly four entries: ${address.mkString("[", ", ", "]")}")
  }

  def interpolate(c: blackbox.Context)(args: c.Expr[Any]*): c.Expr[IP] = {
    import c.universe._

    c.prefix.tree match {
      case Apply(_, List(Apply(_, rawParts))) => {
        val parts = rawParts map { case t @ Literal(Constant(const: String)) => (const, t.pos) }

        val b = new StringBuilder
        parts.zipWithIndex.foreach {
          case ((raw, _), index) => {
            if (index > 0) {
              c.abort(c.enclosingPosition, "IP interpolation can only contain string literals. Use IP.apply for runtime parsing.")
            }
            b.append(raw)
          }
        }
        IP(b.toString()) match {
          case IPv4(p1, p2, p3, p4) => c.Expr[IP](q"IPv4($p1, $p2, $p3, $p4)")
          case IPv6(p, scope) => c.Expr[IP](q"IPv6($p, $scope)")
        }
      }
      case _ => c.abort(c.enclosingPosition, "Bad usage of IP interpolation.")
    }
  }
}