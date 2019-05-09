package io.youi.server

import java.net.{BindException, InetAddress, ServerSocket}

import io.youi.net.IP

object ServerUtil {
  def isPortAvailable(port: Int, host: String = "127.0.0.1"): Boolean = findAvailablePort(List(port), host).isDefined

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
      case exc: BindException if exc.getMessage.startsWith("Address already in use") => findAvailablePort(ports.tail, host)
      case t: Throwable => throw new RuntimeException(s"Error occurred attempting to bind to $host:${ports.head}", t)
    }
  }

  def localIPs(): List[IP] = {
    val localhost = InetAddress.getLocalHost.getCanonicalHostName
    val addresses = InetAddress.getAllByName(localhost)
    addresses.toList.map(a => IP(a.getHostAddress))
  }

  def main(args: Array[String]): Unit = {
    println(s"Available? ${isPortAvailable(80)}")
  }
}