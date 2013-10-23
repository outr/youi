package org.hyperscala.examples.basic

import org.hyperscala.html._
import org.hyperscala.examples.Example
import org.hyperscala.web.useragent.UserAgent

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class UserAgentExample extends Example {
  contents += new tag.Div(content = s"Operating System: ${UserAgent().os}")
  contents += new tag.Div(content = s"Browser: ${UserAgent().browser}")
  contents += new tag.Div(content = s"Type: ${UserAgent().agentType}")
}
