package org.hyperscala.ui.module

import org.hyperscala.module.Module
import org.powerscala.{IO, Version}
import org.hyperscala.web.site.{Website, Webpage}

import org.hyperscala.html._
import com.outr.webcommunicator.URL
import com.outr.webcommunicator.netty._
import com.outr.webcommunicator.netty.handler.RequestHandler
import org.jboss.netty.handler.codec.http.HttpRequest
import org.jboss.netty.channel.{MessageEvent, ChannelHandlerContext}

/**
 * @author Matt Hicks <matt@outr.com>
 */
object JavaScriptCombiner extends URLWebResource with RequestHandler with Module {
  val name = "javaScriptCombiner"
  val version = Version(1)

  private var exclusions = List.empty[String]

  /**
   * Allows specifically excluding a JavaScript URI from being combined. This is useful for things like LESS that need
   * to be explicitly placed at the end of head where combiner places JavaScript replacing the first script.
   */
  def exclude(uri: String) = synchronized {
    exclusions = uri :: exclusions
  }

  def init() = {
    Website().register(this)      // Handle all combined javascript requests
  }

  def load() = {
    Webpage().modulesLoaded.on {
      case evt => {
        val scripts = Webpage().html.head.byTag[tag.Script].filter(s => s.src() != null && !exclusions.contains(s.src()))
        if (scripts.nonEmpty) {
          val combinedName = combineScripts(scripts)                        // Get or create combined script name
          val index = Webpage().html.head.contents.indexOf(scripts.head)    // Determine the index of the first script
          scripts.foreach(s => s.removeFromParent())                        // Remove all scripts
          Webpage().html.head.contents.insert(index, new tag.Script(src = combinedName, mimeType = "text/javascript"))  // Insert the combined script
        }
      }
    }
  }

  private var scriptCache = Map.empty[String, String]
  private var combinedNameByToken = Map.empty[String, String]
  private var scriptsByCombinedName = Map.empty[String, List[String]]

  private def combineScripts(scripts: Seq[tag.Script]) = synchronized {
    val token = createToken(scripts)
    combinedNameByToken.get(token) match {
      case Some(scriptName) => scriptName
      case None => {
        val scriptName = createCombinedName()
        val list = createList(scripts)
        scriptsByCombinedName += scriptName -> list
        combinedNameByToken += token -> scriptName
        scriptName
      }
    }
  }

  private var index = 0
  private def createCombinedName() = synchronized {
    index += 1
    s"/combined_script$index.js"
  }

  private def createToken(scripts: Seq[tag.Script]) = createList(scripts).mkString(";")

  private def createList(scripts: Seq[tag.Script]) = scripts.map(s => s.src()).toList

  def isMatch(url: URL) = scriptsByCombinedName.contains(url.path)

  def create(request: HttpRequest) = this

  def apply(webapp: NettyWebapp, context: ChannelHandlerContext, event: MessageEvent) = {
    val request = event.getMessage.asInstanceOf[HttpRequest]
    val url: URL = request
    scriptsByCombinedName.get(url.path) match {
      case Some(list) => {
        streamString(combinedString(url, list), context, request, "text/javascript")
      }
      case None => throw new RuntimeException(s"Unable to find combined script for: $url")
    }
  }

  private def combinedString(scriptURL: URL, list: List[String]) = {      // Should we cache in a file?
    list.map(uri => loadJavaScript(scriptURL, uri)).mkString("\r\n")
  }

  private def loadJavaScript(scriptURL: URL, uri: String) = synchronized {
    scriptCache.get(uri) match {
      case Some(js) => js
      case None => {
        val url = if (uri.toLowerCase.startsWith("http")) {
          URL.parse(uri).getOrElse(throw new NullPointerException(s"Unable to download: $uri"))
        } else {
          scriptURL.copy(path = uri)
        }
        val javaURL = new java.net.URL(url.toString())
        info(s"Attempting load JavaScript from URL: $url.")
        val js = IO.copy(javaURL)
        scriptCache += uri -> js
        js
        info(s"$url loaded successfully.")
      }
    }
  }
}