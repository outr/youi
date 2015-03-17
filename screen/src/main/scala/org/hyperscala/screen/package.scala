package org.hyperscala

import com.outr.net.URL

import scala.language.implicitConversions
import scala.util.matching.Regex

/**
 * @author Matt Hicks <matt@outr.com>
 */
package object screen {
  implicit def uri2Matcher(uri: String): URL => Boolean = ScreenHandler.pathMatcher(uri)
  implicit def uris2Matcher(uris: List[String]): URL => Boolean = ScreenHandler.pathsMatcher(uris)
  implicit def regex2Matcher(r: Regex): URL => Boolean = ScreenHandler.regexMatcher(r)
}
