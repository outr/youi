package org.hyperscala.jquery

import com.outr.net.http.session.Session
import org.hyperscala.event.SubmitEvent
import org.hyperscala.html.{HTMLTag, tag}
import org.hyperscala.javascript.JavaScriptContent
import org.hyperscala.javascript.dsl.{JSON, JSFunction1, MultiStatement, Statement}
import org.hyperscala.jquery.dsl.jQuerySelector
import org.hyperscala.module._
import org.hyperscala.selector.Selector
import org.hyperscala.web.useragent.{BrowserFamily, UserAgent}
import org.hyperscala.web.{Webpage, Website}
import org.powerscala.Version

/**
 * @author Matt Hicks <matt@outr.com>
 */
object jQuery extends Module {
  val name = "jquery"
  def version = version2

  val version1 = Version(1, 11, 0)
  val version2 = Version(2, 1, 1)

  override def init[S <: Session](website: Website[S]) = {}

  override def load[S <: Session](webpage: Webpage[S]) = {
    val userAgent = UserAgent(webpage)
    val v = if (userAgent.browser.family == BrowserFamily.IE && userAgent.browser.version.major < 9) {
      version1.general
    } else {
      version2.general
    }
    webpage.head.contents += new tag.Script(src = s"//code.jquery.com/jquery-$v.min.js")
  }

  def apply(s: String) = new jQuerySelector(Selector(s))

  def apply(t: HTMLTag) = new jQuerySelector(Selector.id(t))

  def apply(selector: Selector) = new jQuerySelector(selector)

  def post(url: String, data: Statement[String], success: JSFunction1[String, Unit]) = {
    MultiStatement[Unit](sideEffects = true, "jQuery.post(", JavaScriptContent.toJS(url), ", ", data, ", ", success, ")")
  }

  def parseJSON(statement: Statement[String]) = {
    MultiStatement[JSON](sideEffects = true, "jQuery.parseJSON(", statement, ")")
  }
}