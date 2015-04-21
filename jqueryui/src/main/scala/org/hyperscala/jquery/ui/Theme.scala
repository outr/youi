package org.hyperscala.jquery.ui

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class Theme(directory: String, cssPath: String = null)

object Theme {
  val BlackTie = new Theme("black-tie")
  val Blitzer = new Theme("blitzer")
  val Cupertino = new Theme("cupertino")
  val DarkHive = new Theme("dark-hive")
  val DotLuv = new Theme("dot-luv")
  val Eggplant = new Theme("eggplant")
  val ExciteBike = new Theme("excite-bike")
  val Flick = new Theme("flick")
  val HotSneaks = new Theme("hot-sneaks")
  val Humanity = new Theme("humanity")
  val LeFrog = new Theme("le-frog")
  val MintChoc = new Theme("mint-choc")
  val Overcast = new Theme("overcast")
  val PepperGrinder = new Theme("pepper-grinder")
  val Redmond = new Theme("redmond")
  val Smoothness = new Theme("smoothness")
  val SouthStreet = new Theme("south-street")
  val Start = new Theme("start")
  val Sunny = new Theme("sunny")
  val SwankyPurse = new Theme("swanky-purse")
  val Trontastic = new Theme("tronstastic")
  val UIDarkness = new Theme("ui-darkness")
  val UILightness = new Theme("ui-lightness")
  val Vader = new Theme("vader")
  def Custom(cssPath: String) = new Theme(null, cssPath)
}