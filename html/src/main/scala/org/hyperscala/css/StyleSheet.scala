package org.hyperscala.css

import org.powerscala.property.{Property, PropertyParent}
import org.hyperscala.PropertyAttribute
import attributes._
import org.hyperscala.persistence.StringPersistence

import org.powerscala.Color
import org.powerscala.event.Listenable

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <mhicks@hyperscala.org>
 */
class StyleSheet extends PropertyParent with Listenable {
  implicit val stringPersistence = StringPersistence
  implicit val thisStyleSheet = this

  val name = PropertyAttribute[String]("name", null)
  def parent = null

  val alignment = new AnyRef {
    val adjust = StyleSheetAttribute[String]("alignment-adjust", null)
    val baseline = StyleSheetAttribute[String]("alignment-baseline", null)
  }
  val animation = new StyleSheetAttribute[String]("animation", null) {
    val delay = StyleSheetAttribute[String]("animation-delay", null)
    val direction = StyleSheetAttribute[String]("animation-direction", null)
    val duration = StyleSheetAttribute[String]("animation-duration", null)
    val iteration = new AnyRef {
      val count = StyleSheetAttribute[String]("animation-iteration-count", null)
    }
    override val name = StyleSheetAttribute[String]("animation-name", null)
    val play = new AnyRef {
      val state = StyleSheetAttribute[String]("animation-play-state", null)
    }
    val timing = new AnyRef {
      val function = StyleSheetAttribute[String]("animation-timing-function", null)
    }
  }
  val appearance = StyleSheetAttribute[String]("appearance", null)
  val backface = new AnyRef {
    val visibility = StyleSheetAttribute[String]("backface-visibility", null)
  }
  val background = new StyleSheetAttribute[String]("background", null) {
    val attachment = StyleSheetAttribute[String]("background-attachment", null)
    val clip = StyleSheetAttribute[String]("background-clip", null)
    val color = StyleSheetAttribute[Color]("background-color", null)
    val image = StyleSheetAttribute[Resource]("background-image", null)
    val origin = StyleSheetAttribute[String]("background-origin", null)
    val position = StyleSheetAttribute[String]("background-position", null)
    val repeat = StyleSheetAttribute[String]("background-repeat", null)
    val size = StyleSheetAttribute[String]("background-size", null)
  }
  val baseline = new AnyRef {
    val shift = StyleSheetAttribute[String]("baseline-shift", null)
  }
  val bookmark = new AnyRef {
    val label = StyleSheetAttribute[String]("bookmark-label", null)
    val level = StyleSheetAttribute[String]("bookmark-level", null)
    val target = StyleSheetAttribute[String]("bookmark-target", null)
  }
  val border = new StyleSheetAttribute[String]("border", null) {
    val bottom = new StyleSheetAttribute[String]("border-bottom", null) {
      val color = StyleSheetAttribute[Color]("border-bottom-color", null)
      val left = new AnyRef {
        val radius = StyleSheetAttribute[Length]("border-bottom-left-radius", null)
      }
      val right = new AnyRef {
        val radius = StyleSheetAttribute[Length]("border-bottom-right-radius", null)
      }
      val style = StyleSheetAttribute[String]("border-bottom-style", null)
      val width = StyleSheetAttribute[Length]("border-bottom-width", null)
    }
    val collapse = StyleSheetAttribute[String]("border-collapse", null)
    val color = StyleSheetAttribute[Color]("border-color", null)
    val image = new StyleSheetAttribute[String]("border-image", null) {
      val outset = StyleSheetAttribute[String]("border-image-outset", null)
      val repeat = StyleSheetAttribute[String]("border-image-repeat", null)
      val slice = StyleSheetAttribute[String]("border-image-slice", null)
      val source = StyleSheetAttribute[String]("border-image-source", null)
      val width = StyleSheetAttribute[Length]("border-image-width", null)
    }
    val left = new StyleSheetAttribute[String]("border-left", null) {
      val color = StyleSheetAttribute[Color]("border-left-color", null)
      val style = StyleSheetAttribute[String]("border-left-style", null)
      val width = StyleSheetAttribute[Length]("border-left-width", null)
    }
    val radius = StyleSheetAttribute[Length]("border-radius", null)
    val right = new StyleSheetAttribute[String]("border-right", null) {
      val color = StyleSheetAttribute[Color]("border-right-color", null)
      val style = StyleSheetAttribute[String]("border-right-style", null)
      val width = StyleSheetAttribute[Length]("border-right-width", null)
    }
    val spacing = StyleSheetAttribute[String]("border-spacing", null)
    val style = StyleSheetAttribute[String]("border-style", null)
    val top = new StyleSheetAttribute[String]("border-top", null) {
      val color = StyleSheetAttribute[Color]("border-top-color", null)
      val left = new AnyRef {
        val radius = StyleSheetAttribute[Length]("border-top-left-radius", null)
      }
      val right = new AnyRef {
        val radius = StyleSheetAttribute[Length]("border-top-right-radius", null)
      }
      val style = StyleSheetAttribute[String]("border-top-style", null)
      val width = StyleSheetAttribute[Length]("border-top-width", null)
    }
    val width = StyleSheetAttribute[Length]("border-width", null)
  }
  val bottom = StyleSheetAttribute[String]("bottom", null)
  val box = new AnyRef {
    val align = StyleSheetAttribute[String]("box-align", null)
    val decoration = new AnyRef {
      val break = StyleSheetAttribute[String]("box-decoration-break", null)
    }
    val direction = StyleSheetAttribute[String]("box-direction", null)
    val flex = new StyleSheetAttribute[String]("box-flex", null) {
      val group = StyleSheetAttribute[String]("box-flex-group", null)
    }
    val lines = StyleSheetAttribute[String]("box-lines", null)
    val ordinal = new AnyRef {
      val group = StyleSheetAttribute[String]("box-ordinal-group", null)
    }
    val orient = StyleSheetAttribute[String]("box-orient", null)
    val pack = StyleSheetAttribute[String]("box-pack", null)
    val shadow = StyleSheetAttribute[String]("box-shadow", null)
    val sizing = StyleSheetAttribute[String]("box-sizing", null)
  }
  val caption = new AnyRef {
    val side = StyleSheetAttribute[String]("caption-side", null)
  }
  val clear = StyleSheetAttribute[Clear]("clear", null)
  val clip = StyleSheetAttribute[String]("clip", null)
  val color = new StyleSheetAttribute[Color]("color", null) {
    val profile = StyleSheetAttribute[String]("color-profile", null)
  }
  val column = new AnyRef {
    val count = StyleSheetAttribute[String]("column-count", null)
    val fill = StyleSheetAttribute[String]("column-fill", null)
    val gap = StyleSheetAttribute[String]("column-gap", null)
    val rule = new StyleSheetAttribute[String]("column-rule", null) {
      val color = StyleSheetAttribute[Color]("column-rule-color", null)
      val style = StyleSheetAttribute[String]("column-rule-style", null)
      val width = StyleSheetAttribute[Length]("column-rule-width", null)
    }
    val span = StyleSheetAttribute[String]("column-span", null)
    val width = StyleSheetAttribute[Length]("column-width", null)
  }
  val columns = StyleSheetAttribute[String]("columns", null)
  val content = StyleSheetAttribute[String]("content", null)
  val counter = new AnyRef {
    val increment = StyleSheetAttribute[String]("counter-increment", null)
    val reset = StyleSheetAttribute[String]("counter-reset", null)
  }
  val crop = StyleSheetAttribute[String]("crop", null)
  val cursor = StyleSheetAttribute[String]("cursor", null)
  val direction = StyleSheetAttribute[String]("direction", null)
  val display = StyleSheetAttribute[Display]("display", null)
  val dominant = new AnyRef {
    val baseline = StyleSheetAttribute[String]("dominant-baseline", null)
  }
  val drop = new AnyRef {
    val initial = new AnyRef {
      val after = new AnyRef {
        val adjust = StyleSheetAttribute[String]("drop-initial-after-adjust", null)
        val align = StyleSheetAttribute[String]("drop-initial-after-align", null)
      }
      val before = new AnyRef {
        val adjust = StyleSheetAttribute[String]("drop-initial-before-adjust", null)
        val align = StyleSheetAttribute[String]("drop-initial-before-align", null)
      }
      val size = StyleSheetAttribute[String]("drop-initial-size", null)
      val value = StyleSheetAttribute[String]("drop-initial-value", null)
    }
  }
  val empty = new AnyRef {
    val cells = StyleSheetAttribute[String]("empty-cells", null)
  }
  val fit = new StyleSheetAttribute[String]("fit", null) {
    val position = StyleSheetAttribute[String]("fit-position", null)
  }
  val float = new StyleSheetAttribute[Float]("float", null) {
    val offset = StyleSheetAttribute[String]("float-offset", null)
  }
  val font = new StyleSheetAttribute[String]("font", null) {
    val face = StyleSheetAttribute[String]("font-face", null)
    val family = StyleSheetAttribute[String]("font-family", null)
    val size = new StyleSheetAttribute[FontSize]("font-size", null) {
      val adjust = StyleSheetAttribute[String]("font-size-adjust", null)
    }
    val stretch = StyleSheetAttribute[String]("font-stretch", null)
    val style = StyleSheetAttribute[String]("font-style", null)
    val variant = StyleSheetAttribute[String]("font-variant", null)
    val weight = StyleSheetAttribute[String]("font-weight", null)
  }
  val grid = new AnyRef {
    val columns = StyleSheetAttribute[String]("grid-columns", null)
    val rows = StyleSheetAttribute[String]("grid-rows", null)
  }
  val hanging = new AnyRef {
    val punctuation = StyleSheetAttribute[String]("hanging-punctuation", null)
  }
  val height = StyleSheetAttribute[Length]("height", null)
  val hyphenate = new AnyRef {
    val after = StyleSheetAttribute[String]("hyphenate-after", null)
    val before = StyleSheetAttribute[String]("hyphenate-before", null)
    val character = StyleSheetAttribute[String]("hyphenate-character", null)
    val lines = StyleSheetAttribute[String]("hyphenate-lines", null)
    val resource = StyleSheetAttribute[String]("hyphenate-resource", null)
  }
  val hyphens = StyleSheetAttribute[String]("hyphens", null)
  val icon = StyleSheetAttribute[String]("icon", null)
  val image = new AnyRef {
    val orientation = StyleSheetAttribute[String]("image-orientation", null)
    val resolution = StyleSheetAttribute[String]("image-resolution", null)
  }
  val inline = new AnyRef {
    val box = new AnyRef {
      val align = StyleSheetAttribute[String]("inline-box-align", null)
    }
  }
  val left = StyleSheetAttribute[Length]("left", null)
  val letter = new AnyRef {
    val spacing = StyleSheetAttribute[String]("letter-spacing", null)
  }
  val line = new AnyRef {
    val height = StyleSheetAttribute[String]("line-height", null)
    val stacking = new StyleSheetAttribute[String]("line-stacking", null) {
      val ruby = StyleSheetAttribute[String]("line-stacking-ruby", null)
      val shift = StyleSheetAttribute[String]("line-stacking-shift", null)
      val strategy = StyleSheetAttribute[String]("line-stacking-strategy", null)
    }
  }
  val list = new AnyRef {
    val style = new StyleSheetAttribute[String]("list-style", null) {
      val image = StyleSheetAttribute[String]("list-style-image", null)
      val position = StyleSheetAttribute[String]("list-style-position", null)
      val styleType = StyleSheetAttribute[String]("list-style-type", null)
    }
  }
  val margin = new StyleSheetAttribute[String]("margin", null) {
    val bottom = StyleSheetAttribute[Length]("margin-bottom", null)
    val left = StyleSheetAttribute[Length]("margin-left", null)
    val right = StyleSheetAttribute[Length]("margin-right", null)
    val top = StyleSheetAttribute[Length]("margin-top", null)

    /**
     * Allows modification of all lengths with one property
     */
    val all = Property[Length]("margin-all", null)
    bottom.bind(all)
    left.bind(all)
    right.bind(all)
    top.bind(all)
  }
  val mark = new StyleSheetAttribute[String]("mark", null) {
    val after = StyleSheetAttribute[String]("mark-after", null)
    val before = StyleSheetAttribute[String]("mark-before", null)
  }
  val marks = StyleSheetAttribute[String]("marks", null)
  val marquee = new AnyRef {
    val direction = StyleSheetAttribute[String]("marquee-direction", null)
    val play = new AnyRef {
      val count = StyleSheetAttribute[String]("marquee-play-count", null)
    }
    val speed = StyleSheetAttribute[String]("marquee-speed", null)
    val style = StyleSheetAttribute[String]("marquee-style", null)
  }
  val max = new AnyRef {
    val height = StyleSheetAttribute[Length]("max-height", null)
    val width = StyleSheetAttribute[Length]("max-width", null)
  }
  val min = new AnyRef {
    val height = StyleSheetAttribute[Length]("min-height", null)
    val width = StyleSheetAttribute[Length]("min-width", null)
  }
  val move = new AnyRef {
    val to = StyleSheetAttribute[String]("move-to", null)
  }
  val nav = new AnyRef {
    val down = StyleSheetAttribute[String]("nav-down", null)
    val index = StyleSheetAttribute[String]("nav-index", null)
    val left = StyleSheetAttribute[String]("nav-left", null)
    val right = StyleSheetAttribute[String]("nav-right", null)
    val up = StyleSheetAttribute[String]("nav-up", null)
  }
  val opacity = StyleSheetAttribute[Opacity]("opacity", null)
  val orphans = StyleSheetAttribute[String]("orphans", null)
  val outline = new StyleSheetAttribute[String]("outline", null) {
    val color = StyleSheetAttribute[Color]("outline-color", null)
    val offset = StyleSheetAttribute[String]("outline-offset", null)
    val style = StyleSheetAttribute[String]("outline-style", null)
    val width = StyleSheetAttribute[Length]("outline-width", null)
  }
  val overflow = new StyleSheetAttribute[Overflow]("overflow", null) {
    val style = StyleSheetAttribute[String]("overflow-style", null)
    val x = StyleSheetAttribute[Overflow]("overflow-x", null)
    val y = StyleSheetAttribute[Overflow]("overflow-y", null)
  }
  val padding = new StyleSheetAttribute[String]("padding", null) {
    val bottom = StyleSheetAttribute[Length]("padding-bottom", null)
    val left = StyleSheetAttribute[Length]("padding-left", null)
    val right = StyleSheetAttribute[Length]("padding-right", null)
    val top = StyleSheetAttribute[Length]("padding-top", null)

    /**
     * Allows modification of all lengths with one property
     */
    val all = Property[Length]("padding-all", null)
    bottom.bind(all)
    left.bind(all)
    right.bind(all)
    top.bind(all)
  }
  val page = new StyleSheetAttribute[String]("page", null) {
    val break = new AnyRef {
      val after = StyleSheetAttribute[String]("page-break-after", null)
      val before = StyleSheetAttribute[String]("page-break-before", null)
      val inside = StyleSheetAttribute[String]("page-break-inside", null)
    }
    val policy = StyleSheetAttribute[String]("page-policy", null)
  }
  val perspective = new StyleSheetAttribute[String]("perspective", null) {
    val origin = StyleSheetAttribute[String]("perspective-origin", null)
  }
  val phonemes = StyleSheetAttribute[String]("phonemes", null)
  val position = StyleSheetAttribute[Position]("position", null)
  val punctuation = new AnyRef {
    val trim = StyleSheetAttribute[String]("punctuation-trim", null)
  }
  val quotes = StyleSheetAttribute[String]("quotes", null)
  val rendering = new AnyRef {
    val intent = StyleSheetAttribute[String]("rendering-intent", null)
  }
  val resize = StyleSheetAttribute[String]("resize", null)
  val rest = new StyleSheetAttribute[String]("rest", null) {
    val after = StyleSheetAttribute[String]("rest-after", null)
    val before = StyleSheetAttribute[String]("rest-before", null)
  }
  val right = StyleSheetAttribute[String]("right", null)
  val rotation = new StyleSheetAttribute[String]("rotation", null) {
    val point = StyleSheetAttribute[String]("rotation-point", null)
  }
  val ruby = new AnyRef {
    val align = StyleSheetAttribute[String]("ruby-align", null)
    val overhang = StyleSheetAttribute[String]("ruby-overhang", null)
    val position = StyleSheetAttribute[String]("ruby-position", null)
    val span = StyleSheetAttribute[String]("ruby-span", null)
  }
  val size = StyleSheetAttribute[String]("size", null)
  val string = new AnyRef {
    val set = StyleSheetAttribute[String]("string-set", null)
  }
  val table = new AnyRef {
    val layout = StyleSheetAttribute[String]("table-layout", null)
  }
  val target = new StyleSheetAttribute[String]("target", null) {
    override val name = StyleSheetAttribute[String]("target-name", null)
    val position = StyleSheetAttribute[String]("target-position", null)
    val targetNew = StyleSheetAttribute[String]("target-new", null)
  }
  val text = new AnyRef {
    val align = new StyleSheetAttribute[Alignment]("text-align", null) {
      val last = StyleSheetAttribute[Alignment]("text-align-last", null)
    }
    val decoration = StyleSheetAttribute[String]("text-decoration", null)
    val height = StyleSheetAttribute[String]("text-height", null)
    val indent = StyleSheetAttribute[String]("text-indent", null)
    val justify = StyleSheetAttribute[String]("text-justify", null)
    val outline = StyleSheetAttribute[String]("text-outline", null)
    val overflow = StyleSheetAttribute[String]("text-overflow", null)
    val shadow = StyleSheetAttribute[String]("text-shadow", null)
    val transform = StyleSheetAttribute[String]("text-transform", null)
    val wrap = StyleSheetAttribute[String]("text-wrap", null)
  }
  val top = StyleSheetAttribute[Length]("top", null)
  val transform = new StyleSheetAttribute[String]("transform", null) {
    val origin = StyleSheetAttribute[String]("transform-origin", null)
    val style = StyleSheetAttribute[String]("transform-style", null)
  }
  val transition = new StyleSheetAttribute[String]("transition", null) {
    val delay = StyleSheetAttribute[String]("transition-delay", null)
    val duration = StyleSheetAttribute[String]("transition-duration", null)
    val property = StyleSheetAttribute[String]("transition-property", null)
    val timing = new AnyRef {
      val function = StyleSheetAttribute[String]("transition-timing-function", null)
    }
  }
  val unicode = new AnyRef {
    val bidi = StyleSheetAttribute[String]("unicode-bidi", null)
  }
  val vertical = new AnyRef {
    val align = StyleSheetAttribute[Length]("vertical-align", null)
  }
  val visibility = StyleSheetAttribute[String]("visibility", null)
  val voice = new AnyRef {
    val balance = StyleSheetAttribute[String]("voice-balance", null)
    val duration = StyleSheetAttribute[String]("voice-duration", null)
    val pitch = new StyleSheetAttribute[String]("voice-pitch", null) {
      val range = StyleSheetAttribute[String]("voice-pitch-range", null)
    }
    val rate = StyleSheetAttribute[String]("voice-rate", null)
    val stress = StyleSheetAttribute[String]("voice-stress", null)
    val volume = StyleSheetAttribute[String]("voice-volume", null)
  }
  val white = new AnyRef {
    val space = StyleSheetAttribute[WhiteSpace]("white-space", null)
  }
  val widows = StyleSheetAttribute[String]("widows", null)
  val width = StyleSheetAttribute[Length]("width", null)
  val word = new AnyRef {
    val break = StyleSheetAttribute[String]("word-break", null)
    val spacing = StyleSheetAttribute[String]("word-spacing", null)
    val wrap = StyleSheetAttribute[String]("word-wrap", null)
  }
  val z = new AnyRef {
    val index = StyleSheetAttribute[String]("z-index", null)
  }
}