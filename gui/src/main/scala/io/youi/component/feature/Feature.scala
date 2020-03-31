package io.youi.component.feature

import io.youi.component.Component
import org.scalajs.dom.html

import scala.reflect.ClassTag

trait Feature {
  protected def component: Component

  Component.addFeature(component, this)
}

object Feature {
  def nameFor[F <: Feature](tag: ClassTag[F]): String = tag.runtimeClass.getName

  def nameFor[F <: Feature](feature: F): String = feature.getClass.getName
}