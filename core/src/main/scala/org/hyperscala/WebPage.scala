package org.hyperscala

import tags.{Head, Body}
import org.powerscala.hierarchy.{ContainerView, AbstractMutableContainer}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class WebPage(val filename: String) extends AbstractMutableContainer[WebContent] with WebContent {
  private var tab = 1

  val webContent = new ContainerView[WebContent](this)

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

  def nextTab = {
    val next = tab
    tab += 1
    next
  }
}