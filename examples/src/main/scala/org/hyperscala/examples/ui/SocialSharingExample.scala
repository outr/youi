package org.hyperscala.examples.ui

import org.hyperscala.examples.Example
import org.hyperscala.html.StaticHTML
import org.hyperscala.ui.module.SocialSharing._
import org.powerscala.IO
import org.hyperscala.html._

/**
 * Created by mmynsted on 4/6/15.
 * code@growingliberty.com
 */
class SocialSharingExample extends Example {
  this.require(SocialSharingModule)
  import SocialSharingModule._

  val title = "Check out Hyperscala.com"
  val description = "Hyperscala is a statically typed bare-metal HTML, CSS, and JavaScript framework for Scala."
  val imageLink = "http://hyperscala.com/images/hyperscala.png"
  val shareLink = "http://hyperscala.com"

  contents += new tag.Div {
    style.padding := "1em"
    contents += s"Social sharing buttons (${version.general}):"
    contents += new SocialSharingLinks {
      contents += new EmailButton(title, s"$description $shareLink")
      contents += new FacebookButton(shareLink)
      contents += new LinkedInButton(shareLink, title, Some(description))
      contents += new TwitterButton(s"$description $shareLink $imageLink")
      contents += new GoogleplusButton(s"$description $shareLink")
      contents += new PinterestButton(shareLink, Some(description), Some(imageLink))
      contents += new GithubButton(username = "darkfrog26", project = "hyperscala")
    }
  }
  contents += new StaticHTML(IO.copy(getClass.getClassLoader.getResource("social_sharing.html")))
}
