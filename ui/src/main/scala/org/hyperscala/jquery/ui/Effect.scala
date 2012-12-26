package org.hyperscala.jquery.ui

import org.powerscala.{Color, Enumerated, EnumEntry}

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
sealed class Effect extends EnumEntry[Effect] {
  lazy val effectName = cs(name())

  def instance(options: String = null, duration: Int = -1, easing: Easing = null) = EffectInstance(effectName, options, duration, easing)

  protected def cs(s: String): String = s.charAt(0).toLower + s.substring(1)
}

object Effect extends Enumerated[Effect] {
  val Blind = new Effect {
    def apply(direction: Direction = Direction.Up, duration: Int = -1, easing: Easing = null) = {
      instance("direction: '%s'".format(cs(direction.name())), duration, easing)
    }
  }
  val Bounce = new Effect {
    def apply(distance: Int = 20, times: Int = 5, duration: Int = -1, easing: Easing = null) = {
      instance("distance: %s, times: %s".format(distance, times), duration, easing)
    }
  }
  val Clip = new Effect {
    def apply(direction: Direction = Direction.Up, duration: Int = -1, easing: Easing = null) = {
      instance("direction: '%s'".format(cs(direction.name())), duration, easing)
    }
  }
  val Drop = new Effect {
    def apply(direction: Direction = Direction.Left, duration: Int = -1, easing: Easing = null) = {
      instance("direction: '%s'".format(cs(direction.name())), duration, easing)
    }
  }
  val Explode = new Effect {
    def apply(pieces: Int = 9, duration: Int = -1, easing: Easing = null) = {
      instance("pieces: %s".format(pieces), duration, easing)
    }
  }
  val Fade = new Effect {
    def apply(duration: Int = -1, easing: Easing = null) = instance(null, duration, easing)
  }
  val Fold = new Effect {
    def apply(size: Int = 15, horizFirst: Boolean = false, duration: Int = -1, easing: Easing = null) = {
      instance("size: %s, horizFirst: %s".format(size, horizFirst), duration, easing)
    }
  }
  val Highlight = new Effect {
    def apply(color: Color = Color.immutable("#ffff99"), duration: Int = -1, easing: Easing = null) = {
      instance("color: '%s'".format(color.hex.rgb), duration, easing)
    }
  }
  val Puff = new Effect {
    def apply(percent: Int = 150, duration: Int = -1, easing: Easing = null) = {
      instance("percent: %s".format(percent), duration, easing)
    }
  }
  val Pulsate = new Effect {
    def apply(times: Int = 5, duration: Int = -1, easing: Easing = null) = {
      instance("times: %s".format(times), duration, easing)
    }
  }
  val Scale = new Effect {
    def apply(direction: String = "both", origin: List[String] = List("middle", "center"), percent: Int = 0, scale: String = "both", duration: Int = -1, easing: Easing = null) = {
      instance("direction: '%s', origin: [%s], percent: %s, scale: '%s'".format(direction, origin.map(s => "'%s'".format(s)).mkString(", "), percent, scale), duration, easing)
    }
  }
  val Shake = new Effect {
    def apply(direction: String = "both", distance: Int = 20, times: Int = 3, duration: Int = -1, easing: Easing = null) = {
      instance("direction: '%s', distance: %s, times: %s".format(direction, distance, times), duration, easing)
    }
  }
  val Size = new Effect {
    def apply(toWidth: Int, toHeight: Int, origin: List[String] = List("top", "left"), scale: String = "both", duration: Int = -1, easing: Easing = null) = {
      instance("to: { width: %s, height: %s }, origin: [%s], scale: '%s'".format(toWidth, toHeight, origin.map(s => "'%s'".format(s)).mkString(", "), scale), duration, easing)
    }
  }
  val Slide = new Effect {
    def apply(direction: String = "both", distance: Int = -1, duration: Int = -1, easing: Easing = null) = if (distance != -1) {
      instance("direction: '%s', distance: %s".format(direction, distance), duration, easing)
    } else {
      instance("direction: '%s'".format(direction), duration, easing)
    }
  }
  val Transfer = new Effect {
    def apply(className: String, to: String, duration: Int = -1, easing: Easing = null) = {
      instance("className: '%s', to: '%s'".format(className, to), duration, easing)
    }
  }
}

case class EffectInstance(name: String, options: String = null, duration: Int = -1, easing: Easing = null) {
  override def toString = {
    if (options == null && duration == -1 && easing == null) {
      "'%s'".format(name)
    } else {
      val b = new StringBuilder("{ effect: '%s'".format(name))
      if (options != null) {
        b.append(", ")
        b.append(options)
      }
      if (duration != -1) {
        b.append(", duration: ")
        b.append(duration)
      }
      if (easing != null) {
        b.append(", easing: '%s'".format(easing.easingName))
      }
      b.append(" }")
      b.toString()
    }
  }
}

sealed class Direction extends EnumEntry[Direction]

object Direction extends Enumerated[Direction] {
  val Up = new Direction
  val Down = new Direction
  val Left = new Direction
  val Right = new Direction
  val Vertical = new Direction
  val Horizontal = new Direction
}