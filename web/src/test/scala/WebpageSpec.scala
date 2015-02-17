package org.hyperscala.web

import org.scalatest.{Matchers, WordSpec}
import com.outr.net.http.session.MapSession
import com.outr.net.http.request.HttpRequest
import com.outr.net.http.response.HttpResponse
import com.outr.net.Method
import com.outr.net.http.request.HttpRequestHeaders
import com.outr.net.URL
import com.outr.net.Protocol

/**
 * @author hrj <http://github.com/hrj>
 */
class WebpageSpec extends WordSpec with Matchers {

  val website = new Website[MapSession] {
    protected def createSession(request: HttpRequest, id: String) = new MapSession(id, this)

  }

  "Webpage" should {
    "have empty body by default" in {
      val webpage = new Webpage(website)
      webpage.body.innerHTML should equal("""""")
    }

    "have standard head by default" in {
      val webpage = new Webpage(website)
      webpage.head.innerHTML should equal(
          """
           |<title>Webpage</title>
           |<meta name="generator" content="Hyperscala"/>
           |<meta charset="UTF-8"/>""".stripMargin('|'))
    }

  }
}
