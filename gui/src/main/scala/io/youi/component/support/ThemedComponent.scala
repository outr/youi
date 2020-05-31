package io.youi.component.support

import io.youi.component.Component
import io.youi.theme.Theme

trait ThemedComponent extends ThemeSupport {
  this: Component =>

  protected def defaultTheme: Theme

  theme += defaultTheme
}