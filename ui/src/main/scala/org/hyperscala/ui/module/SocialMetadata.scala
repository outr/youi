package org.hyperscala.ui.module

import org.hyperscala.Tag
import org.hyperscala.html.tag.Meta
import org.hyperscala.module.Module
import org.hyperscala.web.{Website, Webpage}
import org.powerscala.Version

/**
 * Created by mmynsted on 4/17/15.
 * code@growingliberty.com
 *
 * Provide simple meta data to improve social media linking
 */
object SocialMetadata extends Module {
  val name = "socialmetadata"
  val version = Version(1,0,0)

  override def init(website: Website): Unit = {}
  override def load(webpage: Webpage): Unit = {}

  trait SMData {
    def tags: Vector[Meta]
    def buildMetaData(j: (String, Option[String]) => Option[Meta])(data: (String, Option[String])*) = {
      data.foldLeft(Vector.empty[Meta])((b, a) => j(a._1, a._2) match {
        case Some(datum) => b :+ datum
        case _ => b
      })
    }
    def maybeDatum(k: String, maybeV: Option[String]) = maybeV map (v => new Meta {
      content := v
      name := k
    })
    def toOptionalValues(s: Seq[(String, String)]) = s map{case (k,v) => (k, Option(v))}
  }

  /**
   * Basic Data applies to various social media site, search engines, etc.
   *
   * @param description of the page content
   * @param keywords to help identify the page and its content
   * @param author of the page when applicable
   * @param copyright when applicable
   * @param applicationName is the application-name for the site
   * @param extras is an optional Seq of extra parameters
   */
  case class BaseData(description: String, keywords: Option[String] = None, author: Option[String] = None,
                      copyright: Option[String] = None, applicationName: Option[String] = None,
                      extras: Seq[(String, String)] = Seq.empty[(String, String)]) extends SMData {
    def tags = {
      val items = Seq(("description", Some(description)), ("keywords", keywords), ("author", author),
        ("copyright", copyright), ("application-name", applicationName)) ++ toOptionalValues(extras)
      buildMetaData(maybeDatum)(items: _*)
    }
  }

  /**
   * Twitter specific meta data.
   *
   * @param title of page
   * @param description of page content
   * @param image that illustrates the meaning of the page
   * @param extras is an optional Seq of extra parameters
   */
  case class TwitterData(title: String, description: String, image: Option[String] = None,
                          extras: Seq[(String, String)] = Seq.empty[(String, String)]) extends SMData {
    override def maybeDatum(k: String, maybeV: Option[String]) = super.maybeDatum(s"twitter:$k", maybeV)
    def tags = {
      val items = Seq(("card", Some("summary")), ("title", Some(title)),
        ("description", Some(description)), ("image", image)) ++ toOptionalValues(extras)
      buildMetaData(maybeDatum)(items: _*)
    }
  }

  /**
   * Open Graph meta data. This is used mostly for Facebook though other sites/applications do read this.
   *
   * @param title of the page
   * @param contentType of the page, using the specific keyword.  Look [[http://opengraphprotocol.org/#types here]]. "type"
   * @param image that illustrates the meaning of the page, specific to the type selected.
   * @param canonicalUrl is the single authoritative URL (in String format) for the given item of the given contentType.
   * @param description of the item and page
   * @param siteName where this page resides. "site_name"
   * @param extras is an optional Seq of extra parameters
   */
  case class OpenGraphData(title: String, contentType: String, image: String, canonicalUrl: String,
                           description: String, siteName: Option[String] = None,
                           extras: Seq[(String, String)] = Seq.empty[(String, String)]) extends SMData {
    override def maybeDatum(k: String, maybeV: Option[String]) = maybeV map (v => new Meta {
        content := v
        this.attribute[String]("property", true) foreach (_ := s"og:$k")
      })
    def tags = {
      val items = Seq(("title", Some(title)), ("type", Some(contentType)),
        ("image", Some(image)), ("url", Some(canonicalUrl)), ("description", Some(description)),
        ("site_name", siteName)) ++ toOptionalValues(extras)
      buildMetaData(maybeDatum)(items: _*)
    }
  }

  /**
   * Schema.org - A robust and complicated meta data system.  Used by Google+ and others.
   * Look at [[http://schema.org schema.org]]
   *
   * @param name of the item
   * @param description of the item
   * @param image can be URL or an ImageObject. (URL would be more compatible with other meta data)
   * @param extras optional Seq of extra parameters
   */
  case class SchemaDotOrg(name: String, description: String, image: String,
                          extras: Seq[(String, String)] = Seq.empty[(String, String)]) extends SMData {
    override def maybeDatum(k: String, maybeV: Option[String]) = maybeV map (v => new Meta {
      content := v
      this.attribute[String]("itemprop", true) foreach (_ := k)
    })
    def tags = {
      val items = Seq(("name", Some(name)), ("description", Some(description)),
        ("image", Some(image))) ++ toOptionalValues(extras)
      buildMetaData(maybeDatum)(items: _*)
    }
  }

  /**
   * Schema.org needs to have the content inside a ''scoped'' tag.  To be compatible with the other meta data the
   * expectation is that this will be used on the `HTML` tag and the page level schema.org markup will be added to
   * the `HEAD` with Meta tags via `SchemaDotOrg` method.
   *
   * @param t is the tag, likely `HTML` to be decorated with the scope identifier and the itemtype.
   */
  implicit class ScopedTag(val t: Tag) extends AnyVal {
    def makeScoped(itemType: String): Unit = {
      t.attribute[Boolean]("itemscope", true) foreach(_ := true)
      t.attribute[String]("itemtype", true) foreach(_:= s"http://schema.org/$itemType")
    }
  }
}
