package org.hyperscala.ui

import org.hyperscala.module.Module
import org.powerscala.{Storage, StorageComponent, Version}
import org.hyperscala.web.{Webpage, Website}
import org.hyperscala.html.{HTMLTag, tag}
import org.powerscala.event.{Intercept, Listenable}
import org.hyperscala.realtime.Realtime
import org.hyperscala.selector.Selector
import org.powerscala.property.Property
import org.hyperscala.css.attributes._
import org.hyperscala.Unique

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Coordinates(val converter: CoordinateConverter)
            extends Listenable with StorageComponent[CoordinatesTag, HTMLTag] {
  Webpage().require(Coordinates)

  val id = Unique()

  override protected def componentIdentifier = s"coordinates-$id"

  protected def create(t: HTMLTag) = Coordinates.synchronized {
    new CoordinatesTag(this, t)
  }
}

trait CoordinateConverter {
  def coordinatesForX(x: Double, width: Double): Double
  def coordinatesForY(y: Double, height: Double): Double
  def localXFor(ct: CoordinatesTag, x: Double): Int
  def localYFor(ct: CoordinatesTag, y: Double): Int
}

class AdjustedConverter(adjustToX: Double => Double, adjustToY: Double => Double,
                        adjustFromX: Double => Double, adjustFromY: Double => Double) extends CoordinateConverter {
  def coordinatesForX(x: Double, width: Double) = adjustToX(x)
  def coordinatesForY(y: Double, height: Double) = adjustToY(y)

  def localXFor(ct: CoordinatesTag, x: Double) = {
    val ax = adjustFromX(x)
    val absX = math.round(ax).toInt
    absX
  }
  def localYFor(ct: CoordinatesTag, y: Double) = {
    val ay = adjustFromY(y)
    val absY = math.round(ay).toInt
    absY
  }
}

class CoordinatesTag(coordinates: Coordinates, val t: HTMLTag) extends Listenable {
  val support = Storage.getOrSet(t, "coordinates-support", new CoordinatesSupport(t))
  private val updatingCoordinates = new ThreadLocal[Boolean] {
    override def initialValue() = false
  }
  def isUpdatingCoordinates = updatingCoordinates.get()

  val x = new Property[Double](default = Some(-1.0))
  val y = new Property[Double](default = Some(-1.0))
  val manageX = new Property[Boolean](default = Option(false))
  val manageY = new Property[Boolean](default = Option(false))
  val horizontal = new Property[Horizontal](default = Some(Horizontal.Left))
  val vertical = new Property[Vertical](default = Some(Vertical.Middle))
  val enabled = new Property[Boolean](default = Some(true))

  def xInt = math.round(x()).toInt
  def yInt = math.round(y()).toInt

  WindowSize.width.change and WindowSize.height.change on {
    case evt => if (enabled()) {   // Update the coordinates when the screen resizes
      check()
      update()
    }
  }

  support.x.change and support.y.change and support.width.change and support.height.change on {
    case evt => if (enabled()) {
      update()  // client changed positioning so we need to update the coordinates
    }
  }
  horizontal.change.on {
    case evt => if (enabled()) update()     // horizontal changed, so we need to update the coordinates
  }
  vertical.change.on {
    case evt => if (enabled()) update()     // horizontal changed, so we need to update the coordinates
  }
  x.change.on {
    case evt => if (!isUpdatingCoordinates && enabled()) {
      manageX := true
      update()
    }
  }
  y.change.on {
    case evt => if (!isUpdatingCoordinates && enabled()) {
      manageY := true
      update()
    }
  }
  manageX.change and manageY.change on {
    case evt => if (enabled()) update()
  }

  check()

  def update() = {
    if (manageX()) {
      updateElementX()
    } else {
      updateCoordinatesX()
    }
    if (manageY()) {
      updateElementY()
    } else {
      updateCoordinatesY()
    }
  }

  def updateCoordinatesX() = {
    updatingCoordinates.set(true)
    try {
      val width = support.width().toDouble
      val cx = coordinates.converter.coordinatesForX(support.x(), width)

      // Adjust for horizontal
      val mx = horizontal() match {
        case Horizontal.Left => cx
        case Horizontal.Center => cx + (width / 2.0)
        case Horizontal.Right => cx + width
      }
      x := mx
    } finally {
      updatingCoordinates.set(false)
    }
  }

  def updateCoordinatesY() = {
    updatingCoordinates.set(true)
    try {
      val height = support.height().toDouble
      val cy = coordinates.converter.coordinatesForY(support.y(), height)

      // Adjust for vertical
      val my = vertical() match {
        case Vertical.Top => cy
        case Vertical.Middle => cy + (height / 2.0)
        case Vertical.Bottom => cy + height
      }
      y := my
    } finally {
      updatingCoordinates.set(false)
    }
  }

  def updateElementX() = {
    val localX = coordinates.converter.localXFor(this, x())
    val width = support.width()
    val mx = horizontal() match {
      case Horizontal.Left => localX
      case Horizontal.Center => localX - math.round(width / 2.0).toInt
      case Horizontal.Right => localX - width
    }
//    println(s"updateElementX - X: ${x()}, Horizontal: ${horizontal()} LocalX: $localX, Width: $width, Modified: $mx, Window Width: ${WindowSize.width()}")
    t.style.left(Length.Pixels(mx))
  }

  def updateElementY() = {
    val localY = coordinates.converter.localYFor(this, y())
    val height = support.height()
    val my = vertical() match {
      case Vertical.Top => localY
      case Vertical.Middle => localY - math.round(height / 2.0).toInt
      case Vertical.Bottom => localY - height
    }
//    println(s"updateElementY - Y: ${y()}, Vertical: ${vertical()} LocalY: $localY, Height: $height, Modified: $my, Window Height: ${WindowSize.height()}")
    t.style.top(Length.Pixels(my))
  }

  def check(batch: Boolean = false) = support.check(batch)
}

class CoordinatesSupport(t: HTMLTag) extends Listenable {
  val x = new Property[Int]()
  val y = new Property[Int]()
  val width = new Property[Int]()
  val height = new Property[Int]()

  t.eventReceived.on {
    case evt => if (evt.event == "coordinates") {
      val ex = evt.message[Int]("x")
      val ey = evt.message[Int]("y")
      val ew = evt.message[Int]("width")
      val eh = evt.message[Int]("height")
      x := ex
      y := ey
      width := ew
      height := eh

      Intercept.Stop
    } else {
      Intercept.Continue
    }
  }

  def check(batch: Boolean = false) = {
    Realtime.sendJavaScript(s"checkCoordinates('${t.identity}', $batch);", selector = Selector.id(t), onlyRealtime = false)
  }
}

object Coordinates extends Module {
  val name = "coordinates"
  val version = Version(1)

  override val dependencies = List(Realtime, WindowSize)

  def init() = {
    Website().register("/coordinates.js", "coordinates.js")
  }

  def load() = {
    val page = Webpage()
    page.head.contents += new tag.Script(mimeType = "text/javascript", src = "/coordinates.js")
  }
}