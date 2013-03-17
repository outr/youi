package org.hyperscala.site

import org.hyperscala.html._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class HyperscalaDocumentation extends HyperscalaPage {
  main.contents += new tag.H1(content = "Hyperscala Documentation")
  main.contents += new tag.Ul {
    l("Hyperscala: An Introduction", "http://www.matthicks.com/2013/01/hyperscala-introduction.html")
    l("Hyperscala: Getting Started", "http://www.matthicks.com/2013/01/hyperscala-getting-started.html")
    l("Hyperscala: Chat Example", "http://www.matthicks.com/2013/01/hyperscala-chat-example.html")
    l("Hyperscala: Why Not Play?", "http://www.matthicks.com/2013/01/hyperscala-why-not-play.html")

    def l(title: String, link: String) = contents += new tag.Li {
      contents += new tag.A(href = link, content = title)
    }
  }
}
