package org.hyperscala

package object html {
  /**
   * Support conversion from String to Text instance
   */
  implicit def s2Text(s: String) = new Text(s)
}