package io.youi.example.ui

import io.youi.example.screen.ExampleScreen
import io.youi.hypertext.Container

trait UIExampleScreen extends ExampleScreen {
  lazy val container: Container = Container.cached(content)
}