package org.hyperscala.site

import org.hyperscala.ui.dynamic.DynamicTag

/**
 * @author Matt Hicks <matt@outr.com>
 */
object HyperscalaAbout extends HyperscalaPage {
  main.contents += DynamicTag.url("about", getClass.getClassLoader.getResource("html/about.html")).create()

  override def sourceURL = "https://github.com/darkfrog26/hyperscala/blob/master/site/src/main/scala/org/hyperscala/site/HyperscalaAbout.scala"
}