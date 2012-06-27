package org.hyperscala.style

import org.hyperscala.value.Property
import org.sgine.scene.AbstractMutableContainer
import org.sgine.hierarchy.Element
import org.hyperscala.{BodyContent, WebAttribute}
import org.sgine.Color

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
class StyleSheet(val name: String)(implicit val bodyContent: BodyContent) extends AbstractMutableContainer[StyleProperty[_]] with WebAttribute {
  bodyContent.attributes += (name -> this)
  implicit def thisStyleSheet: StyleSheet = this

  object alignment {
    val adjust = p[String]("alignment-adjust")
    val baseline = p[String]("alignment-baseline")
  }

  object animation extends StyleProperty[String]("animation") {
    val delay = p[String]("animation-delay")
    val direction = p[String]("animation-direction")
    val duration = p[String]("animation-duration")

    object iteration {
      val count = p[String]("animation-iteration-count")
    }

    val name = p[String]("animation-name")

    object play {
      val state = p[String]("animation-play-state")
    }

    object timing {
      val function = p[String]("animation-timing-function")
    }

  }

  val appearance = p[String]("appearance")

  object backface {
    val visibility = p[String]("backface-visibility")
  }

  object background extends StyleProperty[String]("background") {
    val attachment = p[String]("background-attachment")
    val clip = p[String]("background-clip")
    val color = p[Color]("background-color")
    val image = p[Resource]("background-image")
    val origin = p[String]("background-origin")
    val position = p[String]("background-position")
    val repeat = p[String]("background-repeat")
    val size = p[String]("background-size")
  }

  object baseline {
    val shift = p[String]("baseline-shift")
  }

  object bookmark {
    val label = p[String]("bookmark-label")
    val level = p[String]("bookmark-level")
    val target = p[String]("bookmark-target")
  }

  object border extends StyleProperty[String]("border") {

    object bottom extends StyleProperty[String]("border-bottom") {
      val color = p[Color]("border-bottom-color")

      object left {
        val radius = p[String]("border-bottom-left-radius")
      }

      object right {
        val radius = p[String]("border-bottom-right-radius")
      }

      val style = p[String]("border-bottom-style")
      val width = p[String]("border-bottom-width")
    }

    val collapse = p[String]("border-collapse")
    val color = p[Color]("border-color")

    object image extends StyleProperty[String]("border-image") {
      val outset = p[String]("border-image-outset")
      val repeat = p[String]("border-image-repeat")
      val slice = p[String]("border-image-slice")
      val source = p[String]("border-image-source")
      val width = p[String]("border-image-width")
    }

    object left extends StyleProperty[String]("border-left") {
      val color = p[Color]("border-left-color")
      val style = p[String]("border-left-style")
      val width = p[String]("border-left-width")
    }

    val radius = p[String]("border-radius")

    object right extends StyleProperty[String]("border-right") {
      val color = p[Color]("border-right-color")
      val style = p[String]("border-right-style")
      val width = p[String]("border-right-width")
    }

    val spacing = p[String]("border-spacing")
    val style = p[String]("border-style")

    object top extends StyleProperty[String]("border-top") {
      val color = p[Color]("border-top-color")

      object left {
        val radius = p[String]("border-top-left-radius")
      }

      object right {
        val radius = p[String]("border-top-right-radius")
      }

      val style = p[String]("border-top-style")
      val width = p[String]("border-top-width")
    }

    val width = p[String]("border-width")
  }

  val bottom = p[String]("bottom")

  object box {
    val align = p[String]("box-align")

    object decoration {
      val break = p[String]("box-decoration-break")
    }

    val direction = p[String]("box-direction")

    object flex extends StyleProperty[String]("box-flex") {
      val group = p[String]("box-flex-group")
    }

    val lines = p[String]("box-lines")

    object ordinal {
      val group = p[String]("box-ordinal-group")
    }

    val orient = p[String]("box-orient")
    val pack = p[String]("box-pack")
    val shadow = p[String]("box-shadow")
    val sizing = p[String]("box-sizing")
  }

  object caption {
    val side = p[String]("caption-side")
  }

  val clear = p[String]("clear")
  val clip = p[String]("clip")

  object color extends StyleProperty[Color]("color") {
    val profile = p[String]("color-profile")
  }

  object column {
    val count = p[String]("column-count")
    val fill = p[String]("column-fill")
    val gap = p[String]("column-gap")

    object rule extends StyleProperty[String]("column-rule") {
      val color = p[Color]("column-rule-color")
      val style = p[String]("column-rule-style")
      val width = p[String]("column-rule-width")
    }

    val span = p[String]("column-span")
    val width = p[String]("column-width")
  }

  val columns = p[String]("columns")
  val content = p[String]("content")

  object counter {
    val increment = p[String]("counter-increment")
    val reset = p[String]("counter-reset")
  }

  val crop = p[String]("crop")
  val cursor = p[String]("cursor")
  val direction = p[String]("direction")
  val display = p[Display]("display")

  object dominant {
    val baseline = p[String]("dominant-baseline")
  }

  object drop {

    object initial {

      object after {
        val adjust = p[String]("drop-initial-after-adjust")
        val align = p[String]("drop-initial-after-align")
      }

      object before {
        val adjust = p[String]("drop-initial-before-adjust")
        val align = p[String]("drop-initial-before-align")
      }

      val size = p[String]("drop-initial-size")
      val value = p[String]("drop-initial-value")
    }

  }

  object empty {
    val cells = p[String]("empty-cells")
  }

  object fit extends StyleProperty[String]("fit") {
    val position = p[String]("fit-position")
  }

  object float extends StyleProperty[Float]("float") {
    val offset = p[String]("float-offset")
  }

  object font extends StyleProperty[String]("font") {
    val family = p[String]("font-family")

    object size extends StyleProperty[Length]("font-size") {
      val adjust = p[String]("font-size-adjust")
    }

    val stretch = p[String]("font-stretch")
    val style = p[String]("font-style")
    val variant = p[String]("font-variant")
    val weight = p[String]("font-weight")
    val face = p[String]("@font-face")
  }

  object grid {
    val columns = p[String]("grid-columns")
    val rows = p[String]("grid-rows")
  }

  object hanging {
    val punctuation = p[String]("hanging-punctuation")
  }

  val height = p[Length]("height")

  object hyphenate {
    val after = p[String]("hyphenate-after")
    val before = p[String]("hyphenate-before")
    val character = p[String]("hyphenate-character")
    val lines = p[String]("hyphenate-lines")
    val resource = p[String]("hyphenate-resource")
  }

  val hyphens = p[String]("hyphens")
  val icon = p[String]("icon")

  object image {
    val orientation = p[String]("image-orientation")
    val resolution = p[String]("image-resolution")
  }

  object inline {

    object box {
      val align = p[String]("inline-box-align")
    }

  }

  val left = p[Length]("left")

  object letter {
    val spacing = p[String]("letter-spacing")
  }

  object line {
    val height = p[String]("line-height")

    object stacking extends StyleProperty[String]("line-stacking") {
      val ruby = p[String]("line-stacking-ruby")
      val shift = p[String]("line-stacking-shift")
      val strategy = p[String]("line-stacking-strategy")
    }

  }

  object list {

    object style extends StyleProperty[String]("list-style") {
      val image = p[String]("list-style-image")
      val position = p[String]("list-style-position")
      val styleType = p[String]("list-style-type")
    }

  }

  object margin extends StyleProperty[String]("margin") {
    val bottom = p[String]("margin-bottom")
    val left = p[String]("margin-left")
    val right = p[String]("margin-right")
    val top = p[String]("margin-top")
  }

  object mark extends StyleProperty[String]("mark") {
    val after = p[String]("mark-after")
    val before = p[String]("mark-before")
  }

  val marks = p[String]("marks")

  object marquee {
    val direction = p[String]("marquee-direction")

    object play {
      val count = p[String]("marquee-play-count")
    }

    val speed = p[String]("marquee-speed")
    val style = p[String]("marquee-style")
  }

  object max {
    val height = p[Length]("max-height")
    val width = p[Length]("max-width")
  }

  object min {
    val height = p[Length]("min-height")
    val width = p[Length]("min-width")
  }

  object move {
    val to = p[String]("move-to")
  }

  object nav {
    val down = p[String]("nav-down")
    val index = p[String]("nav-index")
    val left = p[String]("nav-left")
    val right = p[String]("nav-right")
    val up = p[String]("nav-up")
  }

  val opacity = p[String]("opacity")
  val orphans = p[String]("orphans")

  object outline extends StyleProperty[String]("outline") {
    val color = p[Color]("outline-color")
    val offset = p[String]("outline-offset")
    val style = p[String]("outline-style")
    val width = p[String]("outline-width")
  }

  object overflow extends StyleProperty[String]("overflow") {
    val style = p[String]("overflow-style")
    val x = p[String]("overflow-x")
    val y = p[String]("overflow-y")
  }

  object padding extends StyleProperty[Length]("padding") {
    val bottom = p[Length]("padding-bottom")
    val left = p[Length]("padding-left")
    val right = p[Length]("padding-right")
    val top = p[Length]("padding-top")
  }

  object page extends StyleProperty[String]("page") {

    object break {
      val after = p[String]("page-break-after")
      val before = p[String]("page-break-before")
      val inside = p[String]("page-break-inside")
    }

    val policy = p[String]("page-policy")
  }

  object perspective extends StyleProperty[String]("perspective") {
    val origin = p[String]("perspective-origin")
  }

  val phonemes = p[String]("phonemes")
  val position = p[Position]("position")

  object punctuation {
    val trim = p[String]("punctuation-trim")
  }

  val quotes = p[String]("quotes")

  object rendering {
    val intent = p[String]("rendering-intent")
  }

  val resize = p[String]("resize")

  object rest extends StyleProperty[String]("rest") {
    val after = p[String]("rest-after")
    val before = p[String]("rest-before")
  }

  val right = p[String]("right")

  object rotation extends StyleProperty[String]("rotation") {
    val point = p[String]("rotation-point")
  }

  object ruby {
    val align = p[String]("ruby-align")
    val overhang = p[String]("ruby-overhang")
    val position = p[String]("ruby-position")
    val span = p[String]("ruby-span")
  }

  val size = p[String]("size")

  object string {
    val set = p[String]("string-set")
  }

  object table {
    val layout = p[String]("table-layout")
  }

  object target extends StyleProperty[String]("target") {
    val name = p[String]("target-name")
    val newTarget = p[String]("target-new")
    val position = p[String]("target-position")
  }

  object text {

    object align extends StyleProperty[Alignment]("text-align") {
      val last = p[Alignment]("text-align-last")
    }

    val decoration = p[String]("text-decoration")
    val height = p[String]("text-height")
    val indent = p[String]("text-indent")
    val justify = p[String]("text-justify")
    val outline = p[String]("text-outline")
    val overflow = p[String]("text-overflow")
    val shadow = p[String]("text-shadow")
    val transform = p[String]("text-transform")
    val wrap = p[String]("text-wrap")
  }

  val top = p[Length]("top")

  object transform extends StyleProperty[String]("transform") {
    val origin = p[String]("transform-origin")
    val style = p[String]("transform-style")
  }

  object transition extends StyleProperty[String]("transition") {
    val delay = p[String]("transition-delay")
    val duration = p[String]("transition-duration")
    val property = p[String]("transition-property")

    object timing {
      val function = p[String]("transition-timing-function")
    }

  }

  object unicode {
    val bidi = p[String]("unicode-bidi")
  }

  object vertical {
    val align = p[String]("vertical-align")
  }

  val visibility = p[String]("visibility")

  object voice {
    val balance = p[String]("voice-balance")
    val duration = p[String]("voice-duration")

    object pitch extends StyleProperty[String]("voice-pitch") {
      val range = p[String]("voice-pitch-range")
    }

    val rate = p[String]("voice-rate")
    val stress = p[String]("voice-stress")
    val volume = p[String]("voice-volume")
  }

  object white {
    val space = p[WhiteSpace]("white-space")
  }

  val widows = p[String]("widows")
  val width = p[Length]("width")

  object word {
    val break = p[String]("word-break")
    val spacing = p[String]("word-spacing")
    val wrap = p[String]("word-wrap")
  }

  object z {
    val index = p[String]("z-index")
  }

  private def p[T](name: String) = new StyleProperty[T](name)

  def attribute = {
    contents.collect {
      case p if (p.modified) => "%s: %s".format(p._name, p.styleValue)
    }.mkString("; ")
  }

  protected[style] def register(property: StyleProperty[_]) = addChild(property)
}

class StyleProperty[T](val _name: String)(implicit ss: StyleSheet) extends Property[T] with Element {
  ss.register(this)

  def styleValue = value match {
    case color: Color => color.hex.rgb
    case _ => value
  }
}