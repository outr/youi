package org.hyperscala.ui.module

import org.hyperscala.event.BrowserEvent
import org.hyperscala.html._
import org.hyperscala.javascript.JavaScriptContent
import org.hyperscala.module.{Interface, Module}
import org.hyperscala.realtime.{RealtimePage, Realtime}
import org.hyperscala.web.{Website, Webpage}
import org.hyperscala.javascript.dsl._
import org.powerscala.event.Intercept
import org.powerscala.hierarchy.event.Descendants
import org.powerscala.{Priority, Unique, Version}
import org.powerscala.json.TypedSupport

/**
 * ScriptLoader allows for dynamically loading JavaScript files after the page has loaded.
 *
 * @author Matt Hicks <matt@outr.com>
 */
object ScriptLoader extends Module {
  TypedSupport.register("scriptLoaded", classOf[ScriptLoaded])

  override val name = "ScriptLoader"
  override val version = Version(1)

  override def dependencies = List(Realtime)

  override def init(website: Website) = {
    website.register("/js/hyperscala-scriptloader.js", "hyperscala-scriptloader.js")
  }

  override def load(webpage: Webpage) = {
    webpage.head.contents += new tag.Script(src = "/js/hyperscala-scriptloader.js")
  }

  def load(webpage: Webpage, url: String, cache: Boolean = true) = {
    webpage.eval(s"ScriptLoader.load('$url', $cache);")
  }

  def loadMultiple(webpage: Webpage, urls: List[String], cache: Boolean = true) = {
    webpage.eval(s"ScriptLoader.loadMultiple(${urls.map(JavaScriptContent.toJS).mkString("[", ", ", "]")}, $cache);")
  }

  def whenFinished(webpage: Webpage)(callback: => Unit) = {
    val id = Unique()
    webpage.body.eventReceived.onceConditional(Intercept.Continue) {
      case evt: ScriptLoaded if evt.callId == id => {
        callback
        Some(Intercept.Stop)
      }
      case _ => None
    }
    webpage.eval(s"ScriptLoader.onFinish('$id');")
  }
}

case class ScriptLoaded(tag: HTMLTag, callId: String) extends BrowserEvent