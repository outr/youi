package org.hyperscala.examples.ui

import org.hyperscala.bootstrap.component._
import org.hyperscala.html.tag
import org.hyperscala.javascript.dsl.window
import org.hyperscala.selector.Selector
import org.hyperscala.ui.module.SocialMetadata
import org.hyperscala.web.Webpage
import org.powerscala.{IO, Color}
import org.hyperscala.BuildInfo
import org.hyperscala.bootstrap.Bootstrap
import org.hyperscala.css.SelectorStyleSheet
import org.hyperscala.html._
import attributes.Target
import org.hyperscala.css.attributes._
import language.reflectiveCalls
import org.hyperscala.ui.module.SocialMetadata._

/**
 * Created by mmynsted on 4/17/15.
 * code@growingliberty.com
 *
 * Example for Social Media Meta data [[org.hyperscala.ui.module.SocialMetadata]]
 *
 */
class SocialMetadataExample extends Webpage {
  this.require(SocialMetadata)
  this.require(Bootstrap)

  //Common Social Meta Data values
  val smTitle = "Hyperscala - SocialMetadata module"
  val smDescription = """Hyperscala module, SocialMetadata provides meta data in meta tags to improve social media
                        |site linking and semantic search engine crawls.""".stripMargin
  val smKeyWords = "Hyperscala, module, social, media, data, twitter, facebook, open, graph"
  val smApplicationName = "Hyperscala"
  val smImage = "http://hyperscala.org/images/hyperscala.png"

  //Head
  title := smTitle
  head.contents += new tag.Link(rel = "stylesheet", href = "/css/style.css")
  head.contents += new tag.Link(href = "/css/social_meta_data.css")

  //Meta data
  head.contents += new tag.Comment("Social Meta Data: START")

  //base data
  head.contents += new tag.Comment("base data")
  SocialMetadata.BaseData(description = smDescription, keywords = Some(smKeyWords),
    applicationName = Some(smApplicationName)).tags foreach(head.contents += _)

  //twitter
  head.contents += new tag.Comment("twitter")
  SocialMetadata.TwitterData(title = smTitle,
    description = smDescription, image = Some(smImage)). tags foreach(head.contents += _)

  //Open Graph / Facebook
  head.contents += new tag.Comment("open graph/facebook")
  SocialMetadata.OpenGraphData(title = smTitle, contentType = "article",
    image = smImage, canonicalUrl = "http://hyperscala.org/example/ui/social_meta_data.html",
  description = smDescription, siteName = Some(smApplicationName)).tags foreach(head.contents += _)

  //Schema.org
  override lazy val html = new tag.HTML { this.makeScoped("Thing")}
  head.contents += new tag.Comment("schema.org")
  SocialMetadata.SchemaDotOrg(name = smTitle, description = smDescription,
    image = smImage).tags foreach(head.contents += _)

  head.contents += new tag.Comment("Social Meta Data: END")

  //main body content
  val main = new tag.Div {
    contents += new StaticHTML(IO.copy(getClass.getClassLoader.getResource("social_meta_data.html")))
  }

  /* Cosmetic (Just make the content a little more readable)
   */
  body.role := "document"

  new SelectorStyleSheet(Selector.element[tag.Body])(body) {
    paddingTop := 100.px
    paddingBottom := 30.px
  }

  def sourceURL: String = null

  val container = new Container {
    clazz += "wrapper"

    if (sourceURL != null) {
      val filename = sourceURL.substring(sourceURL.lastIndexOf('/') + 1)
      contents += new Button(s"View $filename on GitHub", buttonStyle = ButtonStyle.Primary) {
        style.float := Float.Right
        clickEvent := window.open(sourceURL, Target.Blank)
      }
    }
    contents += main
  }
  body.contents += container
  body.contents += new tag.Footer {
    contents += new tag.I {
      style.display := Display.Block
      style.width := 1170.px
      style.marginLeft := Length.Auto
      style.marginRight := Length.Auto
      style.color := Color.White
      style.fontWeight := FontWeight.Bold
      style.paddingBottom := 30.px
      style.fontSize := FontSize.Small
      style.textAlign := Alignment.Right
      contents += s"&copy;2015 Hyperscala.org, version: ${BuildInfo.version}, built: ${f"${BuildInfo.buildTime}%tc"}"
    }
  }

}
