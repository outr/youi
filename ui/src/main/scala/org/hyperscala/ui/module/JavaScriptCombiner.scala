package org.hyperscala.ui.module

import org.hyperscala.module.Module
import org.powerscala.Version
import org.hyperscala.web.site.Webpage

import org.hyperscala.html._

/**
 * @author Matt Hicks <matt@outr.com>
 */
object JavaScriptCombiner extends Module {
  val name = "javaScriptCombiner"
  val version = Version(1)

  def init() = {}

  def load() = {
    Webpage().modulesLoaded.on {
      case evt => {
        val scripts = Webpage().html.head.byTag[tag.Script].filter(s => s.src() != null)
        println(s"URL: ${Webpage().url}")
        scripts.foreach {
          case s => {
            val url = Webpage().url.copy(path = s.src())
            println(s"Script: ${s.src()}, URL: $url")
          }
        }
      }
    }
  }
}
