package io.youi.theme

import reactify._

trait Theme {
  protected lazy val parentTheme: Var[Option[Theme]] = Var(defaultThemeParent)

  protected def defaultThemeParent: Option[Theme]
}