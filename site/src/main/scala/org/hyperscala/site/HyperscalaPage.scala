package org.hyperscala.site


import org.hyperscala.BuildInfo
import org.hyperscala.bootstrap.Bootstrap
import org.hyperscala.bootstrap.component._
import org.hyperscala.css.SelectorStyleSheet
import org.hyperscala.css.attributes._
import org.hyperscala.html._
import org.hyperscala.html.attributes.Target
import org.hyperscala.javascript.dsl.window
import org.hyperscala.selector.Selector
import org.hyperscala.ui.module.GoogleTagManager
import org.hyperscala.web.Webpage
import org.hyperscala.web.module.FormSupport
import org.powerscala.Color

import scala.language.reflectiveCalls

/**
 * @author Matt Hicks <matt@outr.com>
 */
class HyperscalaPage extends Webpage with FormSupport {
  def sourceURL: String = null

  val (container, main) = HyperscalaPage.configure(this, sourceURL)
}

object HyperscalaPage {
  def configure(webpage: Webpage, sourceURL: String) = {
    webpage.require(Bootstrap)
    webpage.require(new GoogleTagManager("GTM-5BNN4T"))

    webpage.title := "Hyperscala - Statically typed bare-metal HTML, CSS, and JavaScript framework for Scala."

    webpage.body.role := "document"

    webpage.head.contents += new tag.Link(rel = "stylesheet", href = "/css/style.css")

    new SelectorStyleSheet(Selector.element[tag.Body])(webpage.body) {
      paddingTop := 100.px
      paddingBottom := 30.px
    }

    webpage.body.contents += new NavBar(brand = Some(new tag.Img(id = "logo", src = "/images/hyperscala.png", alt = "Hyperscala")), brandLink = Some("/"), theme = new NavBarTheme("navbar-light"), right = true) {
      addLink(HyperscalaSite.siteAbout.link, "About")
      addLink(HyperscalaSite.siteExamples.link, "Examples")
      addLink(HyperscalaSite.siteGenerator.link, "Generator")
      addLink(HyperscalaSite.siteDocumentation.link, "Documentation")
      addLink("https://github.com/darkfrog26/hyperscala/", "Project")
    }

    val main = new tag.Div
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
    webpage.body.contents += container
    webpage.body.contents += new tag.Footer {
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
    (container, main)
  }
}