package org.hyperscala.svg

import attributes.ViewBox
import org.hyperscala.{InclusionMode, PropertyAttribute, Container}
import org.hyperscala.html.constraints.BodyChild
import org.hyperscala.html._
import org.hyperscala.css.attributes.Length
import org.hyperscala.html.attributes.{DropZone, Draggable, Direction, ContentEditable}
import org.hyperscala.css.StyleSheet

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class Svg extends Container[SVGTag] with BodyChild with HTMLTag {
  lazy val xmlLabel = "svg"

  def this(name: String = null,
           accessKey: java.lang.Character = null,
           clazz: List[String] = null,
           contentEditable: ContentEditable = null,
           contextMenu: String = null,
           dir: Direction = null,
           draggable: Draggable = null,
           dropZone: DropZone = null,
           hidden: java.lang.Boolean = null,
           id: String = null,
           lang: String = null,
           role: String = null,
           spellCheck: java.lang.Boolean = null,
           style: StyleSheet = null,
           tabIndex: java.lang.Integer = null,
           titleText: String = null,
           x: java.lang.Double = null,
           y: java.lang.Double = null,
           width: Length = null,
           height: Length = null,
           viewBox: ViewBox = null,
           version: String = null,
           baseProfile: String = null,
           preserveAspectRatio: String = null,
           contentScriptType: String = null,
           contentStyleType: String = null,
           zoomAndPan: String = null,
           xmlns: String = null,
           content: SVGTag = null) = {
    this()
    init(name, accessKey, clazz, contentEditable, contextMenu, dir, draggable, dropZone, hidden, id, lang, role, spellCheck, style, tabIndex, titleText)
    up(this.x, x)
    up(this.y, y)
    up(this.width, width)
    up(this.height, height)
    up(this.viewBox, viewBox)
    up(this.version, version)
    up(this.baseProfile, baseProfile)
    up(this.preserveAspectRatio, preserveAspectRatio)
    up(this.contentScriptType, contentScriptType)
    up(this.contentStyleType, contentStyleType)
    up(this.zoomAndPan, zoomAndPan)
    up(this.xmlns, xmlns)
    if (content != null) contents += content
  }

  lazy val x = PropertyAttribute[Double]("x", 0.0)
  lazy val y = PropertyAttribute[Double]("y", 0.0)
  lazy val width = PropertyAttribute[Length]("width", 100.pct)
  lazy val height = PropertyAttribute[Length]("height", null)
  lazy val viewBox = PropertyAttribute[ViewBox]("viewBox", null)
  lazy val version = PropertyAttribute[String]("version", "1.1", inclusion = InclusionMode.Always)
  lazy val baseProfile = PropertyAttribute[String]("baseProfile", null)
  lazy val preserveAspectRatio = PropertyAttribute[String]("preserveAspectRatio", null)
  lazy val contentScriptType = PropertyAttribute[String]("contentScriptType", null)
  lazy val contentStyleType = PropertyAttribute[String]("contentStyleType", null)
  lazy val zoomAndPan = PropertyAttribute[String]("zoomAndPan", null)
  lazy val xmlns = PropertyAttribute[String]("xmlns", "http://www.w3.org/2000/svg", inclusion = InclusionMode.Always)
}
