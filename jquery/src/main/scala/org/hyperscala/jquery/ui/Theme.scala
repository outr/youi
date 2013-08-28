package org.hyperscala.jquery.ui

import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Theme private(val cssPath: String = null) extends EnumEntry {
  def directory = label.replace(" ", "-").toLowerCase
}

object Theme extends Enumerated[Theme] {
  val BlackTie = new Theme
  val Blitzer = new Theme
  val Cupertino = new Theme
  val DarkHive = new Theme
  val DotLuv = new Theme
  val Eggplant = new Theme
  val ExciteBike = new Theme
  val Flick = new Theme
  val HotSneaks = new Theme
  val Humanity = new Theme
  val LeFrog = new Theme
  val MintChoc = new Theme
  val Overcast = new Theme
  val PepperGrinder = new Theme
  val Redmond = new Theme
  val Smoothness = new Theme
  val SouthStreet = new Theme
  val Start = new Theme
  val Sunny = new Theme
  val SwankyPurse = new Theme
  val Trontastic = new Theme
  val UIDarkness = new Theme
  val UILightness = new Theme
  val Vader = new Theme
  def Custom(cssPath: String) = new Theme(cssPath)
}