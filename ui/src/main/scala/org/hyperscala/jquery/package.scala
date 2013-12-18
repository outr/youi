package org.hyperscala

import org.hyperscala.jquery.dsl.jQuerySelector

import scala.language.implicitConversions

/**
 * @author Matt Hicks <matt@outr.com>
 */
package object jquery {
  implicit def jqs2zoomooz(s: jQuerySelector) = new Zoomooz(s)
}
