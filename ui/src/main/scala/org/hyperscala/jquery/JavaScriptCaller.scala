package org.hyperscala.jquery

import org.hyperscala.web.site.Webpage
import org.hyperscala.javascript.JavaScriptString

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait JavaScriptCaller {
  protected def injectScript(script: String) = Webpage().head.injectScript(new JavaScriptString(script), temporal = true)

  protected def value2String(v: Any) = v match {
    case null => "null"
    case s: String => "'%s'".format(s)
    case _ => v.toString
  }
}
