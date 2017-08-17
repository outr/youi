package io.youi.theme

import reactify.Var

trait Theme {
  protected lazy val parentTheme: Var[Option[Theme]] = Var(defaultThemeParent)

  protected def defaultThemeParent: Option[Theme]
}

object Theme