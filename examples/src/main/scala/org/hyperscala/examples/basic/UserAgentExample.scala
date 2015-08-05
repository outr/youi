package org.hyperscala.examples.basic

import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.web._
import org.hyperscala.web.useragent.UserAgent

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class UserAgentExample extends Webpage with Example {
  override def init(website: Website) = {
    super.init(website)

    body.contents += new tag.Div(content = s"Operating System: ${UserAgent(this).os}")
    body.contents += new tag.Div(content = s"Browser: ${UserAgent(this).browser}")
    body.contents += new tag.Div(content = s"Type: ${UserAgent(this).agentType}")
  }
}