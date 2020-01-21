package io.youi.net

case class IPv6(parts: Vector[Int], scope: Option[String]) extends IP {
  assert(parts.length == 8, s"IPv6 requires exactly 8 parts, but received: ${parts.mkString("[", ", ", "]")} (${parts.length})")

  private def s(i: Int, canonical: Boolean): String = if (canonical) {
    f"$i%04x"
  } else {
    i.toHexString
  }

  lazy val address: Array[Int] = parts.toArray
  lazy val addressString: String = {
    val base = parts.map(i => s(i, canonical = false)).mkString(":")
    scope match {
      case Some(scp) => s"$base%$scp"
      case None => base
    }
  }
  lazy val canonicalString: String = {
    val base = parts.map(i => s(i, canonical = true)).mkString(":")
    scope match {
      case Some(scp) => s"$base%$scp"
      case None => base
    }
  }

  override def equals(o: scala.Any): Boolean = o match {
    case ip: IPv6 => addressString == ip.addressString
    case _ => false
  }
}

object IPv6 {
  val Empty: IPv6 = IPv6(parts = Vector(0, 0, 0, 0, 0, 0, 0, 1), scope = None)

  def apply(address: String): IPv6 = {
    val percent = address.indexOf('%')
    val (a, scope) = if (percent != -1) {
      (address.substring(0, percent), Some(address.substring(percent + 1)))
    } else {
      (address, None)
    }
    val separator = a.indexOf("::")
    if (separator != -1) {
      val left = a.substring(0, separator).split(':').map(Some.apply).toList
      val right = a.substring(separator + 2).split(':').map(Some.apply).toList
      val middle = (0 until (8 - (left.length + right.length))).map(_ => None).toList
      apply((left ::: middle ::: right).map(toInt).toVector, scope)
    } else {
      apply(a.split(':').map(Some.apply).map(toInt).toVector, scope)
    }
  }

  private def toInt(part: Option[String]): Int = Integer.parseInt(part.getOrElse("0"), 16)
}