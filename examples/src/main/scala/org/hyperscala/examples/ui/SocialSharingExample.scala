package org.hyperscala.examples.ui

import org.hyperscala.examples.Example
import org.hyperscala.html.StaticHTML
import org.hyperscala.ui.module.SocialSharing
import org.hyperscala.ui.module.SocialSharing.MessageData
import org.powerscala.IO
import org.hyperscala.html._

/**
 * Created by mmynsted on 4/6/15.
 * code@growingliberty.com
 */
class SocialSharingExample extends Example {
  this.require(SocialSharing)

  val messageData: MessageData = Map("title" -> "Check out Hyperscala.com",
    "description" -> "Hyperscala is a statically typed bare-metal HTML, CSS, and JavaScript framework for Scala.",
    "imageLink" -> "http://hyperscala.com/images/hyperscala.png",
    "shareLink" -> "http://hyperscala.com",
    "githubLink" -> "https://github.com/darkfrog26/hyperscala")

  contents += new tag.Div {
    style.padding := "1em"
    contents += s"Social sharing buttons (${SocialSharing.version.general}):"
    contents += SocialSharing.listTag(messageData)
  }
  contents += new StaticHTML(IO.copy(getClass.getClassLoader.getResource("social_sharing.html")))
}
