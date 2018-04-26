package io.youi.example.ui.component

import io.youi.component.Container
import io.youi.example.ClientExampleApplication
import io.youi._

class Header extends Container {
  position.left := 0.0
  position.top := 0.0
  size.width := ui.size.width
  size.height := 120.0
  background := ClientExampleApplication.colors.blue
}