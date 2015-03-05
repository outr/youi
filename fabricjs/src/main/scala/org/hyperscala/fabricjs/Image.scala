package org.hyperscala.fabricjs

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Image extends Object("Image") {
  lazy val alignX = prop("alignX", "none")
  lazy val alignY = prop("alignY", "none")
  lazy val crossOrigin = prop("crossOrigin", "")
  lazy val meetOrSlice = prop("meetOrSlice", "meet")
}
