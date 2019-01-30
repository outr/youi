package io.youi.net

import scala.reflect.macros.blackbox
import scala.util.matching.Regex

trait IP extends Location {
  def address: Array[Int]
  def addressString: String

  override def toString: String = addressString
}

object IP {
  lazy val LocalHost = IPv4()

  val IPv4Regex: Regex = """\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\b""".r
  val IPv6Regex: Regex = """([0-9a-fA-F]*):([0-9a-fA-F]*):([0-9a-fA-F]*):([0-9a-fA-F]*):([0-9a-fA-F]*):([0-9a-fA-F]*):([0-9a-fA-F]*):([0-9a-fA-F]*)%?(\d*)""".r

  def get(address: String): Option[IP with Product with Serializable] = address match {
    case IPv4Regex(p1, p2, p3, p4) => Some(IPv4(p1.toInt, p2.toInt, p3.toInt, p4.toInt))
    case IPv6Regex(p1, p2, p3, p4, p5, p6, p7, p8, scope) => Some(IPv6(Some(p1), Some(p2), Some(p3), Some(p4), Some(p5), Some(p6), Some(p7), Some(p8), Option(scope)))
    case _ => None
  }

  def apply(address: String): IP = get(address).getOrElse(throw new NullPointerException(s"Unable to parse: $address to IP address."))
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
          case IPv6(p1, p2, p3, p4, p5, p6, p7, p8, scope) => c.Expr[IP](q"IPv6($p1, $p2, $p3, $p4, $p5, $p6, $p7, $p8, $scope)")
        }
      }
      case _ => c.abort(c.enclosingPosition, "Bad usage of IP interpolation.")
    }
  }
}