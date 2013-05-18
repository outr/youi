package org.hyperscala.ui.module

import org.hyperscala.module.Module
import org.powerscala.{IO, Version}
import org.hyperscala.web.site.{Website, Webpage}

import org.hyperscala.html._
import com.outr.webcommunicator.URL
import java.io.{FileWriter, File}

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
            val url = if (s.src().toLowerCase.startsWith("http")) {
              URL.parse(s.src())
            } else {
              Webpage().url.copy(path = s.src())
            }
            println(s"Script: ${s.src()}, URL: $url")
            val js = IO.copy(new java.net.URL(url.toString))
            println(js)
          }
        }
      }
    }
  }

  private var scriptCache = Map.empty[String, String]
  private var combinedCache = Map.empty[String, ]

  private def createToken(scripts: Seq[tag.Script]) = scripts.map(s => s.src()).sorted.mkString(";")

  private def loadJavaScript(uri: String) = synchronized {
    scriptCache.get(uri) match {
      case Some(js) => js
      case None => {
        val url = if (uri.toLowerCase.startsWith("http")) {
          URL.parse(uri).getOrElse(throw new NullPointerException(s"Unable to download: $uri"))
        } else {
          Webpage().url.copy(path = uri)
        }
        val javaURL = new java.net.URL(url.toString())
        val js = IO.copy(javaURL)
        scriptCache += uri -> js
        js
      }
    }
  }
}