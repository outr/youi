package io.youi.client

import java.net.InetAddress

import io.youi.net.IP

import scala.util.Try

trait DNS {
  def lookup(hostName: String): Option[IP]
}

object DNS {
  object default extends DNS {
    override def lookup(hostName: String): Option[IP] = Try(Option(InetAddress.getByName(hostName)).map { a =>
      IP(a.getAddress)
    }).getOrElse(None)
  }

  def apply(overrides: Map[String, IP]): DNS = {
    val map = overrides.map {
      case (hostName, ip) => hostName.toLowerCase -> ip
    }
    new DNS {
      override def lookup(hostName: String): Option[IP] = map.get(hostName.toLowerCase).orElse(default.lookup(hostName))
    }
  }
}