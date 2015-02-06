package org.hyperscala.html.tag

import org.hyperscala._
import css.StyleSheet
import org.hyperscala.html._
import org.hyperscala.html.attributes._
import org.hyperscala.html.constraints._
import java.net.URLDecoder
import com.outr.net.Method

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
class Form extends Container[BodyChild] with BodyChild with HTMLTag {
  lazy val xmlLabel = "form"

  def this(name: String = null,
           accessKey: java.lang.Character = null,
           clazz: Seq[String] = null,
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
           acceptCharset: String = null,
           action: String = null,
           autoComplete: AutoComplete = null,
           encType: String = null,
           method: Method = null,
           noValidate: String = null,
           target: Target = null,
           content: BodyChild = null) = {
    this()
    init(name, accessKey, clazz, contentEditable, contextMenu, dir, draggable, dropZone, hidden, id, lang, role, spellCheck, style, tabIndex, titleText)
    up(this.acceptCharset, acceptCharset)
    up(this.action, action)
    up(this.autoComplete, autoComplete)
    up(this.encType, encType)
    up(this.method, method)
    up(this.noValidate, noValidate)
    up(this.target, target)
    if (content != null) contents += content
  }

  lazy val acceptCharset = PropertyAttribute[String]("acceptcharset", null)
  lazy val action = PropertyAttribute[String]("action", null)
  lazy val autoComplete = PropertyAttribute[AutoComplete]("autocomplete", null)
  lazy val encType = PropertyAttribute[String]("enctype", null)
  lazy val method = PropertyAttribute[Method]("method", Method.Get, inclusion = InclusionMode.Always)
  lazy val noValidate = PropertyAttribute[String]("novalidate", null)
  lazy val target = PropertyAttribute[Target]("target", null)

  changeEvent.on {
    case evt => if (evt.value != null && evt.value.nonEmpty) {
      evt.value.split('&').foreach {
        case pair => {
          val split = pair.indexOf('=')
          val key = URLDecoder.decode(pair.substring(0, split), "UTF-8")
          val value = URLDecoder.decode(pair.substring(split + 1), "UTF-8")
          byId[FormField](key) match {
            case Some(f) => f.changeTo(value)
            case None => warn(s"Unable to find $key by id with value of: $value")
          }
        }
      }
    }
  }
}