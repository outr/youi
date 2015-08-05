package org.hyperscala.examples.ui

import org.hyperscala.examples.Example
import org.hyperscala.html.{StaticHTML, _}
import org.hyperscala.ui.module.SocialSharing._
import org.hyperscala.web.Webpage
import org.powerscala.IO

/**
 * @author Mark Mynsted code@growingliberty.com
 */
class SocialSharingExample extends Webpage with Example {
  require(SocialSharingModule)
  import SocialSharingModule._

  val ssTitle = "Check out Hyperscala.com"
  val description = "Hyperscala is a statically typed bare-metal HTML, CSS, and JavaScript framework for Scala."
  val imageLink = "http://hyperscala.com/images/hyperscala.png"
  val shareLink = "http://hyperscala.com"

  body.contents += new tag.Div {
    style.padding := "1em"
    contents += s"Social sharing buttons (${version.general}):"
    contents += new SocialSharingLinks {
      contents += new EmailButton(ssTitle, s"$description $shareLink")
      contents += new FacebookButton(shareLink)
      contents += new LinkedInButton(shareLink, ssTitle, Some(description))
      contents += new TwitterButton(s"$description $shareLink $imageLink")
      contents += new GoogleplusButton(s"$description $shareLink")
      contents += new PinterestButton(shareLink, Some(description), Some(imageLink))
      contents += new GithubButton(username = "darkfrog26", project = "hyperscala")
    }
  }
  body.contents += new StaticHTML(IO.copy(getClass.getClassLoader.getResource("social_sharing.html")))
}
