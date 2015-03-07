package org.hyperscala.fabricjs.prop

import org.hyperscala.fabricjs.Object
import org.hyperscala.fabricjs.util.Ease
import org.powerscala.concurrent.{TimeAmount, Time}
import org.powerscala.enum.{Enumerated, EnumEntry}
import org.powerscala.property.Property
import org.hyperscala.javascript.dsl._

import scala.collection.mutable.ListBuffer

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ObjectProperty[T](val name: String, val o: Object)(implicit manifest: Manifest[T]) extends Property[T](default = None)(o, manifest) {
  def animate(adjust: Adjust, from: Option[Double] = None, duration: Double = 0.5, ease: Ease = Ease.InSine) = {
    val canvas = o.canvas.getOrElse(throw new RuntimeException("Object not attached to Canvas, cannot animate!"))
    var props = ListBuffer.empty[String]
    from match {
      case Some(d) => props += s"from: $d"
      case None => // Ignore
    }
    duration match {
      case 0.5 => // Ignore
      case _ => props += s"duration: ${TimeAmount(duration).toMilliseconds}"
    }
    ease match {
      case Ease.InSine => // Ignore
      case _ => props += s"easing: fabric.util.ease.ease${ease.name}"
    }
    val options = props.mkString("{", ", ", "}")
    canvas.eval(s"FabricJS.animate('${canvas.id}', '${o.id}', '${name}', ${adjust.toJS}, $options);")
  }
}

case class Adjust(value: Double, adjustType: AdjustType) {
  def toJS = adjustType match {
    case AdjustType.Absolute => s"$value"
    case AdjustType.Relative => if (value >= 0.0) {
      s"'+=$value'"
    } else {
      s"'-=$value'"
    }
  }
}

object Adjust {
  def apply(value: Double): Adjust = Adjust(value, AdjustType.Absolute)
  def +=(value: Double) = Adjust(value, AdjustType.Relative)
  def -=(value: Double) = Adjust(-value, AdjustType.Relative)
}

class AdjustType private() extends EnumEntry

object AdjustType extends Enumerated[AdjustType] {
  val Absolute = new AdjustType
  val Relative = new AdjustType
}