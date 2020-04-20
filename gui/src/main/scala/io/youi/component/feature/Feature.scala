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
}

object Feature {
  def nameFor[F <: Feature](tag: ClassTag[F]): String = tag.runtimeClass.getName

  def nameFor[F <: Feature](feature: F): String = feature.getClass.getName
}