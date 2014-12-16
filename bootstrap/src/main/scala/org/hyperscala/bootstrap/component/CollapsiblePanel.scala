package org.hyperscala.bootstrap.component

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait CollapsiblePanel extends Panel {
  def collapsed: Boolean

  outer.clazz += "panel-collapse"
  outer.clazz += "collapse"

  if (collapsed) {
    heading.clazz += "collapsed"
  }
  heading.data("toggle", "collapse")
  heading.data("target", s"#${outer.identity}")
}
