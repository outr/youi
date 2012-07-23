package org.hyperscala.tags

import org.hyperscala.{WebContent, WebAttribute, BodyContent, Container}
import org.hyperscala.style.StyleSheet
import org.hyperscala.javascript.events.EventSupport

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Tag extends Container[WebContent] with BodyContent with EventSupport {
  val accessKey = WebAttribute[String]("accesskey")
  val clazz = WebAttribute[String]("class")
  val contextEditable = WebAttribute[String]("contexteditable")
  val contextMenu = WebAttribute[String]("contextmenu")
  val dir = WebAttribute[String]("dir")
  val draggable = WebAttribute[String]("draggable")
  val dropZone = WebAttribute[String]("dropzone")
  val hidden = WebAttribute[String]("hidden")
  val id = WebAttribute[String]("id")
  val lang = WebAttribute[String]("lang")
  val spellCheck = WebAttribute[String]("spellcheck")
  val style = new StyleSheet("style")
  val tabIndex = WebAttribute[Int]("tabindex")
  val title = WebAttribute[String]("title")
}