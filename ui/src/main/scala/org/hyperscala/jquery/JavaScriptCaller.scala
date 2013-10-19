package org.hyperscala.jquery

import org.hyperscala.web.Webpage
import org.hyperscala.javascript.{JavaScriptContent, JavaScriptString}

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait JavaScriptCaller {
  protected def injectScript(script: String) = Webpage().head.injectScript(new JavaScriptString(script), temporal = true)

  protected def value2String(v: Any) = JavaScriptContent.toJS(v)
}