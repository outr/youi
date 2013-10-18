package org.hyperscala.web.useragent

import org.powerscala.Version

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class Browser(family: BrowserFamily, version: Version) {
   override def toString = s"name: $family, version: $version"
 }
