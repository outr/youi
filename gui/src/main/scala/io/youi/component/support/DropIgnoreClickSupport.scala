package io.youi.component.support

import io.youi.component.Component

trait DropIgnoreClickSupport {
  this: Component =>

  classes += "drop-ignore-click"
}
