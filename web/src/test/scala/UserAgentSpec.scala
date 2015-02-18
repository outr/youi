package org.hyperscala.web

import org.hyperscala.web.useragent.UserAgent
import org.scalatest.Matchers
import org.scalatest.WordSpec

import com.outr.net.Method
import com.outr.net.URL
import com.outr.net.http.request.HttpRequest
import com.outr.net.http.request.HttpRequestHeaders
import com.outr.net.http.response.HttpResponse
import com.outr.net.http.response.HttpResponseStatus
import com.outr.net.http.session.MapSession

/**
 * @author hrj <http://github.com/hrj>
 */
class UserAgentSpec extends WordSpec with Matchers {
  private val chromiumUAStr = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.124 Safari/537.36"
  private val chromiumCorruptedUAStr = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37e.0.2062.124 Safari/537.36"

  "UserAgent" should {
    "parse empty user agent" in {
      val ua = UserAgent("")
      ua.browser.toString() shouldBe("name: unknown, version: 0")
      ua.agentType.toString() shouldBe("Unknown")
      ua.os.toString() shouldBe("name: unknown, family: Unknown, version: 0")
      ua.toString() shouldBe("Browser: unknown (0), OS: Unknown (unknown / 0), Type: Unknown")
    }

    "parse valid user agent string" in {
      val ua = UserAgent(chromiumUAStr)
      checkChromiumUA(ua)
    }

    "parse a bot's user agent string" in {
      val ua = UserAgent("Mozilla/5.0 (compatible; Linux x86_64; Mail.RU_Bot/2.0; +http://go.mail.ru/help/robots)")
      ua.browser.toString() shouldBe("name: unknown, version: 2")
      ua.agentType.toString() shouldBe("Robot")
      ua.os.family.windows shouldBe(false)
      ua.os.family.nix shouldBe(false)
      ua.os.family.apple shouldBe(false)
      ua.os.toString() shouldBe("name: unknown, family: Unknown, version: 0")
      ua.toString() shouldBe("Browser: unknown (2), OS: Unknown (unknown / 0), Type: Robot")
    }

    "parse corrupted user agent string" in {
      val ua = UserAgent(chromiumCorruptedUAStr)
      ua.browser.toString() shouldBe("name: Chrome, version: 37-e.0.2062.124")
      ua.agentType.toString() shouldBe("Browser")
      ua.os.family.windows shouldBe(true)
      ua.os.family.nix shouldBe(false)
      ua.os.family.apple shouldBe(false)
      ua.os.toString() shouldBe("name: Windows 7, family: Windows, version: 6.1")
      ua.toString() shouldBe("Browser: Chrome (37-e.0.2062.124), OS: Windows (Windows 7 / 6.1), Type: Browser")
    }

    "parse" in {
      val website = new Website[MapSession] {
        protected def createSession(request: HttpRequest, id: String) = new MapSession(id, this)
      }

      val webpage = new Webpage(website)
      website.addHandler(webpage, "/test")
      webpage.pageLoadingEvent.on {page => 
        val ua = UserAgent(page)
        checkChromiumUA(ua)

        val Some(ua2) = UserAgent.get(page)
        checkChromiumUA(ua2)
      }

      val headers = HttpRequestHeaders(Map(HttpRequestHeaders.UserAgent -> chromiumUAStr))
      val request = new HttpRequest(new URL(path = "/test"), Method.Get, headers, Map.empty)
      val response = website.onReceive(request, HttpResponse.NotFound)
      response.status shouldBe (HttpResponseStatus.OK)
      response.content.contentType.mimeType shouldBe ("text/html")
      response.content.asString.length shouldBe >(10)

    }
  }

  private def checkChromiumUA(ua:UserAgent) = {
    ua.browser.toString() shouldBe ("name: Chrome, version: 37.0.2062")
    ua.agentType.toString() shouldBe ("Browser")
    ua.os.family.windows shouldBe (true)
    ua.os.family.nix shouldBe (false)
    ua.os.family.apple shouldBe (false)
    ua.os.toString() shouldBe ("name: Windows 7, family: Windows, version: 6.1")
    ua.toString() shouldBe ("Browser: Chrome (37.0.2062), OS: Windows (Windows 7 / 6.1), Type: Browser")
  }
}
