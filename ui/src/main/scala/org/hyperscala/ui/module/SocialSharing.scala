package org.hyperscala.ui.module

import org.hyperscala.html.constraints.BodyChild
import org.hyperscala.html.tag.{Ul, Span, A}
import org.hyperscala.module.Module
import org.powerscala.log.Logging
import org.powerscala.{IO, Version}
import org.hyperscala.web.{Website,Webpage}
import org.hyperscala.html.tag
import org.hyperscala.jquery.jQuery
import com.outr.net.URL
import org.hyperscala.module.Interface

/**
  * @author Mark Mynsted code@growingliberty.com
 */
object SocialSharing {

  implicit class MapToQueryString(val m: Map[String, String]) extends AnyVal {
    def asQueryString: String = m.foldLeft("") {
      case (s, (k, v)) => s"$s$k=${java.net.URLEncoder.encode(v, "UTF-8").replaceAllLiterally("+", "%20")}&"
    }
  }

  trait SocialSharingBase extends Module with Logging {
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

    override def dependencies: List[Interface] = List(jQuery)

    def stylesheetRef = "/rrssb/css/rrssb.css"
    def javascriptRef = "/rrssb/js/rrssb.min.js"

    override def init(website: Website) =  website.addClassPath("/rrssb/", "rrssb/")

    override def load(webpage: Webpage) = {
      webpage.head.contents += new tag.Link(href = stylesheetRef, rel = "stylesheet")
      webpage.head.contents += new tag.Script(mimeType = "text/javascript", src = javascriptRef)
    }

    def encodeLink(base: String, parms: (String, Option[String])*): String = {
      val decURL = parms.foldLeft(URL.decoded(base))((b,a) => a match {
        case (k, Some(v)) => b.param(k,v)
        case _ => b
      })
      decURL.encoded.toString()
    }

    class SocialSharingLinks extends tag.Ul(clazz = Seq("rrssb-buttons", "clearfix"))

    class SocialSharingLink(val iconName: String, val link: String, val liClazzSuffix: Option[String] = None,
                            val anchorClazz: Seq[String] = Seq("popup"), val anchorSpanContent: Option[String] = None)
      extends tag.Li {
      protected def createIcon: BodyChild = IO.copy(getClass.getClassLoader.getResource(s"rrssb/icons/${iconName}.svg"))
      clazz += s"rrssb-${liClazzSuffix.getOrElse(iconName)}"
      contents += new tag.A(href = link) {
        clazz ++= anchorClazz
        contents += new tag.Span(clazz = List("rrssb-icon")) {
          contents += createIcon
        }
        contents += new Span(content = new tag.Text(anchorSpanContent.getOrElse(iconName))) {
          clazz += "rrssb-text"
        }
      }
    }

    class EmailButton(subject: String, body: String) extends SocialSharingLink(iconName = "mail",
      link = s"mailto:?${Map("subject" -> subject, "body" -> body).asQueryString}", liClazzSuffix = Some("email"),
      anchorClazz = Seq.empty[String], anchorSpanContent = Some("email"))

    /**
     * Customization must come from Open Graph tags in the shared URL.
     * @param u is for the link url.
     */
    class FacebookButton(u: String) extends SocialSharingLink("facebook",
      encodeLink("https://www.facebook.com/sharer/sharer.php", ("u", Some(u))))

    class LinkedInButton(url: String, title: String, summary: Option[String] = None)
      extends SocialSharingLink("linkedin", encodeLink("http://www.linkedin.com/shareArticle", ("mini", Some("true")),
        ("url", Some(url)), ("title", Some(title)), ("summary", summary)))

    class TwitterButton(status: String) extends SocialSharingLink("twitter",
      encodeLink("http://twitter.com/home", ("status", Some(status))))

    /** Google+ identifies the parameter as "url", but it seems that it may include other content
      *
      * @param url the link to share as well as other information
      */
    class GoogleplusButton(url: String) extends SocialSharingLink(iconName = "google_plus",
      link = encodeLink("https://plus.google.com/share", ("url", Some(url))),
      liClazzSuffix = Some("googleplus"), anchorSpanContent = Some("google+"))

    class PinterestButton(url: String, description: Option[String] = None, media: Option[String] = None)
      extends SocialSharingLink("pinterest", encodeLink("http://pinterest.com/pin/create/button/", ("url" -> Some(url)),
          ("description", description), ("media", media)))

    class GithubButton(username: String, project: String)
      extends SocialSharingLink("github",s"https://github.com/$username/$project")
  }
  object SocialSharingModule extends SocialSharingBase
}
