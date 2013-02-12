package org.hyperscala.web

import org.hyperscala.html._
import org.hyperscala.css.attributes.FontSize
import org.powerscala.Color

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class VisualException(t: Throwable) extends tag.Div {
  contents += VisualException.toHTML(t, isCause = false)
}

object VisualException {
  def toHTML(t: Throwable, isCause: Boolean = false): tag.Div = {
    new tag.Div {
      style.fontFamily = "Arial, Sans-Serif"
      style.fontSize = FontSize.Small
      val pre = if (isCause) {
        "Caused by: "
      } else {
        ""
      }
      contents += new tag.B(content = "%s%s: %s".format(pre, t.getClass.getName, t.getMessage)) {
        style.color = Color.immutable("#dd1111")
      }
      contents += new tag.Ul {
        t.getStackTrace.foreach {
          case element => {
            contents += new tag.Div(content = "at %s.%s(%s:%s)".format(element.getClassName, element.getMethodName, element.getFileName, element.getLineNumber))
          }
        }
      }
      t.getCause match {
        case null => // No additional cause
        case cause => contents += toHTML(cause, isCause = true)
      }
    }
  }
}