package org.hyperscala.ui

import org.powerscala.StorageComponent
import org.hyperscala.web.Webpage
import org.hyperscala.html.HTMLTag
import org.powerscala.event.Listenable
import org.powerscala.property.Property
import org.hyperscala.css.attributes._
import org.powerscala.Unique
import org.powerscala.log.Logging
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <matt@outr.com>
 */
abstract class Coordinates(val webpage: Webpage) extends Listenable with StorageComponent[CoordinatesTag, HTMLTag] {
  val id = Unique()

  override protected def componentIdentifier = s"coordinates-$id"

  def coordinateX(absolute: Double, width: Double): Double
  def coordinateY(absolute: Double, height: Double): Double

  def coordinateX(absolute: Double, width: Double, horizontal: Horizontal): Double = {
    val value = coordinateX(absolute, width)
    horizontal match {
      case Horizontal.Left => value
      case Horizontal.Center => value + (width * 0.5)
      case Horizontal.Right => value + width
    }
  }
  def coordinateY(absolute: Double, height: Double, vertical: Vertical): Double = {
    val value = coordinateY(absolute, height)
    vertical match {
      case Vertical.Top => value
      case Vertical.Middle => value + (height * 0.5)
      case Vertical.Bottom => value + height
    }
  }

  def localizeX(x: Double, ct: CoordinatesTag): Double
  def localizeY(y: Double, ct: CoordinatesTag): Double

  protected def create(t: HTMLTag) = synchronized {
    new CoordinatesTag(this, Bounding(t), t)
  }
}

abstract class CoordinatesFromZero(webpage: Webpage) extends Coordinates(webpage) {
  def coordinateX(absolute: Double, width: Double) = absolute - zeroX

  def coordinateY(absolute: Double, height: Double) = absolute - zeroY

  def localizeX(x: Double, ct: CoordinatesTag) = x + zeroX

  def localizeY(y: Double, ct: CoordinatesTag) = y + zeroY

  def zeroX: Double
  def zeroY: Double
}

class Coordinates960(webpage: Webpage) extends CoordinatesFromZero(webpage) {
  def zeroX = (WindowSize.width(webpage)() / 2.0) - 480.0

  def zeroY = 0.0

  override protected def create(t: HTMLTag) = {
    val ct = super.create(t)
    WindowSize.width(webpage).change and WindowSize.height(webpage).change on {   // This coordinate system depends on WindowSize as well
      case evt => ct.update()
    }

    ct
  }
}

class CoordinatesOffsetFromCenter(webpage: Webpage, offsetX: Double = 0.0, offsetY: Double = 0.0) extends Coordinates(webpage) {
  def coordinateX(absolute: Double, width: Double) = absolute - (WindowSize.width(webpage)() / 2.0)

  def coordinateY(absolute: Double, height: Double) = absolute - (WindowSize.height(webpage)() / 2.0)

  def localizeX(x: Double, ct: CoordinatesTag) = x + (WindowSize.width(webpage)() / 2.0)

  def localizeY(y: Double, ct: CoordinatesTag) = y + (WindowSize.height(webpage)() / 2.0)

  override protected def create(t: HTMLTag) = {
    val ct = super.create(t)
    WindowSize.width(webpage).change and WindowSize.height(webpage).change on {   // This coordinate system depends on WindowSize as well
      case evt => ct.update()
    }
    ct
  }
}

class CoordinatesTag(val coordinates: Coordinates, val b: Bounding, val t: HTMLTag) extends Listenable with Logging {
  private val updatingCoordinates = new ThreadLocal[Boolean] {
    override def initialValue() = false
  }
  def isUpdatingCoordinates = updatingCoordinates.get()

  val x = new Property[Double](default = Some(-1.0))
  val y = new Property[Double](default = Some(-1.0))
  val horizontal = new Property[Horizontal](default = Some(Horizontal.Left))
  val vertical = new Property[Vertical](default = Some(Vertical.Top))
  val manageX = new Property[Boolean](default = Option(false))
  val manageY = new Property[Boolean](default = Option(false))
  val enabled = new Property[Boolean](default = Some(true))

  def apply(horizontal: Horizontal) = horizontal match {
    case Horizontal.Left => left
    case Horizontal.Center => center
    case Horizontal.Right => right
  }

  def apply(vertical: Vertical) = vertical match {
    case Vertical.Top => top
    case Vertical.Middle => middle
    case Vertical.Bottom => bottom
  }

  def left = horizontal() match {
    case Horizontal.Left => x()
    case Horizontal.Center => x() - (b.width() / 2.0)
    case Horizontal.Right => x() - b.width()
  }
  def center = horizontal() match {
    case Horizontal.Left => x() + (b.width() / 2.0)
    case Horizontal.Center => x()
    case Horizontal.Right => x() - (b.width() / 2.0)
  }
  def right = horizontal() match {
    case Horizontal.Left => x() + b.width()
    case Horizontal.Center => x() + (b.width() / 2.0)
    case Horizontal.Right => x()
  }

  def top = vertical() match {
    case Vertical.Top => y()
    case Vertical.Middle => y() - (b.height() / 2.0)
    case Vertical.Bottom => y() - b.height()
  }
  def middle = vertical() match {
    case Vertical.Top => y() + (b.height() / 2.0)
    case Vertical.Middle => y()
    case Vertical.Bottom => y() - (b.height() / 2.0)
  }
  def bottom = vertical() match {
    case Vertical.Top => y() + b.height()
    case Vertical.Middle => y() + (b.height() / 2.0)
    case Vertical.Bottom => y()
  }

  b.localX.change and b.localY.change and b.absoluteX.change and b.absoluteY.change and b.width.change and b.height.change on {
    case evt => update()
  }

  x.change.on {
    case evt => if (!isUpdatingCoordinates) {
      update()
    }
  }
  y.change.on {
    case evt => if (!isUpdatingCoordinates) {
      update()
    }
  }
  horizontal.change.on {
    case evt => update()
  }
  vertical.change.on {
    case evt => update()
  }
  manageX.change and manageY.change on {
    case evt => update()
  }

  update()

  t.data("manage-x") match {      // See if this is already a managed element
    case Some(v) => {
      manageX := v == "true"
      try {
        x := t.data("x").get.toDouble
      } catch {
        case exc: Throwable => //warn(s"Unable to set x from data-x: ${t.data("x")}")
      }
    }
    case None => // Ignore
  }

  t.data("manage-y") match {      // See if this is already a managed element
    case Some(v) => {
      manageY := v == "true"
      try {
        y := t.data("y").get.toDouble
      } catch {
        case exc: Throwable => //warn(s"Unable to set y from data-y: ${t.data("y")}")
      }
    }
    case None => // Ignore
  }

  def update() = if (enabled()) {
    t.data("manage-x", manageX().toString)
    t.data("manage-y", manageY().toString)
    if (manageX()) {
      updateElementX(x(), horizontal())
    } else {
      updateCoordinatesX()
    }
    if (manageY()) {
      updateElementY(y(), vertical())
    } else {
      updateCoordinatesY()
    }
  }

  def set(x: Double, y: Double, horizontal: Horizontal, vertical: Vertical) = {
    updateElementX(x, horizontal)
    updateElementY(y, vertical)
  }

  private def updateCoordinatesX() = doUpdate {
    val cx = coordinates.coordinateX(b.absoluteX(), b.width())
    val ux = horizontal() match {
      case Horizontal.Left => cx
      case Horizontal.Center => cx + (b.width() / 2.0)
      case Horizontal.Right => cx + b.width()
    }
    x := ux
  }

  private def updateCoordinatesY() = doUpdate {
    val cy = coordinates.coordinateY(b.absoluteY(), b.height())
    val uy = vertical() match {
      case Vertical.Top => cy
      case Vertical.Middle => cy + (b.height() / 2.0)
      case Vertical.Bottom => cy + b.height()
    }
    y := uy
  }

  private def doUpdate[T](f: => T): T = {
    if (isUpdatingCoordinates) {
      throw new RuntimeException("Cannot stack updates!")
    }
    updatingCoordinates.set(true)
    try {
      f
    } finally {
      updatingCoordinates.set(false)
    }
  }

  private def updateElementX(x: Double, horizontal: Horizontal) = {
    t.data("x", x.toString)
    doUpdate {
      this.x := x     // TODO: adjust for horizontal
    }
    val lx = math.round(coordinates.localizeX(x, this))
    val ux = horizontal match {
      case Horizontal.Left => lx
      case Horizontal.Center => lx - (b.width() / 2.0)
      case Horizontal.Right => lx - b.width()
    }
//    val localX = if (t.style.position() == Position.Absolute) {
//      ux
//    } else {
//      b.absolute2LocalX(ux)
////      val adjustment = b.localX() - b.absoluteX()
////      ux + adjustment
//    }
    val localX = b.absolute2LocalX(ux)
    t.style.left(Length.Pixels(math.round(localX).toInt))
  }

  private def updateElementY(y: Double, vertical: Vertical) = {
    t.data("y", y.toString)
    doUpdate {
      this.y := y     // TODO: adjust for vertical
    }
    val ly = math.round(coordinates.localizeY(y, this))
    val uy = vertical match {
      case Vertical.Top => ly
      case Vertical.Middle => ly - (b.height() / 2.0)
      case Vertical.Bottom => ly - b.height()
    }
    val localY = b.absolute2LocalY(uy)
//    val localY = if (t.style.position() == Position.Absolute) {
//      uy
//    } else {
//      b.absolute2LocalY(uy)
////      val adjustment = b.localY() - b.absoluteY()
////      uy + adjustment
//    }
    t.style.top(Length.Pixels(math.round(localY).toInt))
  }
}