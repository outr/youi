package io.youi.theme

import reactify.Var

trait Theme {
  lazy val parent: Var[Option[Theme]] = Var(defaultParent)

  def defaultParent: Option[Theme]
}

object Theme