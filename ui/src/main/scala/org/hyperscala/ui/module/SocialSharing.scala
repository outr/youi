package org.hyperscala.ui.module

import org.hyperscala.html.constraints.BodyChild
import org.hyperscala.html.tag.{Ul, Span, A}
import org.hyperscala.module.Module
import org.powerscala.log.Logging
import org.powerscala.{IO, Version}
import org.hyperscala.web.{Website,Webpage}
import org.hyperscala.html.tag
import org.hyperscala.jquery.jQuery
import org.hyperscala.ui.convert.MapQueryStringable._

/**
 * Created by mmynsted on 4/6/15.
 * code@growingliberty.com
 */
object SocialSharing extends Module with Logging {
  /*
   * Add rrssb  Ridiculously Responsive Social Sharing Buttons
   *  http://kurtnoble.com/labs/rrssb
   */
  val name = "rrssb"

  /*
   * The version reflects the tagged release as found here
   * https://github.com/kni-labs/rrssb/tags
   */
  val version = Version(1,7,5)

  override def dependencies = List(jQuery)

  override def init(website: Website) =  website.addClassPath("/rrssb/", "rrssb/")

  override def load(webpage: Webpage) = {
    webpage.head.contents += new tag.Link(href = "/rrssb/css/rrssb.css", rel = "stylesheet")
    webpage.head.contents += new tag.Script(mimeType = "text/javascript", src = "/rrssb/js/rrssb.min.js")
  }

  class SocialSharingLinks extends tag.Ul(clazz = Seq("rrssb-buttons", "clearfix"))

  abstract class SocialSharingLink extends tag.Li {
    def iconName: String
    def link: String
    def liClazzSuffix: String = iconName
    def anchorClazz: Seq[String] = Seq("popup")
    def anchorSpanContent: String = iconName
    protected def createIcon: BodyChild = IO.copy(getClass.getClassLoader.getResource(s"rrssb/icons/${iconName}.svg"))
    protected def joinOptionals(m: Map[String, String], optionalPairs: (String, Option[String])*): Map[String, String] =
      optionalPairs.foldLeft(m)((b,a) => a match {
        case (k, Some(v)) => b + (k -> v)
        case _  => b
      })

    clazz += s"rrssb-$liClazzSuffix"
    contents += new tag.A(href = link) {
      clazz ++= anchorClazz
      contents += new tag.Span(clazz = List("rrssb-icon")) {
        contents += createIcon
      }
      contents += new Span(content = anchorSpanContent) {
        clazz += "rrssb-text"
      }
    }
  }

  class EmailButton(subject: String, body: String) extends {
    val iconName = "mail"
    override val anchorClazz = Seq.empty[String]
    override val anchorSpanContent = "email"
    override val liClazzSuffix = "email"
    val link = s"mailto:?${toQueryString(Map("subject" -> subject, "body" -> body))}"
  } with SocialSharingLink

  /**
   * Customization must come from Open Graph tags in the shared URL.
   * @param u is for the link url.
   */
  class FacebookButton(u: String) extends {
    val iconName = "facebook"
    val link = s"https://www.facebook.com/sharer/sharer.php?${toQueryString(Map("u" -> u))}"
  } with SocialSharingLink

  class LinkedInButton(url: String, title: String, summary: Option[String] = None) extends SocialSharingLink {
    lazy val iconName = "linkedin"
    lazy val ld = joinOptionals(Map("mini" -> "true", "url" -> url, "title" -> title), ("summary", summary))
    lazy val link = s"http://www.linkedin.com/shareArticle?${toQueryString(ld)}"
  }
  
  class TwitterButton(status: String) extends {
    val iconName = "twitter"
    val link = s"http://twitter.com/home?${toQueryString(Map("status" -> status))}"
  } with SocialSharingLink

  /** Google+ identifies the parameter as "url", but it seems that it may include other content
    *
    * @param url the link to share as well as other information
    */
  class GoogleplusButton(url: String) extends {
    val iconName = "google_plus"
    override val liClazzSuffix = "googleplus"
    override val anchorSpanContent = "google+"
    val link = s"https://plus.google.com/share?${toQueryString(Map("url" -> url))}"
  } with SocialSharingLink

  class PinterestButton(url: String, description: Option[String] = None, media: Option[String] = None)
    extends SocialSharingLink {
    lazy val iconName = "pinterest"
    lazy val ld = joinOptionals(Map("url" -> url), ("description", description), ("media", media))
    lazy val link = s"http://pinterest.com/pin/create/button/?${toQueryString(ld)}"
  }

  class GithubButton(username: String, project: String) extends {
    val iconName = "github"
    val link = s"https://github.com/$username/$project"
  } with SocialSharingLink
}
