package org.hyperscala.ui.module

import org.hyperscala.html.tag.{Ul, Span, A}
import org.hyperscala.module.Module
import org.powerscala.{IO, Version}
import org.hyperscala.web.{Website,Webpage}
import org.hyperscala.html.tag
import org.hyperscala.jquery.jQuery
import org.hyperscala.ui.convert.MapQueryStringable._

/**
 * Created by mmynsted on 4/6/15.
 * code@growingliberty.com
 */
object SocialSharing extends Module {
  /*
   * Add rrssb  Ridiculously Responsive Social Sharing Buttons
   *  http://kurtnoble.com/labs/rrssb
   */
  val name = "rrssb"

  /*
   * The version reflects the tagged release as found here
   * https://github.com/kni-labs/rrssb/tags
   */
  val version = Version(1,6,5)

  override def dependencies = List(jQuery)

  val cdnBase = s"//oss.maxcdn.com/rrssb/${version.general}/"

  //Basic information used by messages
  type MessageData = Map[String, String]

  def listTag(md: MessageData, buttons: Seq[SocialMediaButton] = Seq.empty[SocialMediaButton]): tag.Ul = {
    val mediaButtons: Seq[SocialMediaButton] = if (buttons.isEmpty) {

      Seq(new EmailButton(md), new FacebookButton(md), new LinkedinButton(md), new TwitterButton(md),
        new GoogleplusButton(md), new PinterestButton(md), new GithubButton(md))
    }
    else
      buttons

    new Ul(clazz = Seq("rrssb-buttons", "clearfix")) {
      for (b <- mediaButtons) contents += b.itemTag(b.anchorTag)
    }
  }

  abstract class SocialMediaButton (messageData: MessageData, customIcon: String = null,
                                    customClazz: Seq[String] = null) {
    def name: String
    def itemTagClassDesc(n: String = null): String = s"rrssb-${Option(n).getOrElse(name)}"
    def anchorSpanContent: String = name
    def getDefaultIcon = IO.copy(getClass.getClassLoader.getResource(s"rrssb/icons/${name}.svg"))
    def icon: String = Option(customIcon).getOrElse(getDefaultIcon)
    def anchorClazz: Seq[String] = Option(customClazz).getOrElse(Seq("popup"))
    def link: String = buildLink(messageData)
    def anchorTag: tag.A = new A(href = link) {
      clazz ++= anchorClazz
      contents += new Span() {
        contents += icon
        clazz += "rrssb-icon"
      }
      contents += new Span(content = anchorSpanContent) {
        clazz += "rrssb-text"
      }
    }
    def itemTag(anchor: tag.A) = new tag.Li(content = anchor) {
      clazz += itemTagClassDesc()
    }

    /**
     * Given a {{MessageData}} {{Map}}, convert to query string for link.
     * Likely should contain a conversion from {{MessageData}} {{Map}} to
     * {{LinkData}} {{Map}} before generating the final {{String}}.
     * @param md
     * @return
     */
    def buildLink(md: MessageData): String

  }

  object SocialMediaButton {
    def name = null
  }

  class EmailButton(messageData: MessageData, customIcon: String = null, customClazz: Seq[String] = Seq.empty[String])
    extends SocialMediaButton(messageData, customIcon, customClazz) {
    val name = "mail"
    override def itemTagClassDesc(n: String) = super.itemTagClassDesc("email")
    override val anchorSpanContent = "email"

    def buildLink(md: MessageData): String = {
      val ld: LinkData = Map("subject" -> md.getOrElse("title", ""),
      "body" -> s"${md.getOrElse("description", "")} ${md.getOrElse("shareLink", "")}")
      s"mailto:?${toQueryString(ld)}"
    }
  }

  class FacebookButton(messageData: MessageData, customIcon: String = null, customClazz: Seq[String] = null)
    extends SocialMediaButton(messageData, customIcon, customClazz) {
    val name = "facebook"

    def buildLink(md: MessageData): String = {
      val ld = Map("u" -> md.getOrElse("shareLink", ""))
      s"https://www.facebook.com/sharer/sharer.php?${toQueryString(ld)}"
    }
  }

  class LinkedinButton(messageData: MessageData, customIcon: String = null, customClazz: Seq[String] = null)
    extends SocialMediaButton(messageData, customIcon, customClazz) {
    val name ="linkedin"

    def buildLink(md: MessageData): String = {
      val ld: LinkData = Map("mini" -> "true", "url" -> md.getOrElse("shareLink", ""),
        "title" -> md.getOrElse("title", ""),
      "summary" -> md.getOrElse("description", ""))
      s"http://www.linkedin.com/shareArticle?${toQueryString(ld)}"
    }
  }

  class TwitterButton(messageData: MessageData, customIcon: String = null, customClazz: Seq[String] = null)
    extends SocialMediaButton(messageData, customIcon, customClazz) {
    val name = "twitter"

    def buildLink(md: MessageData): String = {
      val ld: LinkData = Map("status" -> s"${md.getOrElse("description", "")} ${md.getOrElse("shareLink","")}")
      s"http://twitter.com/home?${toQueryString(ld)}"
    }
  }

  class GoogleplusButton(messageData: MessageData, customIcon: String = null, customClazz: Seq[String] = null)
    extends SocialMediaButton(messageData, customIcon, customClazz) {
    val name = "google_plus"
    override def itemTagClassDesc(n: String) = super.itemTagClassDesc("googleplus")
    override val anchorSpanContent = "google+"

    def buildLink(md: MessageData): String = {
      val ld: LinkData = Map("url" -> s"${md.getOrElse("description", "")} ${md.getOrElse("shareLink", "")}")
      s"https://plus.google.com/share?${toQueryString(ld)}"
    }
  }

  class PinterestButton(messageData: MessageData, customIcon: String = null, customClazz: Seq[String] = null)
    extends SocialMediaButton(messageData, customIcon, customClazz) {
    val name = "pinterest"

    def buildLink(md: MessageData): String = {
      //example for if "media" key was optional...
      val base: LinkData = Map("url" -> md.getOrElse("shareLink", ""),
        "description" -> md.getOrElse("description", ""))

      val ld = md.get("imageLink") match {
        case Some(v) => base + ("media" -> v)
        case _ => base
      }
      s"http://pinterest.com/pin/create/button/?${toQueryString(ld)}"
    }
  }

  class GithubButton(messageData: MessageData, customIcon: String = null, customClazz: Seq[String] = null)
    extends SocialMediaButton(messageData, customIcon, customClazz) {
    val name = "github"

    def buildLink(md: MessageData): String = "https://github.com/darkfrog26/hyperscala"
  }
  override def init(website: Website) = {
    website.addClassPath("/rrssb/", "rrssb/")
  }

  override def load(webpage: Webpage) = {
    webpage.head.contents += new tag.Link(href = "/rrssb/css/rrssb.css", rel = "stylesheet")
    webpage.head.contents += new tag.Script(mimeType = "text/javascript", src = "/rrssb/js/rrssb.min.js")
  }
}
