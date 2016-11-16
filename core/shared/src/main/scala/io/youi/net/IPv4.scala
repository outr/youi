package io.youi.net

case class IPv4(part1: Int = 127, part2: Int = 0, part3: Int = 0, part4: Int = 1) extends IP {
  lazy val address = Array(part1, part2, part3, part4)
  lazy val addressString = s"$part1.$part2.$part3.$part4"
}