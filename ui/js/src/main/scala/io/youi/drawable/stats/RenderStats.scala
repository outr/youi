package io.youi.drawable.stats

import io.youi.drawable.{Context, Drawable}

class RenderStats {
  private val samples = 100

  private var firstRender: Long = 0L
  private var lastRender: Long = 0L
  private var renderStart: Long = 0L
  private var renderFinish: Long = 0L
  private val elapsed: Array[Double] = new Array[Double](samples)
  private var position: Int = 0
  private var count: Long = 0L
  private var lastElapsed: Double = 0.0

  (0 until samples).foreach { index =>
    elapsed(index) = -1.0
  }

  def draw(drawable: Drawable, context: Context, x: Double, y: Double): Unit = {
    val start = System.currentTimeMillis()
    drawable.draw(context, x, y)
    val finished = System.currentTimeMillis()
    val elapsed = (finished - start) / 1000.0

    this.elapsed(position) = elapsed
    position += 1
    if (position == samples) {
      position = 0
    }

    if (firstRender == 0L) {
      firstRender = finished
    }
    lastRender = finished
    renderStart = start
    renderFinish = finished
    count += 1
    lastElapsed = elapsed
  }

  def average: Double = {
    var count = 0.0
    var sum = 0.0
    elapsed.foreach { e =>
      if (e != -1.0) {
        sum += e
        count += 1.0
      }
    }
    sum / count
  }

  def min: Double = elapsed.foldLeft(Double.MaxValue)((min, current) => if (current != -1.0) math.min(min, current) else min)

  def max: Double = elapsed.max match {
    case -1.0 => 0.0
    case d => d
  }

  def renders: Long = count

  def current: Double = lastElapsed
}
