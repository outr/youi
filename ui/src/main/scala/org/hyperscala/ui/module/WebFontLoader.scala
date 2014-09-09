package org.hyperscala.ui.module

import com.outr.net.http.session.Session
import org.hyperscala.module.{Interface, Module}
import org.hyperscala.html._
import org.hyperscala.realtime.Realtime
import org.hyperscala.web.{Website, Webpage}
import org.powerscala.Version

/**
 * WebFontLoader is a wrapper around TypeKit's WebFontLoader: https://github.com/typekit/webfontloader
 *
 * @author Matt Hicks <matt@outr.com>
 */
class WebFontLoader[S <: Session] private(webpage: Webpage[S]) {
  webpage.head.contents += new tag.Script(src = s"//ajax.googleapis.com/ajax/libs/webfont/${WebFontLoader.version}/webfont.js")

  private var loaded = Set.empty[String]

  def google(families: List[String]) = {
    val familyNames = families.toSet -- loaded
    if (familyNames.nonEmpty) {
      val js =
        s"""
           |WebFont.load({
           |  google: {
           |    families: [${familyNames.map(f => s"'$f'").mkString(", ")}]
           |  }
           |});
         """.stripMargin
      Realtime.sendJavaScript(webpage, js, onlyRealtime = false)
      loaded ++= familyNames
    }
  }

  def isLoaded(family: String) = loaded.contains(family)
}

object WebFontLoader extends Module {
  val name = "webfontloader"
  val version = Version(1, 4, 7)

  override def dependencies: List[Interface] = super.dependencies

  override def init[S <: Session](website: Website[S]) = {}

  override def load[S <: Session](webpage: Webpage[S]) = {
    apply(webpage)      // Instantiate the WebFontLoader for this webpage
  }

  def apply[S <: Session](webpage: Webpage[S]) = webpage.store.getOrSet("webfontloader", new WebFontLoader(webpage))
}