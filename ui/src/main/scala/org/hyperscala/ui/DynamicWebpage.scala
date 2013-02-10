package org.hyperscala.ui

import dynamic.DynamicTag
import org.hyperscala.web.site.Webpage
import org.hyperscala.html._

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
abstract class DynamicWebpage extends Webpage {
  def dynamicTag: DynamicTag[tag.HTML]

  private lazy val dynamicHTML = dynamicTag.create()
  override def html = dynamicHTML
}