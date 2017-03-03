package io.youi.server

import java.net.{InetAddress, ServerSocket}

import io.youi.net.IP

import scala.annotation.tailrec

object ServerUtil {
  def isPortAvailable(port: Int, host: String = "127.0.0.1"): Boolean = findAvailablePort(List(port), host).isDefined

  @tailrec
  def findAvailablePort(ports: Seq[Int] = List(0), host: String = "127.0.0.1"): Option[Int] = if (ports.isEmpty) {
    None
  } else {
    try {
      val port = ports.head
      val ss = new ServerSocket(port, 50, InetAddress.getByName(host))
      try {
        Some(ss.getLocalPort)
      } finally {
        ss.close()
      }
    } catch {
      case t: Throwable => findAvailablePort(ports.tail, host)
    }
  }

  def localIPs(): List[IP] = {
    val localhost = InetAddress.getLocalHost.getCanonicalHostName
    val addresses = InetAddress.getAllByName(localhost)
    addresses.toList.map(a => IP(a.getHostAddress))
  }
}