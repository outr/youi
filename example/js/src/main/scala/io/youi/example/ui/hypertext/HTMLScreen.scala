package io.youi.example.ui.hypertext

import io.youi._
import io.youi.example.ui.UIExampleScreen
import io.youi.hypertext.Container

trait HTMLScreen extends UIExampleScreen {
  lazy val container: Container = {
    val c = Container.cached(content)
    AnimationFrame.delta.attach(c.update)
    c
  }
}
