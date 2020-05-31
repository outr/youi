package io.youi.component.support

import io.youi.component.Component
import io.youi.theme.Theme

/**
  * ThemeComponent is a convenience trait presuming that its companion object is a Theme without a unique selector
  */
trait ThemedComponent {
  this: Component =>

  classes += getClass.getSimpleName
}