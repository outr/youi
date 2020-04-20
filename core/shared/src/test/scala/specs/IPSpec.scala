package specs

import io.youi.net.{IP, IPv4, IPv6}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

/**
 * @author Matt Hicks <matt@outr.com>
 */
class IPSpec extends AnyWordSpec with Matchers {
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
        val ip = IP("fe80:0:0:0:0:0:0:1%1").asInstanceOf[IPv6]
        ip.parts should equal(Vector(65152, 0, 0, 0, 0, 0, 0, 1))
        ip.scope should be(Some("1"))
        ip.addressString should be("fe80:0:0:0:0:0:0:1%1")
        ip.canonicalString should be("fe80:0000:0000:0000:0000:0000:0000:0001%1")
      }
      "properly parse 2604:ca00:129:99af::860:930a" in {
        val ip = IP("2604:ca00:129:99af::860:930a").asInstanceOf[IPv6]
        ip.parts should equal(Vector(9732, 51712, 297, 39343, 0, 0, 2144, 37642))
        ip.scope should be(None)
        ip.addressString should be("2604:ca00:129:99af:0:0:860:930a")
        ip.canonicalString should be("2604:ca00:0129:99af:0000:0000:0860:930a")
      }
    }
  }
}