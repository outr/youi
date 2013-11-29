package org.hyperscala.ui

import org.powerscala.StorageComponent
import org.hyperscala.web.Webpage
import org.hyperscala.html.HTMLTag
import org.powerscala.event.Listenable
import org.powerscala.property.Property
import org.hyperscala.css.attributes._
import org.hyperscala.Unique

/**
 * @author Matt Hicks <matt@outr.com>
 */
abstract class Coordinates extends Listenable with StorageComponent[CoordinatesTag, HTMLTag] {
  Webpage().require(Bounding)

  val id = Unique()

  override protected def componentIdentifier = s"coordinates-$id"

  def coordinateX(ct: CoordinatesTag): Double
  def coordinateY(ct: CoordinatesTag): Double

  def localizeX(ct: CoordinatesTag): Double
  def localizeY(ct: CoordinatesTag): Double

  protected def create(t: HTMLTag) = synchronized {
    new CoordinatesTag(this, Bounding(t), t)
  }
}

class CoordinatesOffsetFromCenter(offsetX: Double = 0.0, offsetY: Double = 0.0) extends Coordinates {
  def coordinateX(ct: CoordinatesTag) = ct.b.absoluteX() - (WindowSize.width() / 2.0)

  def coordinateY(ct: CoordinatesTag) = ct.b.absoluteY() - (WindowSize.height() / 2.0)

  def localizeX(ct: CoordinatesTag) = ct.x() + (WindowSize.width() / 2.0)

  def localizeY(ct: CoordinatesTag) = ct.y() + (WindowSize.height() / 2.0)

  override protected def create(t: HTMLTag) = {
    val ct = super.create(t)
    WindowSize.width.change and WindowSize.height.change on {   // This coordinate system depends on WindowSize as well
      case evt => ct.update()
    }
    ct
  }
}

class CoordinatesTag(coordinates: Coordinates, val b: Bounding, val t: HTMLTag) extends Listenable {
  private val updatingCoordinates = new ThreadLocal[Boolean] {
    override def initialValue() = false
  }
  def isUpdatingCoordinates = updatingCoordinates.get()

  val x = new Property[Double](default = Some(-1.0))
  val y = new Property[Double](default = Some(-1.0))
  val horizontal = new Property[Horizontal](default = Some(Horizontal.Left))
  val vertical = new Property[Vertical](default = Some(Vertical.Middle))
  val manageX = new Property[Boolean](default = Option(false))
  val manageY = new Property[Boolean](default = Option(false))
  val enabled = new Property[Boolean](default = Some(true))

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
      manageX := true
      update()
    }
  }
  y.change.on {
    case evt => if (!isUpdatingCoordinates) {
      manageY := true
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

  def update() = if (enabled()) {
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

  private def updateCoordinatesX() = {
    updatingCoordinates.set(true)
    try {
      val cx = coordinates.coordinateX(this)
      val ux = horizontal() match {
        case Horizontal.Left => cx
        case Horizontal.Center => cx + (b.width() / 2.0)
        case Horizontal.Right => cx + b.width()
      }
      x := ux
    } finally {
      updatingCoordinates.set(false)
    }
  }

  private def updateCoordinatesY() = {
    updatingCoordinates.set(true)
    try {
      val cy = coordinates.coordinateY(this)
      val uy = vertical() match {
        case Vertical.Top => cy
        case Vertical.Middle => cy + (b.height() / 2.0)
        case Vertical.Bottom => cy + b.height()
      }
      y := uy
    } finally {
      updatingCoordinates.set(false)
    }
  }

  private def updateElementX() = {
    val lx = math.round(coordinates.localizeX(this)).toInt
    val ux = horizontal() match {
      case Horizontal.Left => lx
      case Horizontal.Center => lx - (b.width() / 2.0)
      case Horizontal.Right => lx - b.width()
    }
    val localX = if (t.style.position() == Position.Absolute) {
      ux
    } else {
      val adjustment = b.localX() - b.absoluteX()
      ux + adjustment
    }
    t.style.left(Length.Pixels(math.round(localX).toInt))
  }

  private def updateElementY() = {
    val ly = math.round(coordinates.localizeY(this)).toInt
    val uy = vertical() match {
      case Vertical.Top => ly
      case Vertical.Middle => ly - (b.height() / 2.0)
      case Vertical.Bottom => ly - b.height()
    }
    val localY = if (t.style.position() == Position.Absolute) {
      uy
    } else {
      val adjustment = b.localY() - b.absoluteY()
      uy + adjustment
    }
    t.style.top(Length.Pixels(math.round(localY).toInt))
  }
}