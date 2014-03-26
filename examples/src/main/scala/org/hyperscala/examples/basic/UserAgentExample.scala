package org.hyperscala.examples.basic

import org.hyperscala.html._
import org.hyperscala.examples.Example
import org.hyperscala.web.useragent.UserAgent
import org.hyperscala.web.Webpage
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class UserAgentExample extends Example {
  connected[Webpage[Session]] {
    case webpage => {
      contents += new tag.Div(content = s"Operating System: ${UserAgent(webpage).os}")
      contents += new tag.Div(content = s"Browser: ${UserAgent(webpage).browser}")
      contents += new tag.Div(content = s"Type: ${UserAgent(webpage).agentType}")
    }
  }
}
