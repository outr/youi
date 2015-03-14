package org.hyperscala.ui.dynamic

import org.hyperscala.web.{Website, Webpage}
import org.hyperscala.html._
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
abstract class DynamicWebpage extends Webpage {
  def dynamicTag: DynamicTag[tag.HTML]

  private lazy val dynamicHTML = dynamicTag.create()
  override def html = dynamicHTML
}