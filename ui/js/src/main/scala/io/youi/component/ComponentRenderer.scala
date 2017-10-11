package io.youi.component

import io.youi.Renderer

class ComponentRenderer(renderer: Renderer) extends AbstractContainer {
  override type Child = Component
}
