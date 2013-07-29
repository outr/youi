package org.hyperscala.hello

import org.hyperscala.web.site.{DynamicWebsite, BasicWebsite}

/**
 * HelloSite is the starting point of our application. It is responsible for all resource management and web pages.
 *
 * We are extending both BasicWebsite and DynamicWebsite to simplify the process of getting started.
 *
 * @author Matt Hicks <matt@outr.com>
 */
object HelloSite extends BasicWebsite with DynamicWebsite {
  def hello = new HelloPage
}