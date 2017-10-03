package specs

import io.youi.http.{CookieHeader, Headers}
import org.scalatest.{Matchers, WordSpec}

class CookieSpec extends WordSpec with Matchers {
  "Cookies" when {
    "parsing" should {
      "support a single entry" in {
        val headers = Headers(Map("Cookie" -> List("first=Hello")))
        val cookies = CookieHeader.value(headers)
        cookies.length should be(1)
        val first = cookies.head
        first.name should be("first")
        first.value should be("Hello")
      }
      "support two entries" in {
        val headers = Headers(Map("Cookie" -> List("first=Hello;second=World")))
        val cookies = CookieHeader.value(headers)
        cookies.length should be(2)
        val first = cookies.head
        first.name should be("first")
        first.value should be("Hello")
        val second = cookies.tail.head
        second.name should be("second")
        second.value should be("World")
      }
      "support a poorly processed value" in {
        val headers = Headers(Map("Cookie" -> List("id=die(pi()*42);; user=assert")))
        val cookies = CookieHeader.value(headers)
        cookies.length should be(2)
        val first = cookies.head
        first.name should be("id")
        first.value should be("die(pi()*42)")
        val second = cookies.tail.head
        second.name should be("user")
        second.value should be("assert")
      }
    }
  }
}
