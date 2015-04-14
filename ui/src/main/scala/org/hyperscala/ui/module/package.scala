package org.hyperscala.ui

import org.hyperscala.html.HTMLTag

import scala.language.implicitConversions

/**
 * @author Matt Hicks <matt@outr.com>
 */
package object module {
  implicit def tag2Compliance(t: HTMLTag) = new Compliance(t)

  /* Information used for building a query string.  If a key exists then a key should be
  * be created in the query string, even in value is empty.  If a key does not exist, then
  * neither the key nor the value will exist in the resulting query string.
  */
  type LinkData = Map[String, String]
}