package io.youi.component.feature

import io.youi.component.Component
import io.youi.theme.{ElementState, Theme}

import scala.scalajs.js.|

class ThemeFeature(val component: Component) extends Feature {
  override protected def parent: FeatureParent = component

  private def className(selector: String): String = {
    Theme.classNameFromSelector(selector).getOrElse(throw new RuntimeException(s"Cannot add a non-class Theme to a Component: $selector"))
  }

  def +=(theme: Theme): Unit = component.classes += className(theme.selector)
  def -=(theme: Theme): Unit = component.classes -= className(theme.selector)
  def create(state: ElementState = ElementState.Default): Theme = {
    val theme = Theme.createClassName(state)
    this += theme
    theme
  }
}