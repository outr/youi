package org.hyperscala.web.useragent

import org.powerscala.Version

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class OperatingSystem(name: String, family: OperatingSystemFamily, version: Version) {
   override def toString = s"name: $name, family: $family, version: $version"
 }
