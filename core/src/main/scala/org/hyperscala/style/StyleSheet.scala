package org.hyperscala.style

import org.hyperscala.value.Property
import org.powerscala.scene.AbstractMutableContainer
import org.powerscala.hierarchy.Element
import org.hyperscala.{BodyContent, WebAttribute}
import org.powerscala.Color

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
class StyleSheet(val name: String)(implicit val bodyContent: BodyContent) extends AbstractMutableContainer[StyleProperty[_]] with WebAttribute {
  bodyContent.attributes += (name -> this)
  implicit def thisStyleSheet: StyleSheet = this

  def modified = contents.find(sp => sp.modified) != None

  val alignment = new AnyRef {
    val adjust = p[String]("alignment-adjust")
    val baseline = p[String]("alignment-baseline")
  }

  val animation = new StyleProperty[String]("animation") {
    val delay = p[String]("animation-delay")
    val direction = p[String]("animation-direction")
    val duration = p[String]("animation-duration")

    val iteration = new AnyRef {
      val count = p[String]("animation-iteration-count")
    }

    val name = p[String]("animation-name")

    val play = new AnyRef {
      val state = p[String]("animation-play-state")
    }

    val timing = new AnyRef {
      val function = p[String]("animation-timing-function")
    }

  }

  val appearance = p[String]("appearance")

  val backface = new AnyRef {
    val visibility = p[String]("backface-visibility")
  }

  val background = new StyleProperty[String]("background") {
    val attachment = p[String]("background-attachment")
    val clip = p[String]("background-clip")
    val color = p[Color]("background-color")
    val image = p[Resource]("background-image")
    val origin = p[String]("background-origin")
    val position = p[String]("background-position")
    val repeat = p[String]("background-repeat")
    val size = p[String]("background-size")
  }

  val baseline = new AnyRef {
    val shift = p[String]("baseline-shift")
  }

  val bookmark = new AnyRef {
    val label = p[String]("bookmark-label")
    val level = p[String]("bookmark-level")
    val target = p[String]("bookmark-target")
  }

  val border = new StyleProperty[String]("border") {

    val bottom = new StyleProperty[String]("border-bottom") {
      val color = p[Color]("border-bottom-color")

      val left = new AnyRef {
        val radius = p[String]("border-bottom-left-radius")
      }

      val right = new AnyRef {
        val radius = p[String]("border-bottom-right-radius")
      }

      val style = p[String]("border-bottom-style")
      val width = p[String]("border-bottom-width")
    }

    val collapse = p[String]("border-collapse")
    val color = p[Color]("border-color")

    val image = new StyleProperty[String]("border-image") {
      val outset = p[String]("border-image-outset")
      val repeat = p[String]("border-image-repeat")
      val slice = p[String]("border-image-slice")
      val source = p[String]("border-image-source")
      val width = p[String]("border-image-width")
    }

    val left = new StyleProperty[String]("border-left") {
      val color = p[Color]("border-left-color")
      val style = p[String]("border-left-style")
      val width = p[String]("border-left-width")
    }

    val radius = p[String]("border-radius")

    val right = new StyleProperty[String]("border-right") {
      val color = p[Color]("border-right-color")
      val style = p[String]("border-right-style")
      val width = p[String]("border-right-width")
    }

    val spacing = p[String]("border-spacing")
    val style = p[String]("border-style")

    val top = new StyleProperty[String]("border-top") {
      val color = p[Color]("border-top-color")

      val left = new AnyRef {
        val radius = p[String]("border-top-left-radius")
      }

      val right = new AnyRef {
        val radius = p[String]("border-top-right-radius")
      }

      val style = p[String]("border-top-style")
      val width = p[String]("border-top-width")
    }

    val width = p[String]("border-width")
  }

  val bottom = p[String]("bottom")

  val box = new AnyRef {
    val align = p[String]("box-align")

    val decoration = new AnyRef {
      val break = p[String]("box-decoration-break")
    }

    val direction = p[String]("box-direction")

    val flex = new StyleProperty[String]("box-flex") {
      val group = p[String]("box-flex-group")
    }

    val lines = p[String]("box-lines")

    val ordinal = new AnyRef {
      val group = p[String]("box-ordinal-group")
    }

    val orient = p[String]("box-orient")
    val pack = p[String]("box-pack")
    val shadow = p[String]("box-shadow")
    val sizing = p[String]("box-sizing")
  }

  val caption = new AnyRef {
    val side = p[String]("caption-side")
  }

  val clear = p[Clear]("clear")
  val clip = p[String]("clip")

  val color = new StyleProperty[Color]("color") {
    val profile = p[String]("color-profile")
  }

  val column = new AnyRef {
    val count = p[String]("column-count")
    val fill = p[String]("column-fill")
    val gap = p[String]("column-gap")

    val rule = new StyleProperty[String]("column-rule") {
      val color = p[Color]("column-rule-color")
      val style = p[String]("column-rule-style")
      val width = p[String]("column-rule-width")
    }

    val span = p[String]("column-span")
    val width = p[String]("column-width")
  }

  val columns = p[String]("columns")
  val content = p[String]("content")

  val counter = new AnyRef {
    val increment = p[String]("counter-increment")
    val reset = p[String]("counter-reset")
  }

  val crop = p[String]("crop")
  val cursor = p[String]("cursor")
  val direction = p[String]("direction")
  val display = p[Display]("display")

  val dominant = new AnyRef {
    val baseline = p[String]("dominant-baseline")
  }

  val drop = new AnyRef {

    val initial = new AnyRef {

      val after = new AnyRef {
        val adjust = p[String]("drop-initial-after-adjust")
        val align = p[String]("drop-initial-after-align")
      }

      val before = new AnyRef {
        val adjust = p[String]("drop-initial-before-adjust")
        val align = p[String]("drop-initial-before-align")
      }

      val size = p[String]("drop-initial-size")
      val value = p[String]("drop-initial-value")
    }

  }

  val empty = new AnyRef {
    val cells = p[String]("empty-cells")
  }

  val fit = new StyleProperty[String]("fit") {
    val position = p[String]("fit-position")
  }

  val float = new StyleProperty[Float]("float") {
    val offset = p[String]("float-offset")
  }

  val font = new StyleProperty[String]("font") {
    val family = p[String]("font-family")

    val size = new StyleProperty[Length]("font-size") {
      val adjust = p[String]("font-size-adjust")
    }

    val stretch = p[String]("font-stretch")
    val style = p[String]("font-style")
    val variant = p[String]("font-variant")
    val weight = p[String]("font-weight")
    val face = p[String]("@font-face")
  }

  val grid = new AnyRef {
    val columns = p[String]("grid-columns")
    val rows = p[String]("grid-rows")
  }

  val hanging = new AnyRef {
    val punctuation = p[String]("hanging-punctuation")
  }

  val height = p[Length]("height")

  val hyphenate = new AnyRef {
    val after = p[String]("hyphenate-after")
    val before = p[String]("hyphenate-before")
    val character = p[String]("hyphenate-character")
    val lines = p[String]("hyphenate-lines")
    val resource = p[String]("hyphenate-resource")
  }

  val hyphens = p[String]("hyphens")
  val icon = p[String]("icon")

  val image = new AnyRef {
    val orientation = p[String]("image-orientation")
    val resolution = p[String]("image-resolution")
  }

  val inline = new AnyRef {

    val box = new AnyRef {
      val align = p[String]("inline-box-align")
    }

  }

  val left = p[Length]("left")

  val letter = new AnyRef {
    val spacing = p[String]("letter-spacing")
  }

  val line = new AnyRef {
    val height = p[String]("line-height")

    val stacking = new StyleProperty[String]("line-stacking") {
      val ruby = p[String]("line-stacking-ruby")
      val shift = p[String]("line-stacking-shift")
      val strategy = p[String]("line-stacking-strategy")
    }

  }

  val list = new AnyRef {

    val style = new StyleProperty[String]("list-style") {
      val image = p[String]("list-style-image")
      val position = p[String]("list-style-position")
      val styleType = p[String]("list-style-type")
    }

  }

  val margin = new StyleProperty[String]("margin") {
    val bottom = p[String]("margin-bottom")
    val left = p[String]("margin-left")
    val right = p[String]("margin-right")
    val top = p[String]("margin-top")
  }

  val mark = new StyleProperty[String]("mark") {
    val after = p[String]("mark-after")
    val before = p[String]("mark-before")
  }

  val marks = p[String]("marks")

  val marquee = new AnyRef {
    val direction = p[String]("marquee-direction")

    val play = new AnyRef {
      val count = p[String]("marquee-play-count")
    }

    val speed = p[String]("marquee-speed")
    val style = p[String]("marquee-style")
  }

  val max = new AnyRef {
    val height = p[Length]("max-height")
    val width = p[Length]("max-width")
  }

  val min = new AnyRef {
    val height = p[Length]("min-height")
    val width = p[Length]("min-width")
  }

  val move = new AnyRef {
    val to = p[String]("move-to")
  }

  val nav = new AnyRef {
    val down = p[String]("nav-down")
    val index = p[String]("nav-index")
    val left = p[String]("nav-left")
    val right = p[String]("nav-right")
    val up = p[String]("nav-up")
  }

  val opacity = p[String]("opacity")
  val orphans = p[String]("orphans")

  val outline = new StyleProperty[String]("outline") {
    val color = p[Color]("outline-color")
    val offset = p[String]("outline-offset")
    val style = p[String]("outline-style")
    val width = p[String]("outline-width")
  }

  val overflow = new StyleProperty[String]("overflow") {
    val style = p[String]("overflow-style")
    val x = p[String]("overflow-x")
    val y = p[String]("overflow-y")
  }

  val padding = new StyleProperty[String]("padding") {
    val bottom = p[Length]("padding-bottom")
    val left = p[Length]("padding-left")
    val right = p[Length]("padding-right")
    val top = p[Length]("padding-top")
  }

  val page = new StyleProperty[String]("page") {
    val break = new AnyRef {
      val after = p[String]("page-break-after")
      val before = p[String]("page-break-before")
      val inside = p[String]("page-break-inside")
    }

    val policy = p[String]("page-policy")
  }

  val perspective = new StyleProperty[String]("perspective") {
    val origin = p[String]("perspective-origin")
  }

  val phonemes = p[String]("phonemes")
  val position = p[Position]("position")

  val punctuation = new AnyRef {
    val trim = p[String]("punctuation-trim")
  }

  val quotes = p[String]("quotes")

  val rendering = new AnyRef {
    val intent = p[String]("rendering-intent")
  }

  val resize = p[String]("resize")

  val rest = new StyleProperty[String]("rest") {
    val after = p[String]("rest-after")
    val before = p[String]("rest-before")
  }

  val right = p[String]("right")

  val rotation = new StyleProperty[String]("rotation") {
    val point = p[String]("rotation-point")
  }

  val ruby = new AnyRef {
    val align = p[String]("ruby-align")
    val overhang = p[String]("ruby-overhang")
    val position = p[String]("ruby-position")
    val span = p[String]("ruby-span")
  }

  val size = p[String]("size")

  val string = new AnyRef {
    val set = p[String]("string-set")
  }

  val table = new AnyRef {
    val layout = p[String]("table-layout")
  }

  val target = new StyleProperty[String]("target") {
    val name = p[String]("target-name")
    val newTarget = p[String]("target-new")
    val position = p[String]("target-position")
  }

  val text = new AnyRef {
    val align = new StyleProperty[Alignment]("text-align") {
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

  val transform = new StyleProperty[String]("transform") {
    val origin = p[String]("transform-origin")
    val style = p[String]("transform-style")
  }

  val transition = new StyleProperty[String]("transition") {
    val delay = p[String]("transition-delay")
    val duration = p[String]("transition-duration")
    val property = p[String]("transition-property")

    val timing = new AnyRef {
      val function = p[String]("transition-timing-function")
    }
  }

  val unicode = new AnyRef {
    val bidi = p[String]("unicode-bidi")
  }

  val vertical = new AnyRef {
    val align = p[Length]("vertical-align")
  }

  val visibility = p[String]("visibility")

  val voice = new AnyRef {
    val balance = p[String]("voice-balance")
    val duration = p[String]("voice-duration")

    val pitch = new StyleProperty[String]("voice-pitch") {
      val range = p[String]("voice-pitch-range")
    }

    val rate = p[String]("voice-rate")
    val stress = p[String]("voice-stress")
    val volume = p[String]("voice-volume")
  }

  val white = new AnyRef {
    val space = p[WhiteSpace]("white-space")
  }

  val widows = p[String]("widows")
  val width = p[Length]("width")

  val word = new AnyRef {
    val break = p[String]("word-break")
    val spacing = p[String]("word-spacing")
    val wrap = p[String]("word-wrap")
  }

  val z = new AnyRef {
    val index = p[String]("z-index")
  }

  private def p[T](name: String)(implicit manifest: Manifest[T]) = new StyleProperty[T](name)(this, manifest)

  def attribute = {
    contents.collect {
      case p if (p.modified) => "%s: %s".format(p._name, p.styleValue)
    }.mkString("; ")
  }

  def attribute_=(value: String) = if (value.trim.length > 0) {
    try {
      value.split(";").map(s => s.split(":")).map(a => a(0).trim -> a(1).trim).foreach {
        case (k, v) => contents.find(p => p._name == k) match {
          case Some(sp) => sp.manifest.erasure.getSimpleName match {
            case "Display" => sp.asInstanceOf[StyleProperty[Display]] := Display(v)
            case "Length" => sp.asInstanceOf[StyleProperty[Length]] := Length(v)
            case "Color" => sp.asInstanceOf[StyleProperty[Color]] := (if (v.startsWith("#")) {
              Color.immutable(v)
            } else if (v.startsWith("rgb(")) {
              val s = v.substring(4, v.length - 1).split(",")
              Color.immutable(s(0).trim.toInt, s(1).trim.toInt, s(2).trim.toInt, 255)
            } else {
              Color.values.find(c => c.name.equalsIgnoreCase(v)).getOrElse(throw new RuntimeException("Unable to find color named: %s".format(v)))
            })
            case "Alignment" => sp.asInstanceOf[StyleProperty[Alignment]] := Alignment(v)
            case "Position" => sp.asInstanceOf[StyleProperty[Position]] := Position(v)
            case "Float" => sp.asInstanceOf[StyleProperty[Float]] := Float(v)
            case "Clear" => sp.asInstanceOf[StyleProperty[Clear]] := Clear(v)
            case "String" => sp.asInstanceOf[StyleProperty[String]] := v
          }
          case None => new CustomStyleProperty(k, v)
        }
      }
    } catch {
      case exc => throw new RuntimeException("Error parsing: [%s]".format(value), exc)
    }
  }

  protected[style] def register(property: StyleProperty[_]) = addChild(property)
}

class StyleProperty[T](val _name: String)(implicit ss: StyleSheet, val manifest: Manifest[T]) extends Property[T] with Element {
  ss.register(this)

  def styleValue = value match {
    case color: Color => color.hex.rgb
    case _ => value
  }
}

class CustomStyleProperty(_name: String, value: String)(implicit ss: StyleSheet) extends StyleProperty[String](_name)(ss, Manifest.classType(classOf[String]))