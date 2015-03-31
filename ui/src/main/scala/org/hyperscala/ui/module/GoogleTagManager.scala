package org.hyperscala.ui.module

import org.hyperscala.css.attributes.{Visibility, Display}
import org.hyperscala.module.Module
import org.hyperscala.html._
import org.hyperscala.web.{Website, Webpage}
import org.hyperscala.javascript.dsl._
import org.powerscala.Version

/**
 * @author Matt Hicks <matt@outr.com>
 */
class GoogleTagManager(id: String) extends Module {
  val name = "GoogleTagManager"
  val version = Version(1)

  override def init(website: Website) = {}

  override def load(webpage: Webpage) = {
    webpage.body.contents.insert(0, new tag.Comment("Google Tag Manager"))
    webpage.body.contents.insert(1, new tag.NoScript {
      contents += new tag.IFrame(src = s"//www.googletagmanager.com/ns.html?id=$id", width = "0", height = "0") {
        style.display := Display.None
        style.visibility := Visibility.Hidden
      }
    })
    val js = s"""(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
               |new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],
               |j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=
               |'//www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);
               |})(window,document,'script','dataLayer','$id');""".stripMargin
    webpage.body.contents.insert(2, new tag.Script(content = js))
    webpage.body.contents.insert(3, new tag.Comment("End Google Tag Manager"))
  }
}
