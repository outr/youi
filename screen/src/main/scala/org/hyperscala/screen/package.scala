package org.hyperscala

import com.outr.net.URL

import scala.language.implicitConversions

/**
 * @author Matt Hicks <matt@outr.com>
 */
package object screen {
  implicit def uri2Matcher(uri: String): URL => Boolean = ScreenKeeper.pathMatcher(uri)
}
