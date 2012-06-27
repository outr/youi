package org.hyperscala

import tags.{Head, Body}
import org.sgine.scene.AbstractMutableContainer

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
class WebPage(val filename: String) extends AbstractMutableContainer[WebContent] with WebContent {
  val head = new Head()
  val body = new Body()

  addChild(head)
  addChild(body)

  def xml = <html>{head.xml}{body.xml}</html>

  override def beforeRender() {
    super.beforeRender()

    head.beforeRender()
    body.beforeRender()
  }

  override def afterRender() {
    super.afterRender()

    head.afterRender()
    body.afterRender()
  }
}