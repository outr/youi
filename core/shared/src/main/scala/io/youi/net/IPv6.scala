package io.youi.net

case class IPv6(part1: Option[String] = None, part2: Option[String] = None, part3: Option[String] = None, part4: Option[String] = None, part5: Option[String] = None, part6: Option[String] = None, part7: Option[String] = None, part8: Option[String] = Some("1"), scope: Option[String] = None) extends IP {
  private def s(part: Option[String]) = part.getOrElse("0")
  def p1 = s(part1)
  def p2 = s(part2)
  def p3 = s(part3)
  def p4 = s(part4)
  def p5 = s(part5)
  def p6 = s(part6)
  def p7 = s(part7)
  def p8 = s(part8)

  def toInt(part: Option[String]) = Integer.parseInt(part.getOrElse("0"), 16)

  lazy val address = Array(toInt(part1), toInt(part2), toInt(part3), toInt(part4), toInt(part5), toInt(part6), toInt(part7), toInt(part8))
  lazy val addressString = s"$p1:$p2:$p3:$p4:$p5:$p6:$p7:$p8${scope.map(s => s"%$s").getOrElse("")}"

  override def equals(o: scala.Any) = o match {
    case ip: IPv6 => addressString == ip.addressString
    case _ => false
  }
}