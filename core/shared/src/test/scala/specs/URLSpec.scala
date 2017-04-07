package specs

import io.youi.net._
import org.scalatest.{Matchers, WordSpec}

class URLSpec extends WordSpec with Matchers {
  "URL" when {
    "parsing" should {
      "properly parse a simple URL" in {
        val url = URL("http://www.outr.com")
        url.host should equal("www.outr.com")
        url.protocol should equal(Protocol.Http)
        url.path.encoded should equal("/")
        url.port should equal(80)
      }
      "properly parse a relative URL" in {
        val url = URL("http://www.outr.com/examples/../images/test.png")
        url.path.encoded should equal("/images/test.png")
      }
      "properly parse a relative URL with invalid higher level" in {
        val url = URL("http://www.outr.com/../images/test.png")
        url.path.encoded should equal("/images/test.png")
      }
      "properly parse a relative URL with multiple higher levels" in {
        val url = URL("http://www.outr.com/examples/testing/../../images/../test.png")
        url.path.encoded should equal("/test.png")
      }
      "apply a relative URL to an existing URL" in {
        val url = URL("http://www.outr.com/examples/otherstuff/test.html")
        val updated = url.withPath("../../images/test.png")
        updated.path.encoded should equal("/images/test.png")
      }
      "apply an absolute path to an existing path" in {
        val url = URL("http://www.outr.com/examples/otherstuff/test.html")
        val updated = url.withPath("/absolute.html")
        updated.path.encoded should equal("/absolute.html")
      }
      "properly parse an extremely long URL and spit it back syntactically equal" in {
        val s = "http://www.a.com.qa/pps/a/publish/Pages/System Pages/Document View Page?com.a.b.pagesvc.renderParams.sub-53343f7a_1279673d2a9_-78af0a000136=rp.currentDocumentID=-4591476d_14a4cb0cbbf_-6cb00a000121&"
        val url = URL(s)
        url.protocol should equal(Protocol.Http)
        url.host should equal("www.a.com.qa")
        url.path.encoded should equal("/pps/a/publish/Pages/System%20Pages/Document%20View%20Page")
        url.parameters.value("com.a.b.pagesvc.renderParams.sub-53343f7a_1279673d2a9_-78af0a000136") should equal(Some("rp.currentDocumentID=-4591476d_14a4cb0cbbf_-6cb00a000121"))
      }
      "properly create a decoded URL and encode it" in {
        val url = URL("http://test.com/location").withParam("address", "Oklahoma City, OK")
        url.toString should equal("http://test.com/location?address=Oklahoma%20City%2C%20OK")
        url.parameters.value("address") should equal(Some("Oklahoma City, OK"))
        val encoded = url.encoded.toString
        encoded should equal("http://test.com/location?address=Oklahoma%20City%2C%20OK")
      }
      "properly interpolate a URL" in {
        val url = url"http://www.youi.io"
        url.encoded.toString should equal("http://www.youi.io/")
      }
    }
    "applying parts" should {
      val url = URL("http://www.youi.io/testing/1/test.html?arg1=true")

      "replace one URL with another" in {
        url.withPart("http://google.com") should be(URL("http://google.com"))
      }
      "replace the path" in {
        url.withPart("/index.html") should be(URL("http://www.youi.io/index.html"))
      }
      "replace params" in {
        url.withPart("?wahoo=true") should be(URL("http://www.youi.io/testing/1/test.html?wahoo=true"))
      }
      "replace the path and params" in {
        url.withPart("/index.html?wahoo=true") should be(URL("http://www.youi.io/index.html?wahoo=true"))
      }
      "apply relative path" in {
        url.withPart("../2/test.html") should be(URL("http://www.youi.io/testing/2/test.html"))
      }
    }
  }
}
