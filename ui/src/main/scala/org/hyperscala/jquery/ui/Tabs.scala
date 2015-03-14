package org.hyperscala.jquery.ui

import com.outr.net.http.session.Session
import org.hyperscala.html._
import org.hyperscala.javascript.dsl._
import org.hyperscala.realtime.Realtime
import org.hyperscala.selector.Selector
import org.hyperscala.web._
import org.powerscala.Unique

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Tabs extends tag.Div {
  this.require(Realtime)
  this.require(jQueryUI)

  identity

  val navigation = new tag.Ul(id = Unique())
  contents += navigation

  connected[Webpage] {
    case webpage => webpage.eval(s"$$('#$identity').tabs();", Some(Selector.id(identity).toCondition))
  }

  def addTab[T <: tag.Div](label: String)(f: => T) = {
    val content: T = f
    navigation.contents += new tag.Li {
      contents += new tag.A(href = "#%s".format(content.identity), content = label)
    }
    contents += content
    content
  }
}

object Tabs {
  def apply(t: HTMLTag) = jQueryUI.tabs(t)
}