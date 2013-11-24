package org.hyperscala.ui

import org.hyperscala.html.HTMLTag

import scala.language.implicitConversions

/**
 * @author Matt Hicks <matt@outr.com>
 */
package object module {
  implicit def tag2Compliance(t: HTMLTag) = new Compliance(t)
}