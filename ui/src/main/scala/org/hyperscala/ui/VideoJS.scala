package org.hyperscala.ui

import com.outr.net.http.session.Session
import org.hyperscala.PropertyAttribute
import org.hyperscala.html._
import org.hyperscala.javascript.JavaScriptContent
import org.hyperscala.module.{Interface, Module}
import org.hyperscala.realtime.Realtime
import org.hyperscala.selector.Selector
import org.hyperscala.web.{Website, Webpage}
import org.powerscala.hierarchy.event.Descendants
import org.powerscala.{Priority, Version}
import org.powerscala.event.Listenable
import org.powerscala.property.Property
import org.hyperscala.web._
import org.powerscala.property.event.PropertyChangeEvent

/**
 * Light-weight wrapper around http://www.videojs.com/
 *
 * @author Matt Hicks <matt@outr.com>
 */
class VideoJS extends tag.Video {
  this.require(VideoJS)

  identity                        // Make sure we have a unique id
  clazz += "video-js"

  val skin = new ClassProperty[String](this, default = VideoJS.skin()) {
    override def toClass(c: String) = Some(s"vjs-$c-skin")
  }
  val playCentered = new ClassProperty[Boolean](this, default = false) {
    override def toClass(c: Boolean) = if (c) Some("vjs-big-play-centered") else None
  }

  connected[Webpage[_ <: Session]] {
    case webpage => {
      val js =
        s"""
          |videojs('${identity}', {}, function() {
          | // TODO: notify the server the video is initialized
          |});
        """.stripMargin
      Realtime.sendJavaScript(webpage, js, selector = Some(Selector.id(this)), onlyRealtime = false)

      listen[PropertyChangeEvent[_], Unit, Unit]("change", Priority.Normal, Descendants) {
        case evt => evt.property match {
          case p: PropertyAttribute[_] if p.parent == this => {
            call(p.name, p.value)
          }
          case _ => // No match
        }
      }
    }
  }

  private def call(method: String, args: Any*) = {
    val argsJS = args.map(a => JavaScriptContent.toJS(a)).mkString(", ")
    Realtime.sendJavaScript(this.webpage, s"videojs('$identity').$method($argsJS);", onlyRealtime = false, selector = Some(Selector.id(this)))
  }

  def currentTime(seconds: Double) = call("currentTime", seconds)

  def dimensions(width: Int, height: Int) = call("dimensions", width, height)

  def dispose() = call("dispose")

  def exitFullscreen() = call("exitFullscreen")

  def hide() = call("hide")

  def language(locale: String) = call("language", locale)

  def muted(b: Boolean) = call("muted", b)

  def pause() = call("pause")

  def play() = call("play")

  def requestFullscreen() = call("requestFullscreen")

  def show() = call("show")

  def volume(percent: Double) = call("volume", percent)

  def source(url: String, mimeType: String) = {
    contents += new tag.Source(src = url, sourceType = mimeType)
  }
}

object VideoJS extends Module {
  override val name = "Video.js"
  override val version = Version(4, 8)

  val skin = Property[String](default = Some("default"))

  override def init[S <: Session](website: Website[S]) = {}

  override def load[S <: Session](webpage: Webpage[S]) = {
    val v = s"${version.major}.${version.minor}"
    webpage.head.contents += new tag.Link(href = s"//vjs.zencdn.net/$v/video-js.css")
    webpage.head.contents += new tag.Script(src = s"//vjs.zencdn.net/$v/video.js")
  }

  override def dependencies = List(Realtime)
}

// TODO: extract to higher level
abstract class ClassProperty[T](t: HTMLTag, default: T)(implicit parent: Listenable, manifest: Manifest[T]) extends Property[T](default = Some(default)) {
  toClass(default) match {
    case Some(c) => t.clazz += c
    case None => // Nothing to set
  }

  change.on {
    case evt => {
      toClass(evt.oldValue) match {
        case Some(c) => t.clazz -= c
        case None => // Nothing to remove
      }
      toClass(evt.newValue) match {
        case Some(c) => t.clazz += c
        case None => // Nothing to add
      }
    }
  }

  def toClass(c: T): Option[String]
}