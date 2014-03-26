package org.hyperscala.jquery

import org.hyperscala.web.Webpage
import org.hyperscala.javascript.{JavaScriptContent, JavaScriptString}
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait JavaScriptCaller {
  protected def injectScript[S <: Session](webpage: Webpage[S], script: String) = webpage.head.injectScript(new JavaScriptString(script), temporal = true)

  protected def value2String(v: Any) = JavaScriptContent.toJS(v)
}