package org.hyperscala.ui.module

import org.hyperscala.module.Module
import org.powerscala.Version
import org.hyperscala.html.tag.Script
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.web.{Website, Webpage}
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <matt@outr.com>
 */
class GoogleAnalytics(key: String) extends Module {
  val name = "google-analytics"
  val version = Version(1, 0)

  override def init(website: Website) = {}

  override def load(webpage: Webpage) = {
    webpage.head.contents += new Script(mimeType = "text/javascript") {
      contents += JavaScriptString("""var _gaq = _gaq || [];
                                     |  _gaq.push(['_setAccount', '%s']);
                                     |  _gaq.push(['_trackPageview']);
                                     |
                                     |  (function() {
                                     |    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
                                     |    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
                                     |    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
                                     |  })();""".stripMargin.format(key))
    }
  }
}
