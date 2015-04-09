package org.hyperscala.examples.ui

import org.hyperscala.examples.Example
import org.hyperscala.html.StaticHTML
import org.hyperscala.ui.module.SocialSharing
import org.powerscala.IO
import org.hyperscala.html._

/**
 * Created by mmynsted on 4/6/15.
 * code@growingliberty.com
 */
class SocialSharingExample extends Example {
  this.require(SocialSharing)

  contents += new tag.Div {
    style.padding := "1em"
    contents += s"Social sharing buttons (${SocialSharing.version.general}):"
    contents += SocialSharing.listTag()
  }
  contents += new StaticHTML(IO.copy(getClass.getClassLoader.getResource("social_sharing.html")))
}
