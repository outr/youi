package io.youi.easing

import scala.util.Random

trait Easing {
  /**
    * Takes a progress (0.0 to 1.0) and applies the eamath.sing algorithm to get the return value.
    *
    * @param progress values 0.0 (start) to 1.0 (end) representing the percent complete
    * @return eased value
    */
  def calculate(progress: Double): Double
}

object Easing {
  def backIn(overshoot: Double = 1.70158): Easing = BackIn(overshoot)
  def backOut(overshoot: Double = 1.70158): Easing = BackOut(overshoot)
  def backInOut(overshoot: Double = 1.70158): Easing = BackInOut(overshoot)
  def linear: Easing = Linear
  def bounceIn: Easing = wrap(Bounce.easeIn)
  def bounceOut: Easing = wrap(Bounce.easeOut)
  def bounceInOut: Easing = wrap(Bounce.easeInOut)
  def circularIn: Easing = wrap(Circular.easeIn)
  def circularOut: Easing = wrap(Circular.easeOut)
  def circularInOut: Easing = wrap(Circular.easeInOut)
  def cubicIn: Easing = wrap(Cubic.easeIn)
  def cubicOut: Easing = wrap(Cubic.easeOut)
  def cubicInOut: Easing = wrap(Cubic.easeInOut)
  def elasticIn: Easing = wrap(Elastic.easeIn)
  def elasticOut: Easing = wrap(Elastic.easeOut)
  def elasticInOut: Easing = wrap(Elastic.easeInOut)
  def exponentialIn: Easing = wrap(Exponential.easeIn)
  def exponentialOut: Easing = wrap(Exponential.easeOut)
  def exponentialInOut: Easing = wrap(Exponential.easeInOut)
  def quadraticIn: Easing = wrap(Quadratic.easeIn)
  def quadraticOut: Easing = wrap(Quadratic.easeOut)
  def quadraticInOut: Easing = wrap(Quadratic.easeInOut)
  def quarticIn: Easing = wrap(Quartic.easeIn)
  def quarticOut: Easing = wrap(Quartic.easeOut)
  def quarticInOut: Easing = wrap(Quartic.easeInOut)
  def quinticIn: Easing = wrap(Quintic.easeIn)
  def quinticOut: Easing = wrap(Quintic.easeOut)
  def quinticInOut: Easing = wrap(Quintic.easeInOut)
  def sineIn: Easing = wrap(Sine.easeIn)
  def sineOut: Easing = wrap(Sine.easeOut)
  def sineInOut: Easing = wrap(Sine.easeInOut)

  lazy val map: Map[String, Easing] = Map(
    "BackIn" -> Easing.backIn(),
    "BackOut" -> Easing.backOut(),
    "BackInOut" -> Easing.backInOut(),
    "Linear" -> Easing.linear,
    "BounceIn" -> Easing.bounceIn,
    "BounceOut" -> Easing.bounceOut,
    "BounceInOut" -> Easing.bounceInOut,
    "CircularIn" -> Easing.circularIn,
    "CircularOut" -> Easing.circularOut,
    "CircularInOut" -> Easing.circularInOut,
    "CubicIn" -> Easing.cubicIn,
    "CubicOut" -> Easing.cubicOut,
    "CubicInOut" -> Easing.cubicInOut,
    "ElasticIn" -> Easing.elasticIn,
    "ElasticOut" -> Easing.elasticOut,
    "ElasticInOut" -> Easing.elasticInOut,
    "ExponentialIn" -> Easing.exponentialIn,
    "ExponentialOut" -> Easing.exponentialOut,
    "ExponentialInOut" -> Easing.exponentialInOut,
    "QuadraticIn" -> Easing.quadraticIn,
    "QuadraticOut" -> Easing.quadraticOut,
    "QuadraticInOut" -> Easing.quadraticInOut,
    "QuarticIn" -> Easing.quarticIn,
    "QuarticOut" -> Easing.quarticOut,
    "QuarticInOut" -> Easing.quarticInOut,
    "QuinticIn" -> Easing.quinticIn,
    "QuinticOut" -> Easing.quinticOut,
    "QuinticInOut" -> Easing.quinticInOut,
    "SineIn" -> Easing.sineIn,
    "SineOut" -> Easing.sineOut,
    "SineInOut" -> Easing.sineInOut
  )
  lazy val all: Vector[Easing] = map.values.toVector

  def random: Easing = all(Random.nextInt(all.size))

  private def wrap(f: (Double, Double, Double, Double) => Double): Easing = new Easing {
    override def calculate(progress: Double): Double = f(progress, 0.0, 1.0, 1.0)
  }
}

object Bounce {
  def easeIn(time: Double, start: Double, change: Double, duration: Double): Double = {
    change - easeOut(duration - time, 0, change, duration) + start
  }

  def easeOut(time: Double, start: Double, change: Double, duration: Double): Double = {
    val t = time / duration
    if (t < (1.0 / 2.75)) {
      change * (7.5625 * t * t) + start
    } else if (t < (2.0 / 2.75)) {
      val t2 = t - (1.5 / 2.75)
      change * (7.5625 * t2 * t2 + 0.75) + start
    } else if (t < (2.5 / 2.75)) {
      val t3 = t - (2.25 / 2.75)
      change * (7.5625 * t3 * t3 + 0.9375) + start
    } else {
      val t4 = t - (2.625 / 2.75)
      change * (7.5625 * t4 * t4 + 0.984375) + start
    }
  }

  def easeInOut(time: Double, start: Double, change: Double, duration: Double): Double = {
    if (time < duration / 2.0) {
      easeIn(time * 2.0, 0.0, change, duration) * 0.5 + start
    } else {
      easeOut(time * 2.0 - duration, 0.0, change, duration) * 0.5 + change * 0.5 + start
    }
  }
}

object Circular {
  def easeIn(time: Double, start: Double, change: Double, duration: Double): Double = {
    val t = time / duration
    -change * (math.sqrt(1.0 - t * t) - 1.0) + start
  }

  def easeOut(time: Double, start: Double, change: Double, duration: Double): Double = {
    val t = (time / duration) - 1.0
    change * math.sqrt(1.0 - t * t) + start
  }

  def easeInOut(time: Double, start: Double, change: Double, duration: Double): Double = {
    val t = time / (duration / 2)
    if (t < 1) {
      -change / 2.0 * (math.sqrt(1.0 - t * t) - 1.0) + start
    } else {
      val t2 = t - 2.0
      change / 2.0 * (math.sqrt(1.0 - t2 * t2) + 1.0) + start
    }
  }
}

object Cubic {
  def easeIn(time: Double, start: Double, change: Double, duration: Double): Double = {
    val t = time / duration
    change * t * t * t + start
  }

  def easeOut(time: Double, start: Double, change: Double, duration: Double): Double = {
    val t = time / duration - 1.0
    change * (t * t * t + 1.0) + start
  }

  def easeInOut(time: Double, start: Double, change: Double, duration: Double): Double = {
    val t = time / (duration / 2.0)
    if (t < 1.0) {
      change / 2.0 * t * t * t + start
    } else {
      val t2 = t - 2.0
      change / 2.0 * (t2 * t2 * t2 + 2.0) + start
    }
  }
}

object Elastic {
  def easeIn(time: Double, start: Double, change: Double, duration: Double): Double = {
    val t = time / duration
    if (time == 0.0) {
      start
    } else if (t == 1.0) {
      start + change
    } else {
      val period = duration * 0.3
      val amplitude = change
      val s = period / 4.0
      val t2 = t - 1.0
      -(amplitude * math.pow(2.0, 10.0 * t2) * math.sin((t2 * duration - s) * (2.0 * math.Pi) / period)) + start
    }
  }

  def easeOut(time: Double, start: Double, change: Double, duration: Double): Double = {
    val t = time / duration
    if (time == 0.0) {
      start
    } else if (t == 1.0) {
      start + change
    } else {
      val period = duration * 0.3
      val amplitude = change
      val s = period / 4.0

      amplitude * math.pow(2.0, -10.0 * t) * math.sin((t * duration - s) * (2.0 * math.Pi) / period) + change + start
    }
  }

  def easeInOut(time: Double, start: Double, change: Double, duration: Double): Double = {
    val t = time / (duration / 2.0)
    if (time == 0.0) {
      start
    } else if (t == 2.0) {
      start + change
    } else {
      val period = duration * (0.3 * 1.5)
      val amplitude = change
      val s = period / 4.0
      val t2 = t - 1.0

      if (t < 1.0) {
        -0.5 * (amplitude * math.pow(2.0, 10.0 * t2) * math.sin((t2 * duration - s) * (2.0 * math.Pi) / period)) + start
      } else {
        amplitude * math.pow(2.0, -10.0 * t2) * math.sin((t2 * duration - s) * (2.0 * math.Pi) / period) * 0.5 + change + start
      }
    }
  }
}

object Exponential {
  def easeIn(time: Double, start: Double, change: Double, duration: Double): Double = {
    if (time == 0.0) {
      start
    } else {
      change * math.pow(2.0, 10.0 * (time / duration - 1.0)) + start
    }
  }

  def easeOut(time: Double, start: Double, change: Double, duration: Double): Double = {
    if (time == duration) {
      start + change
    } else {
      change * (-math.pow(2.0, -10.0 * time / duration) + 1.0) + start
    }
  }

  def easeInOut(time: Double, start: Double, change: Double, duration: Double): Double = {
    val t = time / (duration / 2.0)
    if (time == 0.0) {
      start
    } else if (time == duration) {
      start + change
    } else if (t < 1.0) {
      change / 2.0 * math.pow(2.0, 10.0 * (t - 1.0)) + start
    } else {
      change / 2.0 * (-math.pow(2.0, -10.0 * (t - 1.0)) + 2.0) + start
    }
  }
}

object Quadratic {
  def easeIn(time: Double, start: Double, change: Double, duration: Double): Double = {
    val t = time / duration
    change * t * t + start
  }

  def easeOut(time: Double, start: Double, change: Double, duration: Double): Double = {
    val t = time / duration
    -change * t * (t - 2.0f) + start
  }

  def easeInOut(time: Double, start: Double, change: Double, duration: Double): Double = {
    val t = time / (duration / 2.0)
    if (t < 1.0) {
      change / 2.0 * t * t + start
    } else {
      val t2 = t - 1.0
      -change / 2.0 * (t2 * (t2 - 2.0) - 1.0) + start
    }
  }
}

object Quartic {
  def easeIn(time: Double, start: Double, change: Double, duration: Double): Double = {
    val t = time / duration
    change * t * t * t * t + start
  }

  def easeOut(time: Double, start: Double, change: Double, duration: Double): Double = {
    val t = time / duration - 1.0
    -change * (t * t * t * t - 1.0) + start
  }

  def easeInOut(time: Double, start: Double, change: Double, duration: Double): Double = {
    val t = time / (duration / 2.0)
    if (t < 1.0) {
      change / 2.0 * t * t * t * t + start
    } else {
      val t2 = t - 2.0
      -change / 2.0 * (t2 * t2 * t2 * t2 - 2.0) + start
    }
  }
}

object Quintic {
  def easeIn(time: Double, start: Double, change: Double, duration: Double): Double = {
    val t = time / duration
    change * t * t * t * t * t + start
  }

  def easeOut(time: Double, start: Double, change: Double, duration: Double): Double = {
    val t = time / duration - 1.0
    change * (t * t * t * t * t + 1.0) + start
  }

  def easeInOut(time: Double, start: Double, change: Double, duration: Double): Double = {
    val t = time / (duration / 2.0)
    if (t < 1.0) {
      change / 2.0 * t * t * t * t * t + start
    } else {
      val t2 = t - 2.0
      change / 2.0 * (t2 * t2 * t2 * t2 * t2 + 2.0) + start
    }
  }
}

object Sine {
  def easeIn(time: Double, start: Double, change: Double, duration: Double): Double = {
    -change * math.cos(time / duration * (math.Pi / 2.0)) + change + start
  }

  def easeOut(time: Double, start: Double, change: Double, duration: Double): Double = {
    change * math.sin(time / duration * (math.Pi / 2.0)) + start
  }

  def easeInOut(time: Double, start: Double, change: Double, duration: Double): Double = {
    -change / 2.0 * (math.cos(math.Pi * time / duration) - 1.0) + start
  }
}