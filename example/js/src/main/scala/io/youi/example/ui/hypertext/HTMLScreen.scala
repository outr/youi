package io.youi.example.ui.hypertext

import io.youi.example.ui.UIExampleScreen
import io.youi.hypertext.Container

trait HTMLScreen extends UIExampleScreen {
  lazy val container: Container = Container.cached(content)
}
