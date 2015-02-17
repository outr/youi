package org.hyperscala.web

import org.hyperscala.web.useragent.UserAgent
import org.scalatest.Matchers
import org.scalatest.WordSpec

/**
 * @author hrj <http://github.com/hrj>
 */
class UserAgentSpec extends WordSpec with Matchers {

  "UserAgent" should {
    "parse empty user agent" in {
      val ua = UserAgent("")
      ua.browser.toString() shouldBe("name: unknown, version: 0")
      ua.agentType.toString() shouldBe("Unknown")
      ua.os.toString() shouldBe("name: unknown, family: Unknown, version: 0")
    }

    "parse valid user agent string" in {
      val ua = UserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.124 Safari/537.36")
      ua.browser.toString() shouldBe("name: Chrome, version: 37.0.2062")
      ua.agentType.toString() shouldBe("Browser")
      ua.os.family.windows shouldBe(true)
      ua.os.family.nix shouldBe(false)
      ua.os.family.apple shouldBe(false)
      ua.os.toString() shouldBe("name: Windows 7, family: Windows, version: 6.1")
    }
  }
}
