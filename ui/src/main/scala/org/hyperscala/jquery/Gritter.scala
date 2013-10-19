package org.hyperscala.jquery

import org.powerscala.Version
import org.hyperscala.module.{Module, InterfaceWithDefault}
import org.hyperscala.web.{Webpage, Website}
import org.hyperscala.html.tag
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.realtime.Realtime

/**
 * @author Matt Hicks <matt@outr.com>
 */
object Gritter extends Module with JavaScriptCaller {
  def name = "gritter"

  def version = Version(1, 7, 4)

  override def dependencies = List(InterfaceWithDefault(jQuery, jQuery.Latest), Realtime)

  def init() = {
    Website().addClassPath("/gritter/", "gritter/")
  }

  def load() = {
    val page = Webpage()
    page.head.contents += new tag.Link(href = "/gritter/css/jquery.gritter.css", rel = "stylesheet")
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "/gritter/js/jquery.gritter.min.js")
  }

  def add(title: String,
          text: String,
          image: String = null,
          sticky: Boolean = false,
          time: Int = 8000,
          className: String = null) = {
    Webpage().require(this)
    Webpage().body.contents += new tag.Script(content = new JavaScriptString(
      """
        |$.gritter.add({
        |   'title': %s,
        |   'text': %s,
        |   'image': %s,
        |   'sticky': %s,
        |   'time': %s,
        |   'class_name': %s
        |});
      """.stripMargin.format(value2String(title),       // Title
                             value2String(text),        // Text
                             value2String(image),       // Image
                             sticky,                    // Sticky
                             time,                      // Time
                             value2String(className)    // Class Name
          )
    ))
  }

}