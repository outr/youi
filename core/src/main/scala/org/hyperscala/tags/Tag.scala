package org.hyperscala.tags

import org.hyperscala.{WebAttribute, BodyContent, Container}
import org.hyperscala.style.StyleSheet
import org.hyperscala.javascript.events.EventSupport
import org.hyperscala.value.Property

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
trait Tag extends Container with BodyContent with EventSupport {
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
  val tabIndex = WebAttribute[String]("tabindex")
  val title = WebAttribute[String]("title")

  /**
   * Updates attribute with value if it's not null.
   */
  protected def up[T](attribute: Property[T], value: T) = {
    if (value != null) {
      attribute := value
    }
  }
}
