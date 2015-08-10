package org.hyperscala.ui

import org.powerscala.Version

import org.hyperscala.html.tag
import org.hyperscala.module.Module
import org.hyperscala.web.{Webpage, Website}
import org.hyperscala.javascript.dsl._

object VideoJSThumbnails extends Module {
  override def name = "videojs-thumbnails"

  override def version = Version()

  override def dependencies = List(VideoJS)

  override def init(website: Website) = {
    website.register("/js/videojs.thumbnails.js", "videojs.thumbnails.js")
    website.register("/css/videojs.thumbnails.css", "videojs.thumbnails.css")
  }

  override def load(webpage: Webpage) = {
    webpage.head.contents += new tag.Script(mimeType = "text/javascript", src = "/js/videojs.thumbnails.js")
    webpage.head.contents += new tag.Link(rel = "stylesheet", href = "/css/videojs.thumbnails.css", mimeType = "text/css")
  }

  case class Thumbnail(seconds: Int, sourceUrl: String, width: Option[Int]) {
    def toJson: String = {
      val line = s"$seconds: { src: '$sourceUrl'"
      if (width.isDefined) s"$line, width: '${width.get}' }" else s"$line }"
    }
  }

  def setThumbnails(webpage: Webpage, videoJS: VideoJS, thumbnails: Seq[Thumbnail]) = {
    val thumbnailsJson = "{ " + thumbnails.map(_.toJson).mkString(", ") + " }"
    videoJS.callJson(webpage, "thumbnails", thumbnailsJson)
  }
}
