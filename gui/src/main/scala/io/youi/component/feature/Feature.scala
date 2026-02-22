package io.youi.component.feature

import io.youi.component.Component
import io.youi.theme.Theme

import scala.reflect.ClassTag

trait Feature {
  addFeature(Feature.nameFor(this))

  protected def parent: FeatureParent

  protected def componentOption: Option[Component] = (parent: Any) match {
    case c: Component => Some(c)
    case _ => None
  }

  protected def addFeature(name: String): Unit = (parent: Any) match {
    case c: Component => FeatureParent.add(name, c, this)
    case t: Theme => FeatureParent.add(name, t, this)
  }

  /** Registers this feature under [[name]] only if no feature is already registered there.
   *  Use this for "default" registrations that should yield to higher-priority features
   *  (e.g. [[SizeFeature]] yielding to [[MeasuredFeature]] for width/height). */
  protected def addFeatureIfAbsent(name: String): Unit = {
    if (FeatureParent.get[Feature](name, parent).isEmpty) addFeature(name)
  }
}

object Feature {
  private val Pixels = """([0-9.-]+)px""".r

  def nameFor[F <: Feature](tag: ClassTag[F]): String = tag.runtimeClass.getName

  def nameFor[F <: Feature](feature: F): String = feature.getClass.getName

  def sizeOption(s: String): Option[Double] = s match {
    case "" | null => None
    case Pixels(d) => Some(d.toDouble)
    case _ => throw new RuntimeException(s"Unsupported size: [$s]")
  }
  
  def size(s: String): Double = sizeOption(s).getOrElse(0.0)
}