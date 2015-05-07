package org.hyperscala.jquery

import com.outr.net.http.session.Session
import org.hyperscala.html.tag
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.module.Module
import org.hyperscala.realtime.Realtime
import org.hyperscala.web.{Webpage, Website}
import org.powerscala.Version

/**
 * @author Matt Hicks <matt@outr.com>
 */
object Gritter extends Module with JavaScriptCaller {
  def name = "gritter"

  def version = Version(1, 7, 4)

  override def dependencies = List(jQuery, Realtime)

  override def init(website: Website) = {
    website.addClassPath("/gritter/", "gritter/")
  }

  override def load(webpage: Webpage) = {
    webpage.head.contents += new tag.Link(href = "/gritter/css/jquery.gritter.css", rel = "stylesheet")
    webpage.head.contents += new tag.Script(mimeType = "text/javascript", src = "/gritter/js/jquery.gritter.min.js")
  }

  def add(webpage: Webpage,
                        title: String,
                        text: String,
                        image: String = null,
                        sticky: Boolean = false,
                        time: Int = 8000,
                        className: String = null) = {
    webpage.require(this)
    webpage.eval(JavaScriptString(
      s"""$$(function() {
        |  $$.gritter.add({
        |    'title': ${value2String(title)},
        |    'text': ${value2String(text)},
        |    'image': ${value2String(image)},
        |    'sticky': $sticky,
        |    'time': $time,
        |    'class_name': ${value2String(className)}
        |  });
        |});
      """
    ))
  }

}