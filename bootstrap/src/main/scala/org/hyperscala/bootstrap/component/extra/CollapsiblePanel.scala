package org.hyperscala.bootstrap.component.extra

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait CollapsiblePanel extends PanelFactory {
  def collapsed: Boolean

  outer.clazz += "panel-collapse"
  outer.clazz += "collapse"

  if (collapsed) {
    heading.clazz += "collapsed"
  } else {
    outer.clazz += "in"
  }
  heading.data("toggle", "collapse")
  heading.data("target", s"#${outer.identity}")

  def collapse() = {
    heading.clazz += "collapsed"
    outer.clazz -= "in"
  }

  def expand() = {
    heading.clazz -=" collapsed"
    outer.clazz += "in"
  }
}
