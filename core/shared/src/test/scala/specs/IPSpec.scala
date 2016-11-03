package specs

import io.youi.net.{IP, IPv4, IPv6}
import org.scalatest.{Matchers, WordSpec}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class IPSpec extends WordSpec with Matchers {
  "IP" when {
    "parsing IPv4 addresses" should {
      "properly parse 127.0.01" in {
        val ip = IP("127.0.0.1")
        ip should equal(IPv4(127, 0, 0, 1))
      }
      "properly parse 1.2.3.4" in {
        val ip = IP("1.2.3.4")
        ip should equal(IPv4(1, 2, 3, 4))
      }
      "resolve None for 256.0.0.1" in {
        IP.get("256.0.0.1") should equal(None)
      }
    }
    "parsing IPv6 addresses" should {
      "properly parse fe80:0:0:0:0:0:0:1%1" in {
        val ip = IP("fe80:0:0:0:0:0:0:1%1")
        ip should equal(IPv6(part1 = Some("fe80"), scope = Some("1")))
      }
    }
  }
}
